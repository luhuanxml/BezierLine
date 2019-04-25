package cn.com.kotlin.bezierline

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_love_bezier.*
import java.util.*

class LoveBezierActivity : AppCompatActivity() {
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 1) {
                if (loveBezier.measuredWidth!= 0) {
                    loveBezier.addImage()
                }
            }
        }
    }

    lateinit var timer: Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_love_bezier)
        timer = Timer()
        btn.setOnClickListener {
            loveBezier.addImage()
        }
    }

    override fun onResume() {
        super.onResume()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val message = Message()
                message.what = 1
                handler.sendMessage(message)
            }
        }, 0, 200)
    }

}
