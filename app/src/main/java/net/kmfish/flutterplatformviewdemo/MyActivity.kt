package net.kmfish.flutterplatformviewdemo

import android.content.Context
import android.os.Bundle
import io.flutter.app.FlutterActivity
import io.flutter.view.FlutterNativeView
import net.kmfish.flutterplatformviewdemo.plugin.PublicScreenViewFactory
import net.kmfish.flutterplatformviewdemo.plugin.VIEW_TYPE

class MyActivity : FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun createFlutterNativeView(): FlutterNativeView {
        return buildMyFlutterNativeView(this)
    }

    private fun buildMyFlutterNativeView(context: Context): FlutterNativeView {
        val nativeView = FlutterNativeView(context)
        nativeView.pluginRegistry
            .registrarFor("mykey")?.run {
                val viewFactory = PublicScreenViewFactory(messenger())
                platformViewRegistry().registerViewFactory(VIEW_TYPE, viewFactory)
            }
        
        return nativeView
    }
}