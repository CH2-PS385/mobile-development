package com.ch2ps385.nutrimate.common

object Constants {
    const val BASE_URL = "https://ch2-ps385.et.r.appspot.com/v1/"

    val foodItems = listOf(
        "Daging Kerbau",
        "Beras",
        "Biji-bijian",
        "Buah",
        "Daging Ayam",
        "Daging Babi",
        "Daging Kambing",
        "Daging Sapi",
        "Ikan",
        "Kedelai",
        "Sayur",
        "Susu",
        "Telur Ayam",
        "Tepung",
        "Umbi-umbian"
    )

    val foodCategories = listOf("Breakfast", "Lunch", "Dinner", "Snack")

    enum class Gender {
        Male,
        Female
    }
}

const val NOTIFICATION_CHANNEL_ID = "notify-channel"