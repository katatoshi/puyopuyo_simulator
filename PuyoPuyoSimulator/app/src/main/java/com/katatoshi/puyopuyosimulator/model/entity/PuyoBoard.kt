package com.katatoshi.puyopuyosimulator.model.entity

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

                val candidateSet = mutableSetOf<Coordinate>()

                //region BFS
                val rootCoordinate = Coordinate(row, column)
                val bfsQueue = ArrayDeque(mutableListOf(rootCoordinate))
                while (bfsQueue.isNotEmpty()) {
                    val coordinate = bfsQueue.poll()
                    val current = board[coordinate.row][coordinate.column]

                    require(current != null)

                    candidateSet.add(coordinate)

                    // 右
                    if (coordinate.column < 5
                            && !candidateSet.contains(Coordinate(coordinate.row, coordinate.column + 1))
                            && board[coordinate.row][coordinate.column + 1]?.let { current == it } ?: false) {
                        bfsQueue.offer(Coordinate(coordinate.row, coordinate.column + 1))
                    }

                    // 上
                    if (coordinate.row < 11
                            && !candidateSet.contains(Coordinate(coordinate.row + 1, coordinate.column))
                            && board[coordinate.row + 1][coordinate.column]?.let { current == it } ?: false) {
                        bfsQueue.offer(Coordinate(coordinate.row + 1, coordinate.column))
                    }

                    // 左
                    if (0 < coordinate.column
                            && !candidateSet.contains(Coordinate(coordinate.row, coordinate.column - 1))
                            && board[coordinate.row][coordinate.column - 1]?.let { current == it } ?: false) {
                        bfsQueue.offer(Coordinate(coordinate.row, coordinate.column - 1))
                    }

                    // 下
                    if (0 < coordinate.row
                            && !candidateSet.contains(Coordinate(coordinate.row - 1, coordinate.column))
                            && board[coordinate.row - 1][coordinate.column]?.let { current == it } ?: false) {
                        bfsQueue.offer(Coordinate(coordinate.row - 1, coordinate.column))
                    }
                }
                //endregion

                if (candidateSet.size < 4) {
                    continue
                }

                for (coordinate in candidateSet) {
                    board[coordinate.row][coordinate.column] = null
                }
            }
        }
    }

    data class Coordinate(val row: Int, val column: Int)
}