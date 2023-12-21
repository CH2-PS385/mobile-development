package com.ch2ps385.nutrimate.presentation.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.sBabyPink
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite

@Composable
fun GenderButtonPackage(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(120.dp)
            .height(56.dp)
            .padding(horizontal = 4.dp)
            .clip(Shapes.medium)
            .background(if (isSelected) pSmashedPumpkin else solidWhite)
            .border(
                width = 1.dp,
                shape = Shapes.medium,
                color = if (!isSelected) sBabyPink else pSmashedPumpkin
            )
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            color = if (isSelected) solidWhite else neutralColor1,
            style = MaterialTheme.typography.labelMedium
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GenderRadioButtonPreview() {
    NutriMateTheme {
        GenderButtonPackage(
            text = "Laki-Laki",
            isSelected = true,
            onClick = { /* Handle click action */ }
        )
    }
}