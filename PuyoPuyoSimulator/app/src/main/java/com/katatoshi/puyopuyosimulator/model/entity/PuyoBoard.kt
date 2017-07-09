package com.katatoshi.puyopuyosimulator.model.entity

import com.katatoshi.puyopuyosimulator.model.vo.ColorType
import com.katatoshi.puyopuyosimulator.model.vo.OjamaType
import com.katatoshi.puyopuyosimulator.model.vo.PuyoType
import java.util.*

/**
 * ぷよぷよのフィールドのエンティティ。
 */
class PuyoBoard {
    val board = (1..13).toList().map { arrayOfNulls<PuyoType>(6) }.toTypedArray()

    fun explode(): ExplosionBonus? {
        val connectionSizeList = mutableListOf<Int>()
        val colorTypeSet = mutableSetOf<ColorType>()

        for (row in 0..11) {
            for (column in 0..5) {
                val root = board[row][column]
                val rootColor = when (root) {
                    is PuyoType.ColoredPuyo -> root.colorType
                    else -> null
                }

                if (rootColor == null) {
                    continue
                }

                val rootCoordinate = Coordinate(row, column)
                val connectedSet = mutableSetOf(rootCoordinate)

                //region BFS
                val visitedSet = mutableSetOf(rootCoordinate)
                val bfsQueue = ArrayDeque(visitedSet)

                while (bfsQueue.isNotEmpty()) {
                    val coordinate = bfsQueue.poll()
                    val current = board[coordinate.row][coordinate.column]

                    require(current != null)

                    // 右に連結しているか調べる
                    if (coordinate.column < 5) {
                        val rightCoordinate = Coordinate(coordinate.row, coordinate.column + 1)
                        val right = board[rightCoordinate.row][rightCoordinate.column]
                        if (!visitedSet.contains(rightCoordinate) && right != null) {
                            when (right) {
                                current -> {
                                    visitedSet.add(rightCoordinate)
                                    connectedSet.add(rightCoordinate)
                                    bfsQueue.offer(rightCoordinate)
                                }
                                PuyoType.OjamaPuyo(OjamaType.OJAMA) -> {
                                    visitedSet.add(rightCoordinate)
                                }
                            }
                        }
                    }

                    // 上に連結しているか調べる
                    if (coordinate.row < 11) {
                        val topCoordinate = Coordinate(coordinate.row + 1, coordinate.column)
                        val top = board[topCoordinate.row][topCoordinate.column]
                        if (!visitedSet.contains(topCoordinate) && top != null) {
                            when (top) {
                                current -> {
                                    visitedSet.add(topCoordinate)
                                    connectedSet.add(topCoordinate)
                                    bfsQueue.offer(topCoordinate)
                                }
                                PuyoType.OjamaPuyo(OjamaType.OJAMA) -> {
                                    visitedSet.add(topCoordinate)
                                }
                            }
                        }
                    }

                    // 左に連結しているか調べる
                    if (0 < coordinate.column) {
                        val leftCoordinate = Coordinate(coordinate.row, coordinate.column - 1)
                        val left = board[leftCoordinate.row][leftCoordinate.column]
                        if (!visitedSet.contains(leftCoordinate) && left != null) {
                            when (left) {
                                current -> {
                                    visitedSet.add(leftCoordinate)
                                    connectedSet.add(leftCoordinate)
                                    bfsQueue.offer(leftCoordinate)
                                }
                                PuyoType.OjamaPuyo(OjamaType.OJAMA) -> {
                                    visitedSet.add(leftCoordinate)
                                }
                            }
                        }
                    }

                    // 下に連結しているか調べる
                    if (0 < coordinate.row) {
                        val bottomCoordinate = Coordinate(coordinate.row - 1, coordinate.column)
                        val bottom = board[bottomCoordinate.row][bottomCoordinate.column]
                        if (!visitedSet.contains(bottomCoordinate) && bottom != null) {
                            when (bottom) {
                                current -> {
                                    visitedSet.add(bottomCoordinate)
                                    connectedSet.add(bottomCoordinate)
                                    bfsQueue.offer(bottomCoordinate)
                                }
                                PuyoType.OjamaPuyo(OjamaType.OJAMA) -> {
                                    visitedSet.add(bottomCoordinate)
                                }
                            }
                        }
                    }
                }
                //endregion

                if (connectedSet.size < 4) {
                    continue
                }

                connectionSizeList.add(connectedSet.size)
                colorTypeSet.add(rootColor)

                for (coordinate in visitedSet) {
                    board[coordinate.row][coordinate.column] = null
                }
            }
        }

        return if (0 < connectionSizeList.size && 0 < colorTypeSet.size) {
            ExplosionBonus(
                    countBonus = connectionSizeList.sum() * 10,
                    connectionBonus = connectionSizeList.map(::connectionBonusTable).sum(),
                    colorBonus = colorBonusTable(colorTypeSet.size)
            )
        } else {
            null
        }
    }

