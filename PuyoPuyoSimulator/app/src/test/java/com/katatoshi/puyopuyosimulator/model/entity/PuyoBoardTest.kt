package com.katatoshi.puyopuyosimulator.model.entity

import com.katatoshi.puyopuyosimulator.model.vo.ColorType
import com.katatoshi.puyopuyosimulator.model.vo.OjamaType
import com.katatoshi.puyopuyosimulator.model.vo.PuyoType
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue

import org.junit.Assert.*
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
class PuyoBoardTest {

    class explode関数のテスト {

        @Test
        fun ぷよが一つもないフィールドは何も消えない() {
            val test = """>|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |""".trimMargin(">")

            val expected = test

            val sut = test.toPuyoBoard()

            sut.explode()

            assertThat(sut.toString(), `is`(expected))
        }

        @Test
        fun 幽霊以外すべて同じ色で埋まっているフィールドはぷよが一つもなくなる() {
            val test = """>|            |
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

            val expected = """>|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |""".trimMargin(">")

            val sut = test.toPuyoBoard()

            sut.explode()

            assertThat(sut.toString(), `is`(expected))
        }

        @Test
        fun 幽霊まですべて同じ色で埋まっているフィールドは幽霊以外のぷよがなくなる() {
            val test = """>| R R R R R R|
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

            val expected = """>| R R R R R R|
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |""".trimMargin(">")

            val sut = test.toPuyoBoard()

            sut.explode()

            assertThat(sut.toString(), `is`(expected))
        }

        @Test
        fun 同じ色が右に巻いた形で連結していればぷよが一つもなくなる() {
            val test = """>|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >| R R R R R R|
                          >| R         R|
                          >| R   R R   R|
                          >| R   R     R|
                          >| R   R R R R|
                          >| R          |""".trimMargin(">")

            val expected = """>|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |""".trimMargin(">")

            val sut = test.toPuyoBoard()

            sut.explode()

            assertThat(sut.toString(), `is`(expected))
        }

        @Test
        fun 同じ色が左に巻いた形で連結していればぷよが一つもなくなる() {
            val test = """>|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >| R R R R R R|
                          >| R         R|
                          >| R   R R   R|
                          >| R     R   R|
                          >| R R R R   R|
                          >|           R|""".trimMargin(">")

            val expected = """>|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |""".trimMargin(">")

            val sut = test.toPuyoBoard()

            sut.explode()

            assertThat(sut.toString(), `is`(expected))
        }

        @Test
        fun 同じ色が十字に連結していればぷよが一つもなくなる() {
            val test = """>|            |
                          >|   R     R  |
                          >| R R R R R R|
                          >|   R     R  |
                          >|   R     R  |
                          >| R R R R R R|
                          >|   R     R  |
                          >|   R     R  |
                          >| R R R R R R|
                          >|   R     R  |
                          >|   R     R  |
                          >| R R R R R R|
                          >|   R     R  |""".trimMargin(">")

            val expected = """>|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |""".trimMargin(">")

            val sut = test.toPuyoBoard()

            sut.explode()

            assertThat(sut.toString(), `is`(expected))
        }

