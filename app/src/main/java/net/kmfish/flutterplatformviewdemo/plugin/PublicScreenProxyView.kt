package net.kmfish.flutterplatformviewdemo.plugin

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import net.kmfish.flutterplatformviewdemo.R

@SuppressLint("ViewConstructor")
class PublicScreenProxyView(
    context: Context,
    text: String
) : FrameLayout(context, null, 0) {

    private val TAG = "PublicScreenProxyView"

    init {
        Log.d(TAG, "PublicScreenProxyView init")
//        setBackgroundColor(Color.parseColor("#60FF0000"))
//        val textView = TextView(context)
//        textView.text = "This is PublicScreenProxyView."
//        textView.textSize = (20 * 2).toFloat()
//        val lp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
//        lp.gravity = Gravity.CENTER
//        addView(textView, lp)

        View.inflate(context, R.layout.layout_public_screen, this)

        findViewById<TextView>(R.id.tv_from_flutter)?.text = text
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "onAttachedToWindow")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "onDetachedFromWindow")
    }
}