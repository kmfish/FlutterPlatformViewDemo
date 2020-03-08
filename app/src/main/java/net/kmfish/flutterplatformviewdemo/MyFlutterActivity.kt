package net.kmfish.flutterplatformviewdemo

import android.content.Context
import android.content.Intent
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import net.kmfish.flutterplatformviewdemo.plugin.PublicScreenPlugin

class MyFlutterActivity : FlutterActivity() {

    internal class MyNewEngineIntentBuilder
        (activityClass: Class<out FlutterActivity?>) :
        NewEngineIntentBuilder(activityClass)

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.d(TAG, "configureFlutterEngine")
        registerChatViewPlugin(this, flutterEngine)
    }

    override fun cleanUpFlutterEngine(flutterEngine: FlutterEngine) {
        super.cleanUpFlutterEngine(flutterEngine)
        Log.d(TAG, "cleanUpFlutterEngine")
        unregisterChatViewPlugin(flutterEngine)
    }

    private fun registerChatViewPlugin(
        context: Context,
        engine: FlutterEngine
    ) {
        engine.plugins.add(PublicScreenPlugin())
    }

    private fun unregisterChatViewPlugin(engine: FlutterEngine) {
        engine.plugins.remove(PublicScreenPlugin::class.java)
    }

    companion object {
        private const val TAG = "MyFlutterActivity"
        fun createDefaultIntent(launchContext: Context): Intent {
            return MyNewEngineIntentBuilder(MyFlutterActivity::class.java).build(launchContext)
        }
    }
}