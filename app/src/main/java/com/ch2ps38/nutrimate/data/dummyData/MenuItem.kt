package com.ch2ps38.nutrimate.data.dummyData

import com.atya.nutrimate.R

data class Menu(
    val image: Int,
    val title: String,
    val calories: String,
    val foodType: String,
    val foodMEthod:String,
)

val dummyMenu = listOf(
    Menu(R.drawable.menu1, "Tiramisu Coffee Milk", "225 cal", "Real food", "Baked"),
)
