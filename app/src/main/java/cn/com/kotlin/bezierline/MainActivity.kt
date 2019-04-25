package cn.com.kotlin.bezierline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onceBezier.setOnClickListener {
            startActivity(Intent(this@MainActivity, OnceBezierActivity::class.java))
        }
        twiceBezier.setOnClickListener {
            startActivity(Intent(this@MainActivity, TwiceBezierActivity::class.java))
        }
        thirdBezier.setOnClickListener {
            startActivity(Intent(this@MainActivity, ThirdBezierActivity::class.java))
        }
        forthBezier.setOnClickListener {
            startActivity(Intent(this@MainActivity, ForthBezierActivity::class.java))
        }
        loveBezier.setOnClickListener {
            startActivity(Intent(this@MainActivity,LoveBezierActivity::class.java))
        }
    }
}
