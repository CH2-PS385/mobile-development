package com.ch2ps385.nutrimate.data

data class SignInResult(
    val data: UserData?,
    val errorMessage:String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)
