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

    fun explode() {
        for (row in 0..11) {
            for (column in 0..5) {
                if (board[row][column] == null) {
                    continue
                }

                if (board[row][column] is PuyoType.OjamaPuyo) {
                    continue
                }

                //region BFS
                val rootCoordinate = Coordinate(row, column)
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

                if (visitedSet.size < 4) {
                    continue
                }

                for (coordinate in visitedSet) {
                    board[coordinate.row][coordinate.column] = null
                }
            }
        }
    }

    override fun toString(): String = puyoBoardToString(this)

    /**
     * 座標を表すデータクラス。
     */
    data class Coordinate(val row: Int, val column: Int)
}

fun String.toPuyoBoard(): PuyoBoard = stringToPuyoBoard(this)

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
