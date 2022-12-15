package hu.ait.minesweeper.model

import kotlin.random.Random

object MinesweeperModel {

    data class Field(var minesAround: Int, var isFlagged: Boolean, var wasClicked: Boolean, var bomb: Boolean)
    private lateinit var cell: Array<Array<Field>>

    fun inItGameArea(size: Int) {
        cell = Array(size){ Array(size) {Field(0, false, false, false)} }
    }

    private fun resetCell(row: Int, col: Int) {
        cell[row][col].minesAround=0
        cell[row][col].isFlagged = false
        cell[row][col].wasClicked = false
        cell[row][col].bomb = false
    }

    fun resetModel() {
        // reset all cells
        for (i in 0..8) {
            for (j in 0..8) {
                resetCell(i, j)
            }
        }
        // randomly allocate bombs
        var mines = 0
        while (mines<10){
            val randomValues = Random.nextInt(0, 81)
            val row = randomValues/9
            val column = randomValues%9

            if (!cell[row][column].bomb) {
                cell[row][column].bomb = true
                mines++
            }
        }
    }

    fun getFieldContent(x: Int, y: Int) = cell[x][y]

    private fun isValid(row:Int, col:Int): Boolean {
        return (row in 0..8 && col in 0..8)
    }

    fun countAdjacentMines(row:Int, col:Int) {
        var count = 0

        //north
        if (isValid (row-1, col))
        {
            if (cell[row-1][col].bomb )
                count++
        }
        //south
        if (isValid (row+1, col))
        {
            if (cell[row+1][col].bomb )
                count++
        }
        //east
        if (isValid (row, col+1))
        {
            if (cell[row][col+1].bomb )
                count++
        }
        //east
        if (isValid (row, col-1))
        {
            if (cell[row][col-1].bomb )
                count++
        }
        //north east
        if (isValid (row-1, col+1))
        {
            if (cell[row-1][col+1].bomb )
                count++
        }
        //north west
        if (isValid (row-1, col-1))
        {
            if (cell[row-1][col-1].bomb )
                count++
        }
        //south east
        if (isValid (row+1, col+1))
        {
            if (cell[row+1][col+1].bomb )
                count++
        }
        //south west
        if (isValid (row+1, col-1))
        {
            if (cell[row+1][col-1].bomb )
                count++
        }
        cell[row][col].minesAround = count
    }

    fun winnerCheck():Boolean{
        for (i in 0..8){
            for (j in 0..8){
                if(!cell[i][j].wasClicked && !cell[i][j].bomb)
                    return false
            }
        }
        return true
    }
}//MinesweeperModel

