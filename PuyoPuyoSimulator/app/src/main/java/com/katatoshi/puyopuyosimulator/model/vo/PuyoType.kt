package com.katatoshi.puyopuyosimulator.model.vo

/**
 * ぷよの種類を表す代数的データ型。
 */
sealed class PuyoType {
    data class ColoredPuyo(val puyoColor: PuyoColor) : PuyoType()
    object GarbagePuyo : PuyoType()
}
