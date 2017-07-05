package com.katatoshi.puyopuyosimulator.model.entity

import com.katatoshi.puyopuyosimulator.model.vo.PuyoColor
import com.katatoshi.puyopuyosimulator.model.vo.PuyoType

import org.junit.Assert.*
import org.junit.Test

class PuyoBoardTest {

    val r = PuyoType.ColoredPuyo(PuyoColor.RED);

    val g = PuyoType.ColoredPuyo(PuyoColor.GREEN);

    val b = PuyoType.ColoredPuyo(PuyoColor.BLUE);

    val y = PuyoType.ColoredPuyo(PuyoColor.YELLOW);

    val v = PuyoType.ColoredPuyo(PuyoColor.VIOLET);

    @Test
    fun 幽霊まですべて同じ色で埋まっているフィールドをexplodeすると幽霊以外のすべてのぷよが消える() {
        val testBoard = arrayOf(
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   )
        ).reversedArray()

        val expectedBoard = arrayOf(
                arrayOf<PuyoType?>(r   , r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null)
        ).reversedArray()

        val sut = PuyoBoard()

        for (row in 0..12) {
            for (column in 0..5) {
                sut.board[row][column] = testBoard[row][column]
            }
        }

        sut.explode()

        for (row in 0..12) {
            for (column in 0..5) {
                assertEquals(expectedBoard[row][column], sut.board[row][column])
            }
        }
    }

    @Test
    fun ぐねぐねした連結でもexplodeで消える() {
        val testBoard = arrayOf(
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, r   , r   , r   , null),
                arrayOf<PuyoType?>(null, null, null, null, r   , null),
                arrayOf<PuyoType?>(null, r   , r   , r   , r   , r   ),
                arrayOf<PuyoType?>(null, r   , g   , g   , g   , r   ),
                arrayOf<PuyoType?>(r   , r   , g   , r   , g   , r   ),
                arrayOf<PuyoType?>(null, r   , g   , r   , g   , r   ),
                arrayOf<PuyoType?>(null, r   , r   , r   , g   , r   ),
                arrayOf<PuyoType?>(null, null, r   , g   , g   , r   ),
                arrayOf<PuyoType?>(r   , r   , r   , g   , r   , r   ),
                arrayOf<PuyoType?>(r   , r   , g   , g   , r   , null),
                arrayOf<PuyoType?>(null, null, null, null, r   , null),
                arrayOf<PuyoType?>(null, r   , r   , r   , r   , null)
        ).reversedArray()

        val expectedBoard = arrayOf(
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null)
        ).reversedArray()

        val sut = PuyoBoard()

        for (row in 0..12) {
            for (column in 0..5) {
                sut.board[row][column] = testBoard[row][column]
            }
        }

        sut.explode()

        for (row in 0..12) {
            for (column in 0..5) {
                assertEquals(expectedBoard[row][column], sut.board[row][column])
            }
        }
    }

    @Test
    fun 連結していても4連結が一つもないなら何も消えない() {

        val testBoard = arrayOf(
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, null),
                arrayOf<PuyoType?>(null, null, null, null, null, v   ),
                arrayOf<PuyoType?>(null, null, null, null, v   , v   ),
                arrayOf<PuyoType?>(null, null, null, v   , y   , y   ),
                arrayOf<PuyoType?>(b   , r   , b   , y   , g   , y   ),
                arrayOf<PuyoType?>(b   , b   , r   , b   , b   , g   ),
                arrayOf<PuyoType?>(r   , r   , y   , b   , g   , g   )
        ).reversedArray()

        val expectedBoard = testBoard

        val sut = PuyoBoard()

        for (row in 0..12) {
            for (column in 0..5) {
                sut.board[row][column] = testBoard[row][column]
            }
        }

        sut.explode()

        for (row in 0..12) {
            for (column in 0..5) {
                assertEquals(expectedBoard[row][column], sut.board[row][column])
            }
        }
    }
}