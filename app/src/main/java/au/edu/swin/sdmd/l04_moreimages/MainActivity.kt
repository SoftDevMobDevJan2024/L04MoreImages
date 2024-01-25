package au.edu.swin.sdmd.l04_moreimages

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView

const val KEY_IMAGE = "image_key"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<ImageView>(R.id.imageView)

        /**
         * This is the code to restore the state.
         *
         * The override function onRestoreInstanceState can also be used, however
         * this is called at a different point in the lifecycle.
         */
        savedInstanceState?.let {
            image.contentDescription = it.getString(KEY_IMAGE)

            image.apply {
                when (contentDescription) {
                    "station" -> setImageDrawable(
                        getDrawable(
                            R.drawable.station
                        )
                    )
                    "college" -> setImageDrawable(
                        getDrawable(
                            R.drawable.college
                        )
                    )
                    "theatre" -> setImageDrawable(
                        getDrawable(
                            R.drawable.theatre
                        )
                    )
                }
            }
            Log.i("LIFECYCLE", "onCreate (saved instance restored)")
        }


        val station = findViewById<Button>(R.id.station)
        station.setOnClickListener {
            image.setImageDrawable(
                getDrawable(R.drawable.station))
            image.contentDescription = "station"
        }

        val onClickTheatre = View.OnClickListener {
            image.setImageDrawable(
                getDrawable(R.drawable.theatre))
            image.contentDescription = "theatre"
        }

        val theatre = findViewById<Button>(R.id.theatre)
        theatre.setOnClickListener(onClickTheatre)
    }

    fun onClickCollege(v: View) {
        val image = findViewById<ImageView>(R.id.imageView)
        image.setImageDrawable(
            getDrawable(R.drawable.college))
        image.contentDescription = "college"
    }

    /**
     * This is needed to save state. The variables to save need to be
     * added to a Bundle with a key, in this case "image".
     */
    override fun onSaveInstanceState(outState: Bundle) {
        val image = findViewById<ImageView>(R.id.imageView)
        outState.putString(KEY_IMAGE, image.contentDescription.toString())

        // call super AFTER
        super.onSaveInstanceState(outState)

        Log.i("LIFECYCLE", "onSaveInstanceState")
    }

    // These override functions are ONLY for testing to observe
    // lifecycle functions
    // In practice, ONLY override the functions that you need!
    override fun onStart() {
        super.onStart()
        Log.i("LIFECYCLE", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LIFECYCLE", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LIFECYCLE", "onRestart")
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        // call super BEFORE
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        Log.i("LIFECYCLE", "onRestoreInstanceState")
    }

//    override fun onConfigurationChanged(newConfig: Configuration) {
//        // call super BEFORE
//        super.onConfigurationChanged(newConfig)
//        Log.i("LIFECYCLE", "onConfigurationChanged")
//    }

    // For these functions: call the super function last
    override fun onPause() {
        Log.i("LIFECYCLE", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i("LIFECYCLE", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("LIFECYCLE", "onDestroy")
        super.onDestroy()
    }

}