package net.kmfish.flutterplatformviewdemo

import android.content.Context
import android.os.Bundle
import android.view.View
import net.kmfish.flutterplatformviewdemo.plugin.PublicScreenPlugin
import io.flutter.Log
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine

class MyFlutterFragment : FlutterFragment() {

    private val TAG = "MyFlutterFragment"

    override fun provideFlutterEngine(context: Context): FlutterEngine? {
        Log.d(TAG, "provideFlutterEngine")
        return FlutterEngine(context)
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.d(TAG, "configureFlutterEngine: $flutterEngine")
        registerChatViewPlugin(context, flutterEngine)
    }

    override fun cleanUpFlutterEngine(flutterEngine: FlutterEngine) {
        super.cleanUpFlutterEngine(flutterEngine)
        Log.d(TAG, "cleanUpFlutterEngine")
        unregisterChatViewPlugin(flutterEngine)
    }

    private fun registerChatViewPlugin(context: Context, engine: FlutterEngine) {
        val plugin = PublicScreenPlugin()
        engine.plugins.add(plugin)
    }

    private fun unregisterChatViewPlugin(engine: FlutterEngine) {
        engine.plugins.remove(PublicScreenPlugin::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach")
        super.onAttach(context)
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        val flutterEngine = flutterEngine
        // fix flutter bug: https://github.com/flutter/flutter/issues/48063
//        flutterEngine?.platformViewsController?.onFlutterViewDestroyed()
        super.onDestroy()
    }
}