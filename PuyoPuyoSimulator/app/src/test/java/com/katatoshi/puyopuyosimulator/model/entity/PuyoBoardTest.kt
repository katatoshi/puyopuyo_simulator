package com.katatoshi.puyopuyosimulator.model.entity

import com.katatoshi.puyopuyosimulator.model.vo.ColorType
import com.katatoshi.puyopuyosimulator.model.vo.OjamaType
import com.katatoshi.puyopuyosimulator.model.vo.PuyoType
import org.hamcrest.CoreMatchers.`is`

import org.junit.Assert.*
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
class PuyoBoardTest {

    class toStringのテスト {

        val r = PuyoType.ColoredPuyo(ColorType.RED)

        val g = PuyoType.ColoredPuyo(ColorType.GREEN)

        val b = PuyoType.ColoredPuyo(ColorType.BLUE)

        val y = PuyoType.ColoredPuyo(ColorType.YELLOW)

        val v = PuyoType.ColoredPuyo(ColorType.VIOLET)

        val o = PuyoType.OjamaPuyo(OjamaType.OJAMA)

        val p = PuyoType.OjamaPuyo(OjamaType.POINT)

        val h = PuyoType.OjamaPuyo(OjamaType.HARD)

        val i = PuyoType.OjamaPuyo(OjamaType.IRON)

        @Test
        fun すべての種類のぷよが存在するフィールドをtoString() {
            val testBoard = arrayOf(
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, p   ),
                    arrayOf<PuyoType?>(null, h   , null, null, p   , v   ),
                    arrayOf<PuyoType?>(g   , g   , h   , h   , v   , v   ),
                    arrayOf<PuyoType?>(o   , o   , o   , v   , y   , y   ),
                    arrayOf<PuyoType?>(b   , r   , b   , y   , g   , y   ),
                    arrayOf<PuyoType?>(b   , b   , r   , b   , b   , g   ),
                    arrayOf<PuyoType?>(r   , r   , i   , b   , g   , g   )
            ).reversedArray()

            val expected = """>|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|           P|
                              >|   H     P V|
                              >| G G H H V V|
                              >| O O O V Y Y|
                              >| B R B Y G Y|
                              >| B B R B B G|
                              >| R R I B G G|""".trimMargin(">")

            val sut = PuyoBoard()

            for (row in 0..12) {
                for (column in 0..5) {
                    sut.board[row][column] = testBoard[row][column]
                }
            }

            assertThat(sut.toString(), `is`(expected))
        }

        @Test
        fun 幽霊まですべて同じ色で埋まっているフィールドをtoString() {
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

            val expected = """>| R R R R R R|
                              >| R R R R R R|
                              >| R R R R R R|
                              >| R R R R R R|
                              >| R R R R R R|
                              >| R R R R R R|
                              >| R R R R R R|
                              >| R R R R R R|
                              >| R R R R R R|
                              >| R R R R R R|
                              >| R R R R R R|
                              >| R R R R R R|
                              >| R R R R R R|""".trimMargin(">")

            val sut = PuyoBoard()

            for (row in 0..12) {
                for (column in 0..5) {
                    sut.board[row][column] = testBoard[row][column]
                }
            }

            assertThat(sut.toString(), `is`(expected))
        }

        @Test
        fun ぐねぐねした連結をtoString() {
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

            val expected = """>|            |
                              >|     R R R  |
                              >|         R  |
                              >|   R R R R R|
                              >|   R G G G R|
                              >| R R G R G R|
                              >|   R G R G R|
                              >|   R R R G R|
                              >|     R G G R|
                              >| R R R G R R|
                              >| R R G G R  |
                              >|         R  |
                              >|   R R R R  |""".trimMargin(">")

            val sut = PuyoBoard()

            for (row in 0..12) {
                for (column in 0..5) {
                    sut.board[row][column] = testBoard[row][column]
                }
            }

            assertThat(sut.toString(), `is`(expected))
        }

