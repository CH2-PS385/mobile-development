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
        title = "Effortless Meal Planning",
        description = "With the Meal Planning feature, you can easily plan your daily meals. Customize your menu for each day and enjoy a healthy lifestyle with the right food choices"
    )


    object Second : OnBoardingPage(
        image = R.drawable.onboarding_healthyfood,
        title = "Discover Culinary Delights",
        description = "Embark on a gastronomic journey with our Explore Menu feature. Uncover a diverse range of nutritious and delicious dishes tailored to meet your dietary preferences, making every dining experience a delightful adventure"

    )

    object Third : OnBoardingPage(
        image = R.drawable.onboarding_hydration,
        title = "Hydration Habit Builder",
        description = "Discover your Balanced H2O Companion, ensuring you maintain optimal hydration levels throughout the day for improved wellness."
    )
}
