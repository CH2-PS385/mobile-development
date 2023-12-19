package com.ch2ps385.nutrimate.data.remote.model

data class AddUserPreferences(
    val email: String,
    val height: Int,
    val weight: Int,
    val age: Int,
    val gender: String
)
