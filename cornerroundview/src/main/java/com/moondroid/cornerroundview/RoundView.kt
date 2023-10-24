package com.moondroid.cornerroundview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class RoundView : FrameLayout {
    private var topLeftCornerRadius: Float = 0.0f
    private var topRightCornerRadius: Float = 0.0f
    private var bottomLeftCornerRadius: Float = 0.0f
    private var bottomRightCornerRadius: Float = 0.0f
    private var cornerRadius: Float = 0.0f

    constructor(context: Context) : super(context, null, 0) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {
        init(context, attrs)
    }

    constructor(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int,
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(
        context: Context,
        attrs: AttributeSet?,
    ) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.RoundView, 0, 0)
        cornerRadius =
            typeArray.getDimension(R.styleable.RoundView_cornerRadius, 0.0f)
        topLeftCornerRadius =
            typeArray.getDimension(R.styleable.RoundView_topLeftCornerRadius, 0.0f)
        topRightCornerRadius =
            typeArray.getDimension(R.styleable.RoundView_topRightCornerRadius, 0.0f)
        bottomLeftCornerRadius =
            typeArray.getDimension(R.styleable.RoundView_bottomLeftCornerRadius, 0.0f)
        bottomRightCornerRadius =
            typeArray.getDimension(R.styleable.RoundView_bottomRightCornerRadius, 0.0f)

        typeArray.recycle()
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    override fun dispatchDraw(canvas: Canvas) {
        val count = canvas.save()
        val path = Path()

        val corners = if (cornerRadius != 0.0f) {
            arrayOf(
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius
            )
        } else {
            arrayOf(
                topLeftCornerRadius, topLeftCornerRadius,
                topRightCornerRadius, topRightCornerRadius,
                bottomRightCornerRadius, bottomRightCornerRadius,
                bottomLeftCornerRadius, bottomLeftCornerRadius
            )
        }

        val cornerDimensions = FloatArray(8) {
            corners[it]
        }


        path.addRoundRect(
            RectF(0F, 0F, canvas.width.toFloat(), canvas.height.toFloat()),
            cornerDimensions,
            Path.Direction.CW
        )

        canvas.clipPath(path)

        super.dispatchDraw(canvas)
        count.let { canvas.restoreToCount(it) }
    }
}