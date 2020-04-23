package github.coderbuck.codehub.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import github.coderbuck.codehub.R
import github.coderbuck.codehub.bean.CommitGraphBean

class GraphView : View {
    val paint = Paint()
    val rect = Rect()
    var commitGraphBean: CommitGraphBean? = null
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        paint.color = resources.getColor(R.color.colorAccent)
        paint.strokeWidth = 2f
        paint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (commitGraphBean == null || canvas == null) {
            return
        }
        val graphBean = commitGraphBean!!

        val scale = (width - 100).toFloat() / graphBean.width
        canvas.scale(scale, scale)
        println("gwf: onDraw")

        // 外边框
        paint.style = Paint.Style.STROKE
        rect.set(0, 0, graphBean.width, graphBean.height)
        canvas.drawRect(rect, paint)
        val pdx = graphBean.translate.x
        val pdy = graphBean.translate.y

        graphBean.columns.forEach { columnBean ->
            val pdx1 = columnBean.transform.x
            val pdy1 = columnBean.transform.y
            paint.style = Paint.Style.FILL_AND_STROKE
            columnBean.cells.forEach { cell ->
                rect.set(0, 0, cell.width, cell.height)
                rect.offset(pdx + pdx1, pdy + pdy1 + cell.y)
                if (cell.data_date.equals("2020-04-18")) {
                    println("ggg: cell = " + cell)
                    println("ggg: pdx = $pdx, pdx1 = $pdx1, pdy = $pdy, pdy1 = $pdy1," + cell)
                }

                paint.color = Color.parseColor(cell.fill)
                if (rect.bottom > graphBean.height) {
                    println("gwf : rect.bottom = ${rect.bottom} > graphBean.height = ${graphBean.height} columnBean = ${cell.data_date} " + cell)
                }
                canvas.drawRect(rect, paint)
            }
        }
        canvas.scale(1f, 1f)
    }
}