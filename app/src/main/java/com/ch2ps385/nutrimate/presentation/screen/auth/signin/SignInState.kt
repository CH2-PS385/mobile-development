package com.ch2ps385.nutrimate.presentation.screen.auth.signin

data class SignInState(
    val isSignInSuccessful : Boolean = false,
    val signInError: String? = null
)
