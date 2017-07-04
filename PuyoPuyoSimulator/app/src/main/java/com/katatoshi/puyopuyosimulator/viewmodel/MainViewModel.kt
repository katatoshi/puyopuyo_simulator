package com.katatoshi.puyopuyosimulator.viewmodel

import android.databinding.ObservableField

class MainViewModel {

    val text: ObservableField<String> = ObservableField("ぷよぷよシミュレーター")

    private var counter = 1

    fun onResume() {
        text.set((1..counter).toList().fold("") { acc, _ -> acc + "ぷよぷよ" } + "シミュレーター")

        counter++
    }
}