        @Test
        fun GTRをtoString() {
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

            val expected = """>|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|           V|
                              >|         V V|
                              >|       V Y Y|
                              >| B R B Y G Y|
                              >| B B R B B G|
                              >| R R Y B G G|""".trimMargin(">")

            val sut = PuyoBoard()

            for (row in 0..12) {
                for (column in 0..5) {
                    sut.board[row][column] = testBoard[row][column]
                }
            }

            assertThat(sut.toString(), `is`(expected))
        }
    }

    class toPuyoBoardのテスト {

        val r = PuyoType.ColoredPuyo(ColorType.RED)

        val g = PuyoType.ColoredPuyo(ColorType.GREEN)

        val b = PuyoType.ColoredPuyo(ColorType.BLUE)

        val y = PuyoType.ColoredPuyo(ColorType.YELLOW)

        val v = PuyoType.ColoredPuyo(ColorType.VIOLET)

        val o = PuyoType.OjamaPuyo(OjamaType.OJAMA)

        val p = PuyoType.OjamaPuyo(OjamaType.POINT)

        val h = PuyoType.OjamaPuyo(OjamaType.HARD)

        val i = PuyoType.OjamaPuyo(OjamaType.IRON)

        @Test
        fun すべての種類のぷよが存在するフィールド文字列をtoPuyoBoard() {
            val testString = """>|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|           P|
                                >|   H     P V|
                                >| G G H H V V|
                                >| O O O V Y Y|
                                >| B R B Y G Y|
                                >| B B R B B G|
                                >| R R I B G G|""".trimMargin(">")

            val expectedBoard = arrayOf(
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, p   ),
                    arrayOf<PuyoType?>(null, h   , null, null, p   , v   ),
                    arrayOf<PuyoType?>(g   , g   , h   , h   , v   , v   ),
                    arrayOf<PuyoType?>(o   , o   , o   , v   , y   , y   ),
                    arrayOf<PuyoType?>(b   , r   , b   , y   , g   , y   ),
                    arrayOf<PuyoType?>(b   , b   , r   , b   , b   , g   ),
                    arrayOf<PuyoType?>(r   , r   , i   , b   , g   , g   )
            ).reversedArray()

            val sut = testString.toPuyoBoard()

            for (row in 0..12) {
                for (column in 0..5) {
                    assertEquals(expectedBoard[row][column], sut.board[row][column])
                }
            }
        }

        @Test
        fun 幽霊まですべて同じ色で埋まっているフィールド文字列をtoPuyoBoard() {
            val testString = """>| R R R R R R|
                                >| R R R R R R|
                                >| R R R R R R|
                                >| R R R R R R|
                                >| R R R R R R|
                                >| R R R R R R|
                                >| R R R R R R|
                                >| R R R R R R|
                                >| R R R R R R|
                                >| R R R R R R|
                                >| R R R R R R|
                                >| R R R R R R|
                                >| R R R R R R|""".trimMargin(">")

            val expectedBoard = arrayOf(
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

            val sut = testString.toPuyoBoard()

            for (row in 0..12) {
                for (column in 0..5) {
                    assertEquals(expectedBoard[row][column], sut.board[row][column])
                }
            }
        }

        @Test
        fun ぐねぐねした連結のフィールド文字列をtoPuyoBoard() {
            val testString =
                    """>|            |
                       >|     R R R  |
                       >|         R  |
                       >|   R R R R R|
                       >|   R G G G R|
                       >| R R G R G R|
                       >|   R G R G R|
                       >|   R R R G R|
                       >|     R G G R|
                       >| R R R G R R|
                       >| R R G G R  |
                       >|         R  |
                       >|   R R R R  |""".trimMargin(">")

            val expectedBoard = arrayOf(
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

            val sut = testString.toPuyoBoard()

            for (row in 0..12) {
                for (column in 0..5) {
                    assertEquals(expectedBoard[row][column], sut.board[row][column])
                }
            }
        }

        @Test
        fun GTRのフィールド文字列をtoPuyoBoard() {
            val testString = """>|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|           V|
                                >|         V V|
                                >|       V Y Y|
                                >| B R B Y G Y|
                                >| B B R B B G|
                                >| R R Y B G G|""".trimMargin(">")

            val expectedBoard = arrayOf(
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

            val sut = testString.toPuyoBoard()

            for (row in 0..12) {
                for (column in 0..5) {
                    assertEquals(expectedBoard[row][column], sut.board[row][column])
                }
            }
        }
    }

    class explode関数のテスト {

        val r = PuyoType.ColoredPuyo(ColorType.RED)

        val g = PuyoType.ColoredPuyo(ColorType.GREEN)

        val b = PuyoType.ColoredPuyo(ColorType.BLUE)

        val y = PuyoType.ColoredPuyo(ColorType.YELLOW)

        val v = PuyoType.ColoredPuyo(ColorType.VIOLET)

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
}