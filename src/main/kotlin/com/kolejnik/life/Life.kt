@file:JvmName("Life")

package com.kolejnik.life

import kotlin.random.Random

private const val WIDTH = 200
private const val HEIGHT = 150
private const val TIME_TICK_MS = 50L

val NEIGHBOURHOOD: IntRange = -1..1
const val ALIVE = 1
const val DEAD = 0
const val SCALE = 3
const val INJECTION_RADIUS = 20
const val INITIAL_DENSITY_LEVEL = 10

fun main() {
    val board = Board(WIDTH, HEIGHT)

    val initField: (Int, Int) -> Unit = { x, y ->
        board.getBoard()[x][y] = when (Random.nextInt(INITIAL_DENSITY_LEVEL)) {
            0 -> ALIVE
            else -> DEAD
        }
    }
    for2d(0 until WIDTH, 0 until HEIGHT, initField)

    val boardPreview = BoardPreview(board, "Game of Life", SCALE * WIDTH, SCALE * HEIGHT)
    boardPreview.open()

    while (true) {
        boardPreview.draw()
        board.nextGeneration()
        Thread.sleep(TIME_TICK_MS)
    }
}

fun scale(x: Int): Int {
    return x * SCALE
}

fun for2d(xRange: IntRange, yRange: IntRange, consumer: (x: Int, y: Int) -> Unit) {
    for (x in xRange) {
        for (y in yRange) {
            consumer(x, y)
        }
    }
}
