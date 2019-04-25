package cn.com.kotlin.bezierline

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_twice_bezier.*

class TwiceBezierActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twice_bezier)
        btn.setOnClickListener {
            waterBezier.startAnimation()
        }
    }

    override fun onResume() {
        super.onResume()
        waterBezier.startAnimation()
    }
}
