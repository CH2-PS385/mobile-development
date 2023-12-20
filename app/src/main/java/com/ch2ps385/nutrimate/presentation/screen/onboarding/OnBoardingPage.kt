package com.ch2ps385.nutrimate.presentation.screen.onboarding

import androidx.annotation.DrawableRes
import com.ch2ps385.nutrimate.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title :String,
    val description : String
){
    object First: OnBoardingPage(
        image = R.drawable.onboarding_mealplanning,
        title = "Meeting",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )


    object Second : OnBoardingPage(
        image = R.drawable.onboarding_hydration,
        title = "Coordination",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Third : OnBoardingPage(
        image = R.drawable.onboarding_mealplanning,
        title = "Dialogue",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )
}
