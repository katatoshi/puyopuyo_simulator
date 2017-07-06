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
                        if (!visitedSet.contains(rightCoordinate) && board[rightCoordinate.row][rightCoordinate.column]?.let { current == it } ?: false) {
                            visitedSet.add(rightCoordinate)
                            bfsQueue.offer(rightCoordinate)
                        }
                    }

                    // 上に連結しているか調べる
                    if (coordinate.row < 11) {
                        val topCoordinate = Coordinate(coordinate.row + 1, coordinate.column)
                        if (!visitedSet.contains(topCoordinate) && board[topCoordinate.row][topCoordinate.column]?.let { current == it } ?: false) {
                            visitedSet.add(topCoordinate)
                            bfsQueue.offer(topCoordinate)
                        }
                    }

                    // 左に連結しているか調べる
                    if (0 < coordinate.column) {
                        val leftCoordinate = Coordinate(coordinate.row, coordinate.column - 1)
                        if (!visitedSet.contains(leftCoordinate) && board[leftCoordinate.row][leftCoordinate.column]?.let { current == it } ?: false) {
                            visitedSet.add(leftCoordinate)
                            bfsQueue.offer(leftCoordinate)
                        }
                    }

                    // 下に連結しているか調べる
                    if (0 < coordinate.row) {
                        val bottomCoordinate = Coordinate(coordinate.row - 1, coordinate.column)
                        if (!visitedSet.contains(bottomCoordinate) && board[bottomCoordinate.row][bottomCoordinate.column]?.let { current == it } ?: false) {
                            visitedSet.add(bottomCoordinate)
                            bfsQueue.offer(bottomCoordinate)
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

    /**
     * 座標を表すデータクラス。
     */
    data class Coordinate(val row: Int, val column: Int)
}