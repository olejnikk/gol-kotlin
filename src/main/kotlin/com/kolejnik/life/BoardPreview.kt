package com.kolejnik.life

import java.awt.Color
import java.awt.Dimension
import java.awt.EventQueue
import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JFrame
import javax.swing.JPanel

class BoardPreview(private val life: Life, title: String, width: Int, height: Int) : JFrame() {

    private val boardPane = BoardPane(life)

    init {
        configureFrame(title, width, height)
    }

    fun open() {
        EventQueue.invokeLater { display() }
    }

    fun draw() {
        boardPane.repaint()
    }

    private fun configureFrame(title: String, width: Int, height: Int) {
        setTitle(title)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(width, height)
        setLocationRelativeTo(null)
        boardPane.addMouseListener(AddCellMouseListener(life))
        add(boardPane)
    }

    private fun display() {
        this.isVisible = true
    }

    class BoardPane(private val life: Life) : JPanel() {
        private val backgroundColor = Color.WHITE
        private val cellColor = Color.BLACK

        override fun getPreferredSize(): Dimension {
            return Dimension(scale(life.width), scale(life.height))
        }

        override fun paintComponent(g: Graphics?) {
            super.paintComponent(g)
            clear(g)
            drawBoard(g)
        }

        private fun drawBoard(g: Graphics?) {
            g?.color = cellColor
            for2d(0 until life.width, 0 until life.height) { x, y ->
                if (life.getBoard()[x][y] == ALIVE) {
                    g?.fillRect(scale(x), scale(y), pixelSize(), pixelSize())
                }
            }
        }

        private fun clear(g: Graphics?) {
            g?.color = backgroundColor
            g?.fillRect(0, 0, scale(width), scale(height))
        }

        private fun pixelSize(): Int {
            return (scale(1) - 1).coerceAtLeast(1)
        }
    }

    class AddCellMouseListener(private val life: Life) : MouseListener {
        override fun mouseClicked(e: MouseEvent?) {
            life.randomInjection(e!!.x.div(SCALE), e.y.div(SCALE))
        }

        override fun mouseReleased(e: MouseEvent?) {
            return
        }

        override fun mouseEntered(e: MouseEvent?) {
            return
        }

        override fun mouseExited(e: MouseEvent?) {
            return
        }

        override fun mousePressed(e: MouseEvent?) {
            return
        }
    }

}
