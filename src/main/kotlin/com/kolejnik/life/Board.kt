package com.kolejnik.life

import kotlin.random.Random

class Board(private var board: Array<IntArray>) {
    val width: Int = board.size
    val height: Int = board[0].size

    constructor(width: Int, height: Int) : this(Array(width) { IntArray(height) })

    fun nextGeneration() {
        val newBoard = newBoard()
        for2d(
            0 until width,
            0 until height
        ) { x, y ->
            newBoard[x][y] = nextState(x, y)
        }
        board = newBoard
    }

    fun randomInjection(x: Int, y: Int) {
        for2d(
            -INJECTION_RADIUS until INJECTION_RADIUS,
            -INJECTION_RADIUS until INJECTION_RADIUS
        ) { vX, vY ->
            if (Random.nextBoolean()) {
                board[x + vX][y + vY] = ALIVE
            }
        }
    }

    private fun nextState(x: Int, y: Int): Int {
        val currentState = board[x][y]
        val neighboursCount = countNeighbours(x, y)

        when (currentState) {
            ALIVE -> {
                if (listOf(2, 3).contains(neighboursCount)) {
                    return ALIVE
                }
            }
            DEAD -> {
                if (listOf(3).contains(neighboursCount)) {
                    return ALIVE
                }
            }
        }
        return DEAD
    }

    private fun countNeighbours(x: Int, y: Int): Int {
        var neighboursCount = 0
        for2d(NEIGHBOURHOOD, NEIGHBOURHOOD) { vX, vY ->
            if (!(vX == 0 && vY == 0) && isInsideBoard(x + vX, y + vY)) {
                neighboursCount += board[x + vX][y + vY]
            }
        }
        return neighboursCount
    }

    private fun isInsideBoard(x: Int, y: Int): Boolean {
        return x in 0 until width && y in 0 until height
    }

    private fun newBoard(): Array<IntArray> {
        return Array(width) { IntArray(height) }
    }

    fun getBoard(): Array<IntArray> {
        return board
    }

}