package cn.com.kotlin.bezierline.wegit

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator


class WaterBezierView : View {

    private var mWight = 0
    private var mHeight = 0
    private var mPaint: Paint? = null

    private var itemWater:Float =0f
    //水平偏移量
    private var dx:Float=0f

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mPaint = Paint()
        mPaint?.apply {
            isAntiAlias = true
            isDither = true
            color = Color.parseColor("#FF3891")
            style = Paint.Style.FILL
        }
        itemWater=context.resources.displayMetrics.widthPixels.toFloat()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWight = measuredWidth
        mHeight = measuredHeight
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val path = Path()
        path.reset()
        path.moveTo(-itemWater+dx, mHeight / 2f)
        var i = -itemWater
        while (i < width+itemWater) {
            path.rQuadTo(itemWater/ 4f, -100f, itemWater/ 2f, 0f)
            path.rQuadTo(itemWater/ 4f, 100f, itemWater/ 2f, 0f)
            i +=itemWater
        }
//        path.moveTo((mWight / 2 - itemWater).toFloat(), mHeight / 2f)
//        path.quadTo((mWight / 2 - itemWater / 2).toFloat(), mHeight / 2f - 100, mWight / 2f, mHeight / 2f)
//        path.quadTo(
//            (mWight / 2 + itemWater / 2).toFloat(),
//            mHeight / 2f + 100,
//            (mWight / 2 + 200).toFloat(),
//            mHeight / 2f
//        )
        path.lineTo(mWight.toFloat(),mHeight.toFloat())
        path.lineTo(0f,mHeight.toFloat())
        path.close()
        canvas?.drawPath(path, mPaint)
    }

    fun startAnimation(){
        val valueAnimator = ValueAnimator.ofFloat( 0f, itemWater)
        valueAnimator.duration = 2000
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.addUpdateListener { animation ->
            //水平方向的偏移量
            dx=animation.animatedValue as Float
            invalidate()
        }
        valueAnimator.setTarget(this)
        valueAnimator.start()
    }
}