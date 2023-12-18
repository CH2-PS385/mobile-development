package com.ch2ps385.nutrimate.presentation.ui.component.button

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.pSinopia
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.sBabyPink
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite


@Composable
fun CustomCheckBoxButton(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    var isClicked by remember { mutableStateOf(!checked) }

    Button(
        onClick = {
            isClicked = !isClicked
            onCheckedChange(isClicked)
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = if (isClicked) pSmashedPumpkin else solidWhite,
            containerColor = if (isClicked) solidWhite else pSmashedPumpkin
        ),
        border = BorderStroke(
            width = 1.dp,
            color = pSmashedPumpkin
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = if (isClicked) neutralColor1 else solidWhite,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomCheckBoxButtonPreview() {
    NutriMateTheme {
        CustomCheckBoxButton(
            text = "Sample Text",
            checked = true,
            onCheckedChange = { /* Handle checked change */ },
        )
    }
}
