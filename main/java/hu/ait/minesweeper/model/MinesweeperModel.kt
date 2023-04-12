package hu.ait.minesweeper.model

import kotlin.random.Random

object MinesweeperModel {

    data class Field(var minesAround: Int, var isFlagged: Boolean, var wasClicked: Boolean, var bomb: Boolean)
    private lateinit var cell: Array<Array<Field>>

    // Initializes the game area by creating a 2D array of Field objects.
    // Each Field object represents a cell on the game board and has properties to store whether the cell has a bomb, whether it was clicked or flagged, and how many mines are adjacent to it.
    // The size parameter determines the dimensions of the game area.
    fun inItGameArea(size: Int) {
        cell = Array(size){ Array(size) {Field(0, false, false, false)} }
    }

    // Resets a single cell by setting its properties to their initial values.
    private fun resetCell(row: Int, col: Int) {
        cell[row][col].minesAround=0
        cell[row][col].isFlagged = false
        cell[row][col].wasClicked = false
        cell[row][col].bomb = false
    }

    // Resets the game board by calling resetCell on each cell and randomly allocating bombs to 10 cells.
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

    // Returns the Field object at the specified coordinates.
    fun getFieldContent(x: Int, y: Int) = cell[x][y]

    // Checks if the specified coordinates are within the boundaries of the game area.
    private fun isValid(row:Int, col:Int): Boolean {
        return (row in 0..8 && col in 0..8)
    }

    // Counts how many bombs are adjacent to the specified cell by checking the surrounding cells using isValid and incrementing a count variable.
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

    // Checks if all non-bomb cells have been clicked by looping through all cells and returning false if there are any non-clicked non-bomb cells.
    // Returns true if all non-bomb cells have been clicked, which means the player has won the game.
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

