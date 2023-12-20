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
        title = "Explore Nutritious Menus",
        description = "Maintain your body's health by managing and tracking your daily water intake. The Water Intake feature helps you ensure your body stays hydrated at all times"
    )

    object Third : OnBoardingPage(
        image = R.drawable.onboarding_hydration,
        title = "Hydration Habit Builder",
        description = "Discover your Balanced H2O Companion, ensuring you maintain optimal hydration levels throughout the day for improved wellness."
    )
}
