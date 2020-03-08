package net.kmfish.flutterplatformviewdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.flutter.view.FlutterMain

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_open_flutter_fragment)?.setOnClickListener {
            startActivity(Intent(this, FlutterContainerActivity::class.java))
        }

        findViewById<Button>(R.id.btn_open_flutter_activity)?.setOnClickListener {
            startActivity(MyFlutterActivity.createDefaultIntent(this))
        }

        findViewById<Button>(R.id.btn_open_activity_with_flutter_view)?.setOnClickListener {
            FlutterMain.startInitialization(this)
            startActivity(Intent(this, MyActivity::class.java))
        }
    }
}
