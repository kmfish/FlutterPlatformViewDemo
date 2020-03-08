package net.kmfish.flutterplatformviewdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_open_flutter_fragment)?.setOnClickListener {
            startActivity(Intent(this@MainActivity, FlutterContainerActivity::class.java))
        }

        findViewById<Button>(R.id.btn_open_flutter_activity)?.setOnClickListener {
            startActivity(MyFlutterActivity.createDefaultIntent(this@MainActivity))
        }
    }
}