        @Test
        fun 連結していても4連結が一つもないなら何も消えない() {
            val test = """>|            |
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

            val expected = test

            val sut = test.toPuyoBoard()

            sut.explode()

            assertThat(sut.toString(), `is`(expected))
        }

        @Test
        fun 隣接しているおじゃまぷよも消える() {
            val test = """>|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >| O B O      |
                          >| O B O      |
                          >| O B O O    |
                          >| O B O O    |
                          >| O O O O    |
                          >| O O O O O O|
                          >| O O R G O O|
                          >| R R R G G G|""".trimMargin(">")

            val expected = """>|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|       O    |
                              >|       O    |
                              >| O   O O    |
                              >| O O     O O|
                              >|            |
                              >|            |""".trimMargin(">")

            val sut = test.toPuyoBoard()

            sut.explode()

            assertThat(sut.toString(), `is`(expected))
        }

        @Test
        fun 色ぷよは1つだけで隣接するおじゃまぷよが3つでも何も消えない() {
            val test = """>|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|   O        |
                          >| O R O      |""".trimMargin(">")

            val expected = test

            val sut = test.toPuyoBoard()

            sut.explode()

            assertThat(sut.toString(), `is`(expected))

        }

        @Test
        fun 何も消えないフィールドの発火ボーナスはnull() {
            val test = """>|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >| R G        |
                          >| R G        |
                          >| R G        |""".trimMargin(">")

            val sut = test.toPuyoBoard()

            val result = sut.explode()

            assertThat(result, `is`(nullValue()))
        }

        @Test
        fun 単色4連結1つならcountBonusは40でconnectionBonusは0でcolorBonusは0() {
            val test = """>|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >| R          |
                          >| R          |
                          >| R          |
                          >| R          |""".trimMargin(">")

            val expected = PuyoBoard.ExplosionBonus(40, 0, 0)

            val sut = test.toPuyoBoard()

            val result = sut.explode()

            assertThat(result?.countBonus, `is`(expected.countBonus))
            assertThat(result?.connectionBonus, `is`(expected.connectionBonus))
            assertThat(result?.colorBonus, `is`(expected.colorBonus))
        }

        @Test
        fun 単色8連結1つならcountBonusは80でconnectionBonusは5でcolorBonusは0() {
            val test = """>|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >| R R        |
                          >| R R        |
                          >| R R        |
                          >| R R        |""".trimMargin(">")

            val expected = PuyoBoard.ExplosionBonus(80, 5, 0)

            val sut = test.toPuyoBoard()

            val result = sut.explode()

            assertThat(result?.countBonus, `is`(expected.countBonus))
            assertThat(result?.connectionBonus, `is`(expected.connectionBonus))
            assertThat(result?.colorBonus, `is`(expected.colorBonus))
        }

        @Test
        fun 単色4連結2つならcountBonusは80でconnectionBonusは0でcolorBonusは0() {
            val test = """>|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >| R   R      |
                          >| R   R      |
                          >| R   R      |
                          >| R   R      |""".trimMargin(">")

            val expected = PuyoBoard.ExplosionBonus(80, 0, 0)

            val sut = test.toPuyoBoard()

            val result = sut.explode()

            assertThat(result?.countBonus, `is`(expected.countBonus))
            assertThat(result?.connectionBonus, `is`(expected.connectionBonus))
            assertThat(result?.colorBonus, `is`(expected.colorBonus))
        }

        @Test
        fun 二色4連結1つずつならcountBonusは80でconnectionBonusは0でcolorBonusは3() {
            val test = """>|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >| R G        |
                          >| R G        |
                          >| R G        |
                          >| R G        |""".trimMargin(">")

            val expected = PuyoBoard.ExplosionBonus(80, 0, 3)

            val sut = test.toPuyoBoard()

            val result = sut.explode()

            assertThat(result?.countBonus, `is`(expected.countBonus))
            assertThat(result?.connectionBonus, `is`(expected.connectionBonus))
            assertThat(result?.colorBonus, `is`(expected.colorBonus))
        }

        @Test
        fun 三色8連結1つずつならcountBonusは240でconnectionBonusは15でcolorBonusは6() {
            val test = """>|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >| R G B      |
                          >| R G B      |
                          >| R G B      |
                          >| R G B      |
                          >| R G B      |
                          >| R G B      |
                          >| R G B      |
                          >| R G B      |""".trimMargin(">")

            val expected = PuyoBoard.ExplosionBonus(240, 15, 6)

            val sut = test.toPuyoBoard()

            val result = sut.explode()

            assertThat(result?.countBonus, `is`(expected.countBonus))
            assertThat(result?.connectionBonus, `is`(expected.connectionBonus))
            assertThat(result?.colorBonus, `is`(expected.colorBonus))
        }

        @Test
        fun 単色4連結1つならおじゃまぷよが周囲にあってもcountBonusは40でconnectionBonusは0でcolorBonusは0() {
            val test = """>|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >|            |
                          >| O R R R R O|
                          >| O O O O O O|""".trimMargin(">")

            val expected = PuyoBoard.ExplosionBonus(40, 0, 0)

            val sut = test.toPuyoBoard()

            val result = sut.explode()

            assertThat(result?.countBonus, `is`(expected.countBonus))
            assertThat(result?.connectionBonus, `is`(expected.connectionBonus))
            assertThat(result?.colorBonus, `is`(expected.colorBonus))
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
        fun ぷよが一つもないフィールド() {
            val testString = """>|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |""".trimMargin(">")

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

            val sut = testString.toPuyoBoard()

            for (row in 0..12) {
                for (column in 0..5) {
                    assertEquals(expectedBoard[row][column], sut.board[row][column])
                }
            }
        }

        @Test
        fun ぷよが少なくとも一つあり幽霊以外で埋まっていない場所があるフィールド() {
            val testString = """>|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|            |
                                >|   G B      |
                                >| G B Y Y Y Y|
                                >| B G B B R R|
                                >| G G Y R B B|
                                >| B B B Y Y Y|
                                >| R R R B B B|""".trimMargin(">")

            val expectedBoard = arrayOf(
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, g   , b   , null, null, null),
                    arrayOf<PuyoType?>(g   , b   , y   , y   , y   , y   ),
                    arrayOf<PuyoType?>(b   , g   , b   , b   , r   , r   ),
                    arrayOf<PuyoType?>(g   , g   , y   , r   , b   , b   ),
                    arrayOf<PuyoType?>(b   , b   , b   , y   , y   , y   ),
                    arrayOf<PuyoType?>(r   , r   , r   , b   , b   , b   )
            ).reversedArray()

            val sut = testString.toPuyoBoard()

            for (row in 0..12) {
                for (column in 0..5) {
                    assertEquals(expectedBoard[row][column], sut.board[row][column])
                }
            }
        }

        @Test
        fun 幽霊以外すべて埋まっているフィールド() {
            val testString = """>|            |
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
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
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
        fun 幽霊も含めてすべて埋まっているフィールド() {
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
        fun すべての種類のぷよが存在するフィールド() {
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
            val testString = """>|            |
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
        fun ぷよが一つもないフィールド() {
            val testBoard = arrayOf(
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

            val expected = """>|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |""".trimMargin(">")

            val sut = PuyoBoard()

            for (row in 0..12) {
                for (column in 0..5) {
                    sut.board[row][column] = testBoard[row][column]
                }
            }

            assertThat(sut.toString(), `is`(expected))
        }

        @Test
        fun ぷよが少なくとも一つあり幽霊以外で埋まっていない場所があるフィールド() {
            val testBoard = arrayOf(
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
                    arrayOf<PuyoType?>(null, g   , b   , null, null, null),
                    arrayOf<PuyoType?>(g   , b   , y   , y   , y   , y   ),
                    arrayOf<PuyoType?>(b   , g   , b   , b   , r   , r   ),
                    arrayOf<PuyoType?>(g   , g   , y   , r   , b   , b   ),
                    arrayOf<PuyoType?>(b   , b   , b   , y   , y   , y   ),
                    arrayOf<PuyoType?>(r   , r   , r   , b   , b   , b   )
            ).reversedArray()

            val expected = """>|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|            |
                              >|   G B      |
                              >| G B Y Y Y Y|
                              >| B G B B R R|
                              >| G G Y R B B|
                              >| B B B Y Y Y|
                              >| R R R B B B|""".trimMargin(">")

            val sut = PuyoBoard()

            for (row in 0..12) {
                for (column in 0..5) {
                    sut.board[row][column] = testBoard[row][column]
                }
            }

            assertThat(sut.toString(), `is`(expected))
        }

        @Test
        fun 幽霊以外すべて埋まっているフィールド() {
            val testBoard = arrayOf(
                    arrayOf<PuyoType?>(null, null, null, null, null, null),
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

            val expected = """>|            |
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
        fun 幽霊も含めてすべて埋まっているフィールド() {
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
        fun すべての種類のぷよが存在するフィールド() {
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
    }
}