package com.ch2ps38.nutrimate.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.atya.nutrimate.R


val interFont = FontFamily(
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_regular, FontWeight.Normal),
)

// Set of Material typography styles to start with
val Typography = Typography(
    // [ headline ] for Heading 1 - Heading 3
    headlineLarge = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 38.4.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,

    ),
    headlineSmall = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 38.4.sp,
    ),
    // End of Heading 3

    // [ title ] for Heading 4 - Heading 6
    titleLarge =  TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        lineHeight = 22.1.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 19.6.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.8.sp,
    ),
    // End of Heading 6

    // Regular [ Main Content ]
    bodyLarge = TextStyle(
        fontSize = 17.sp,
        lineHeight = 27.2.sp,
        fontFamily = interFont,
        fontWeight = FontWeight(400),
    ),
    // Regular [ Sub Content ]
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 22.4.sp,
        fontFamily = interFont,
        fontWeight = FontWeight(400),
    ),
    // Regular [ Tiny Content ]
    bodySmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 19.2.sp,
        fontFamily = interFont,
        fontWeight = FontWeight(400),
    ),

    // NOTED : for the smaller fontsize could be edited manually when developing the components,
    // Please using 10sp or 8sp for the smaller fontsize (check the figma for more detail)


    // Medium [ Main Content ]
    labelLarge = TextStyle(
        fontFamily = interFont,
        fontSize = 17.sp,
        lineHeight = 27.2.sp,
        fontWeight = FontWeight(500),
    ),

    // Medium [ Sub Content ]
    labelMedium = TextStyle(
        fontFamily = interFont,
        fontSize = 14.sp,
        lineHeight = 22.4.sp,
        fontWeight = FontWeight(500),
    ),

    // Medium [ Tiny Content ]
    labelSmall = TextStyle(
        fontFamily = interFont,
        fontSize = 14.sp,
        lineHeight = 22.4.sp,
        fontWeight = FontWeight(500),
    ),

    // NOTED : for the smaller fontsize could be edited manually when developing the components,
    // Please using 10sp or 8sp for the smaller fontsize (check the figma for more detail)

    // Button Large
    displayLarge = TextStyle(
        fontSize = 20.sp,
        lineHeight = 24.sp,
        fontFamily = interFont,
        fontWeight = FontWeight(600),
    ),

    // Button Medium
    displayMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = interFont,
        fontWeight = FontWeight(600),
    ),

    // Button Small
    displaySmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 24.sp,
        fontFamily = interFont,
        fontWeight = FontWeight(600),
    ),


)