    override fun toString(): String = puyoBoardToString(this)

    /**
     * 座標を表すデータクラス。
     */
    data class Coordinate(val row: Int, val column: Int)

    /**
     * 一回の発火のボーナスを表すデータクラス。
     */
    data class ExplosionBonus(val countBonus: Int, val connectionBonus: Int, val colorBonus: Int)
}

fun String.toPuyoBoard(): PuyoBoard = stringToPuyoBoard(this)

/**
 * 連結ボーナス表。複数色で連結がある場合や、同色で複数の連結がある場合は、それぞれの連結に対してこの表を適用し、最後に合計をとる。
 */
private fun connectionBonusTable(connectionSize: Int): Int = when {
    connectionSize ==  2 ->  0
    connectionSize ==  3 ->  0
    connectionSize ==  4 ->  0
    connectionSize ==  5 ->  2
    connectionSize ==  6 ->  3
    connectionSize ==  7 ->  4
    connectionSize ==  8 ->  5
    connectionSize ==  9 ->  6
    connectionSize == 10 ->  7
    connectionSize >= 11 -> 10
    else -> throw IllegalStateException("connectionSize の範囲は 2 <= connectionSize です。")
}

/**
 * 色数ボーナス表。
 */
private fun colorBonusTable(countColorType: Int): Int = when (countColorType) {
    1 ->  0
    2 ->  3
    3 ->  6
    4 -> 12
    5 -> 24
    else -> throw IllegalStateException("countColorType の範囲は 1 <= countColorType <= 5 です。")
}

private fun puyoBoardToString(puyoBoard: PuyoBoard): String =
        puyoBoard.board.reversedArray().map {
            it.fold("|") { acc, puyoType ->
                if (puyoType == null) {
                    acc + "  "
                } else {
                    acc + when (puyoType) {
                        is PuyoType.ColoredPuyo -> when (puyoType.colorType) {
                            ColorType.RED -> " R"
                            ColorType.GREEN -> " G"
                            ColorType.BLUE -> " B"
                            ColorType.YELLOW -> " Y"
                            ColorType.VIOLET -> " V"
                        }
                        is PuyoType.OjamaPuyo -> when (puyoType.ojamaType) {
                            OjamaType.OJAMA -> " O"
                            OjamaType.POINT -> " P"
                            OjamaType.HARD -> " H"
                            OjamaType.IRON -> " I"
                        }
                    }
                }
            } + "|"
        }.fold("") { acc, line ->
            if (acc.isEmpty()) line else acc + "\n" + line
        }

private fun stringToPuyoBoard(string: String): PuyoBoard {
    val lineList = string.split('\n').reversed()

    if (lineList.size != 13) {
        throw IllegalStateException()
    }

    if (lineList.any { it.length != 6 * 2 + 2 }) {
        throw IllegalStateException()
    }

    val parsedBoard = lineList.map { line ->
        val body = line.substring(1, line.length - 1)
        (0..5).toList().map {
            body.substring(2 * it, 2 * it + 2)
        }.map {
            when (it) {
                "  " -> null
                " R" -> PuyoType.ColoredPuyo(ColorType.RED)
                " G" -> PuyoType.ColoredPuyo(ColorType.GREEN)
                " B" -> PuyoType.ColoredPuyo(ColorType.BLUE)
                " Y" -> PuyoType.ColoredPuyo(ColorType.YELLOW)
                " V" -> PuyoType.ColoredPuyo(ColorType.VIOLET)
                " O" -> PuyoType.OjamaPuyo(OjamaType.OJAMA)
                " P" -> PuyoType.OjamaPuyo(OjamaType.POINT)
                " H" -> PuyoType.OjamaPuyo(OjamaType.HARD)
                " I" -> PuyoType.OjamaPuyo(OjamaType.IRON)
                else -> throw IllegalStateException()
            }
        }
    }

    val puyoBoard = PuyoBoard()

    for (row in 0..12) {
        for (column in 0..5) {
            puyoBoard.board[row][column] = parsedBoard[row][column]
        }
    }

    return puyoBoard
}
