package hu.ait.minesweeper.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import hu.ait.minesweeper.R
import hu.ait.minesweeper.model.MinesweeperModel
import android.graphics.Canvas
import hu.ait.minesweeper.MainActivity

class MinesweeperView (context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paintBackGround = Paint()
    var paintLine = Paint()
    var openCell: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.square)
    var bitmapDemo: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.pit)
    var bitmapDemo0: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.t)
    var bitmapDemo1: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.one)
    var bitmapDemo2: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.two)
    var bitmapDemo3: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.three)
    var bitmapDemo4: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.four)
    var bitmapDemo5: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.five)
    var bitmapDemo6: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.six)
    var bitmapDemo7: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.seven)
    var bitmapDemo8: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.eight)
    var bitmapDemoFlag: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.flag)

    init{
        paintBackGround.color= Color.LTGRAY
        paintBackGround.style=Paint.Style.FILL

        paintLine.color = Color.WHITE
        paintLine.style=Paint.Style.STROKE
        paintLine.strokeWidth = 5f

        MinesweeperModel.inItGameArea(9)
    }

    // The onSizeChanged function is called when the size of the view changes. In this implementation, it resizes all of the bitmap images used in the game to fit the new size.
    // This is done by calling the createScaledBitmap function on each bitmap and passing the new width and height values.
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        openCell = Bitmap.createScaledBitmap(openCell, width/9, height/9, false)
        bitmapDemo = Bitmap.createScaledBitmap(bitmapDemo, width/9, height/9, false)
        bitmapDemo0 = Bitmap.createScaledBitmap(bitmapDemo0, width/9, height/9, false)
        bitmapDemo1 = Bitmap.createScaledBitmap(bitmapDemo1, width/9, height/9, false)
        bitmapDemo2 = Bitmap.createScaledBitmap(bitmapDemo2, width/9, height/9, false)
        bitmapDemo3 = Bitmap.createScaledBitmap(bitmapDemo3, width/9, height/9, false)
        bitmapDemo4 = Bitmap.createScaledBitmap(bitmapDemo4, width/9, height/9, false)
        bitmapDemo5 = Bitmap.createScaledBitmap(bitmapDemo5, width/9, height/9, false)
        bitmapDemo6 = Bitmap.createScaledBitmap(bitmapDemo6, width/9, height/9, false)
        bitmapDemo7 = Bitmap.createScaledBitmap(bitmapDemo7, width/9, height/9, false)
        bitmapDemo8 = Bitmap.createScaledBitmap(bitmapDemo8, width/9, height/9, false)
        bitmapDemoFlag = Bitmap.createScaledBitmap(bitmapDemoFlag, width/9, height/9, false)
    }


    //The onDraw function is called to draw the game board and its contents. It first draws a background of 9x9 squares using the bitmapDemo0 image.
    // Then, it calls the drawGameArea function to draw the game board's grid lines.
    // After that, it calls the revealCell and revealFlag functions to draw the contents of each cell.
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0..8){
            for (j in 0..8){
                canvas.drawBitmap(bitmapDemo0, i * 1f * width / 9, j * 1f * height / 9, null)
            }
        }
        drawGameArea(canvas)
        revealCell(canvas)
        revealFlag(canvas)
    }

    // The drawGameArea function draws the game board's grid lines by using the drawRect and drawLine functions from the Canvas class.
    // It first draws a rectangle around the entire board using the paintLine object.
    // Then, it draws 9 horizontal lines and 9 vertical lines at equal intervals to create a 9x9 grid.
    private fun drawGameArea(canvas: Canvas) {
        // border
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLine)
        // 9 horizontal lines
        for (i in 1..9) {
            canvas.drawLine(
                0f, (i * height / 9).toFloat(), width.toFloat(), (i * height / 9).toFloat(),
                paintLine
            )
        }
        // 9 vertical lines
        for (i in 1..9) {
            canvas.drawLine(
                (i * width / 9).toFloat(), 0f, (i * width / 9).toFloat(), height.toFloat(),
                paintLine
            )
        }
    }

    // Reveal specified cell
    private fun revealCell(canvas: Canvas){
        for (Row in 0..8){
            for (Col in 0..8){
                if (MinesweeperModel.getFieldContent(Row, Col).isFlagged){
                    canvas.drawBitmap(bitmapDemoFlag,
                        (Row * 1f) * width / 9,
                        (Col* 1f) * height / 9,
                        null
                    )
                }
                if (MinesweeperModel.getFieldContent(Row, Col).bomb && MinesweeperModel.getFieldContent(Row, Col).wasClicked && !MinesweeperModel.getFieldContent(Row, Col).isFlagged) {
                    canvas.drawBitmap(bitmapDemo, (Row * 1f) * width / 9, (Col * 1f) * height / 9, null)
                    }
                else if (MinesweeperModel.getFieldContent(Row, Col).wasClicked && !MinesweeperModel.getFieldContent(Row, Col).isFlagged){
                    when (MinesweeperModel.getFieldContent(Row, Col).minesAround) {
                        0 -> {
                            canvas.drawBitmap(openCell,
                                (Row * 1f) * width / 9,
                                (Col * 1f) * height / 9,
                                null
                            )
                        }
                        1 -> {
                            canvas.drawBitmap(
                                bitmapDemo1,
                                (Row * 1f) * width / 9,
                                (Col * 1f) * height / 9,
                                null
                            )
                        }
                        2 -> {
                            canvas.drawBitmap(
                                bitmapDemo2,
                                (Row * 1f) * width / 9,
                                (Col * 1f) * height / 9,
                                null
                            )
                        }
                        3 -> {
                            canvas.drawBitmap(
                                bitmapDemo3,
                                (Row * 1f) * width / 9,
                                (Col * 1f) * height / 9,
                                null
                            )
                        }
                        4 -> {
                            canvas.drawBitmap(
                                bitmapDemo4,
                                (Row * 1f) * width / 9,
                                (Col * 1f) * height / 9,
                                null
                            )
                        }
                        5 -> {
                            canvas.drawBitmap(
                                bitmapDemo5,
                                (Row * 1f) * width / 9,
                                (Col * 1f) * height / 9,
                                null
                            )
                        }
                        6 -> {
                            canvas.drawBitmap(
                                bitmapDemo6,
                                (Row * 1f) * width / 9,
                                (Col * 1f) * height / 9,
                                null
                            )
                        }
                        7 -> {
                            canvas.drawBitmap(
                                bitmapDemo7,
                                (Row * 1f) * width / 9,
                                (Col * 1f) * height / 9,
                                null
                            )
                        }
                        8 -> {
                            canvas.drawBitmap(
                                bitmapDemo8,
                                (Row * 1f) * width / 9,
                                (Col * 1f) * height / 9,
                                null
                            )
                        }
                    }// when
                }
            }
        }
    }// revealCell

    // Reveal all cells
    private fun revealAll(canvas:Canvas) {
        for (i in 0..8) {
            for (j in 0..8) {
                MinesweeperModel.getFieldContent(i,j).wasClicked = true
                MinesweeperModel.countAdjacentMines(i,j)
                MinesweeperModel.getFieldContent(i,j).isFlagged = false
                revealCell(canvas)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN){
            val tX = event.x.toInt() / (width / 9)
            val tY = event.y.toInt() / (height / 9)

            if ((context as MainActivity).isFlagModeOn() && tX < 9 && tY < 9) {
                MinesweeperModel.getFieldContent(tX, tY).isFlagged = true
                revealFlag(Canvas())
            }

            if (tX < 9 && tY < 9 && !MinesweeperModel.getFieldContent(tX, tY).wasClicked) {
                MinesweeperModel.getFieldContent(tX, tY).wasClicked = true
                if (MinesweeperModel.getFieldContent(tX, tY).bomb && !MinesweeperModel.getFieldContent(tX, tY).isFlagged) {
                    revealAll(Canvas())
                    (context as MainActivity).setLoserText(resources.getString(R.string.loser_text))
                    invalidate()
                }
                else {
                    MinesweeperModel.countAdjacentMines(tX, tY)
                    revealCell(Canvas())
                    if (MinesweeperModel.winnerCheck()){
                        (context as MainActivity).setLoserText(resources.getString(R.string.winner_text))
                        invalidate()
                    }
                }
            }
            //tell android that the view is not valid any more and it should be redrawn,
            // then the Android system will call the onDraw again
            invalidate()
        }
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val h = MeasureSpec.getSize(heightMeasureSpec)
        val d = if (w == 0) h else if (h == 0) w else if (w < h) w else h
        setMeasuredDimension(d, d)
    }

    // Reset Game to Start
    fun resetGame(){
        MinesweeperModel.resetModel()
        (context as MainActivity).setLoserText("")
        invalidate()
    }

    // Reveal All Flags
    fun revealFlag(canvas:Canvas){
        for (Row in 0..8){
            for (Col in 0..8){
                if ((context as MainActivity).isFlagModeOn() && MinesweeperModel.getFieldContent(Row, Col).isFlagged){
                    canvas.drawBitmap(bitmapDemoFlag,
                        (Row * 1f) * width / 9,
                        (Col* 1f) * height / 9,
                        null
                    )
                }
            }
        }
    }
}