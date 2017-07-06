package com.katatoshi.puyopuyosimulator.model.vo

/**
 * ぷよの種類を表す代数的データ型。
 */
sealed class PuyoType {
    data class ColoredPuyo(val colorType: ColorType) : PuyoType()
    data class OjamaPuyo(val ojamaType: OjamaType) : PuyoType()
}
