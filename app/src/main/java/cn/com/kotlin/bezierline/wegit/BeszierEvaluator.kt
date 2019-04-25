package cn.com.kotlin.bezierline.wegit

import android.animation.TypeEvaluator
import android.graphics.PointF

class BeszierEvaluator(private val point1: PointF, private val point2: PointF) : TypeEvaluator<PointF> {


    /**
     * @param fraction value 0~1 作为bezier的事件t
     */
    override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
        // B(t) = P0(1-t)(1-t)(1-t)+3*P1*t*(1-t)*(1-t)+3*P2*t*t*(1-t)+P3*t*t*t
        //这里计算不要换行，会导致终点为0，0
        val point = PointF()
        point.x =
            startValue.x * (1f - fraction) * (1f - fraction) * (1f - fraction) + 3 * point1.x * fraction * (1f - fraction) * (1f - fraction) + 3 * point2.x * fraction * fraction * (1f - fraction) + endValue.x * fraction * fraction * fraction

        point.y =
            startValue.y * (1f - fraction) * (1f - fraction) * (1f - fraction) + 3 * point1.y * fraction * (1f - fraction) * (1f - fraction) + 3 * point2.y * fraction * fraction * (1f - fraction) + endValue.y * fraction * fraction * fraction

        return point
    }
}