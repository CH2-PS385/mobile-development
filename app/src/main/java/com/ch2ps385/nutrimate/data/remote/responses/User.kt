package com.ch2ps385.nutrimate.data.remote.responses

data class User(
    val name : String,
    val token : String,
    val isLogin : Boolean
)