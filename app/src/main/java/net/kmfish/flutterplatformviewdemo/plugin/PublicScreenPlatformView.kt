package net.kmfish.flutterplatformviewdemo.plugin

import android.content.Context
import android.util.Log
import android.view.View
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.platform.PlatformView

const val VIEW_TYPE = "platform_public_chat"

class PublicScreenPlatformView(
    private val context: Context,
    messenger: BinaryMessenger,
    id: Int?,
    private val params: Map<String, Any>?
) : PlatformView {

    private val TAG = "PublicScreenP"

    private var mNativeView: View? = null

    override fun getView(): View {
        return PublicScreenProxyView(context, params?.get("text")?.toString() ?: "default")
    }

    override fun dispose() {
        Log.i(TAG, "dispose")
        mNativeView = null
    }

    override fun onFlutterViewAttached(flutterView: View) {
        Log.d(TAG, "onFlutterViewAttached")
    }

    override fun onFlutterViewDetached() {
        Log.d(TAG, "onFlutterViewDetached")
    }
}