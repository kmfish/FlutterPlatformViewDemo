package net.kmfish.flutter.share_platform_plugin

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.platform.PlatformView

class AndroidTextView(
    context: Context,
    messenger: BinaryMessenger,
    id: Int?,
    params: Map<String, Any>?
) : PlatformView {
    
    private val TAG = "AndroidTextView"

    private val mAndroidTextView: TextView = TextView(context)

    init {
        Log.d(TAG, "init")
        val text = params?.get("text") as CharSequence?
        mAndroidTextView.text = text ?: "android native TextView"
        mAndroidTextView.textSize = 30f
    }

    override fun getView(): View = mAndroidTextView

    override fun dispose() {
        Log.d(TAG, "dispose")
    }

}