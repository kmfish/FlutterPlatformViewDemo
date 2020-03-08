package net.kmfish.flutterplatformviewdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import io.flutter.embedding.android.FlutterFragment

class FlutterContainerActivity : AppCompatActivity() {

    private val TAG = "FlutterContainer"

    private val TAG_FLUTTER_FRAGMENT = "flutter_fragment"
    private var mFlutterFragment: FlutterFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_flutter_container)
        
        addFlutterFragment()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    private fun addFlutterFragment() {
        Log.d(TAG, "addFlutterFragment")
        val fragmentManager: FragmentManager? = supportFragmentManager

        // Attempt to find an existing FlutterFragment, in case this is not the
        // first time that onCreate() was run.
        mFlutterFragment =
            fragmentManager?.findFragmentByTag(TAG_FLUTTER_FRAGMENT) as FlutterFragment?

        // Create and attach a FlutterFragment if one does not exist.
        if (mFlutterFragment == null) {
            val newFlutterFragment = FlutterFragment
                .NewEngineFragmentBuilder(MyFlutterFragment::class.java)
                .build<MyFlutterFragment>()

            mFlutterFragment = newFlutterFragment
            fragmentManager
                ?.beginTransaction()
                ?.add(
                    R.id.flutter_container,
                    newFlutterFragment,
                    TAG_FLUTTER_FRAGMENT
                )
                ?.commit()

        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}