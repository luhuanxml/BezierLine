package cn.com.kotlin.bezierline.wegit

import android.animation.*
import android.content.Context
import android.graphics.PointF
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.ImageView
import cn.com.kotlin.bezierline.R
import java.util.*

class LoveLayout : ConstraintLayout {

    private var plain: Drawable? = null
    private var uav: Drawable? = null
    private var drawables: Array<Drawable>? = null

    private var imgWight = 0
    private var imgHeight = 0

    private var mWight = 0
    private var mHeight = 0
    private val random = Random()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        plain = ContextCompat.getDrawable(context, R.mipmap.airplane)
        uav = ContextCompat.getDrawable(context, R.mipmap.uav)
        drawables = arrayOf(plain!!, uav!!)
        //图片实际尺寸的宽高
        imgWight = plain!!.intrinsicWidth
        imgHeight = plain!!.intrinsicHeight
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWight = measuredWidth
        mHeight = measuredHeight
    }

    //true为飞机 false为无人机
    private var bool:Boolean=true

    fun addImage() {
        val imageView = ImageView(this.context)
        val parentParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        parentParams.bottomToBottom = 0
        parentParams.leftToLeft = 0
        parentParams.rightToRight = 0
        imageView.layoutParams = parentParams
        bool = when {
            bool -> {
                imageView.setImageDrawable(plain)
                false
            }
            else -> {
                imageView.setImageDrawable(uav)
                true
            }
        }
        addView(imageView)

        //开启动画
        val animator = createAnimator(imageView)
        //动画结束后 清除控件
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                removeView(imageView)
            }
        })
        animator.start()
    }

    private fun createAnimator(imageView: ImageView): Animator {
        //alpha 动画
        val alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0.3f, 1f)
        //缩放动画
        val scaleXAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 0.2f, 1f).setDuration(500)
        val scaleYAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 0.2f, 1f).setDuration(500)
        val enterAnimatorSet = AnimatorSet()
        enterAnimatorSet.playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator)

        //bezier 曲线动画
        val pointFP1 = getPointF(1)
        val pointFP2 = getPointF(2)
        val pointFP0 = PointF(mWight / 2f, mHeight - (imgHeight / 2f))
        val pointFP3 = PointF(mWight / 2f, imgHeight / 2f)
        //使用估值器计算轨迹
        val bezierEvaluator = BeszierEvaluator(pointFP1, pointFP2)
        val bezierAnimator = ValueAnimator.ofObject(bezierEvaluator, pointFP0, pointFP3).setDuration(3000)
        bezierAnimator.addUpdateListener {
            val pointF = it.animatedValue as PointF
            imageView.x = pointF.x
            imageView.y = pointF.y
            imageView.alpha = 1f - it.animatedFraction
        }
        val bezierAnimatorSet = AnimatorSet()
        bezierAnimatorSet.playSequentially(enterAnimatorSet, bezierAnimator)
        bezierAnimatorSet.setTarget(imageView)
        return bezierAnimatorSet
    }

    private fun getPointF(p: Int): PointF {
        val pointF = PointF()
        when (p) {
            1 -> {
                pointF.x = random.nextInt(mWight).toFloat()
                pointF.y = random.nextInt(mHeight / 2).toFloat()
            }
            2 -> {
                pointF.x = random.nextInt(mWight).toFloat()
                pointF.y = (random.nextInt(mHeight / 2) + mHeight / 2).toFloat()
            }
        }
        return pointF
    }
}