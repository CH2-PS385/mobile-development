package com.ch2ps385.nutrimate.presentation.ui.component.button

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite


@Composable
fun CustomCheckBoxButton(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    index: Int
) {
    var isClicked by remember { mutableStateOf(checked) }

    Button(
        onClick = {
            isClicked = !isClicked
            onCheckedChange(isClicked)

            Log.d("CustomCheckBoxButton", "Clicked on item at index $index with text: $text")
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = if (isClicked) solidWhite else pSmashedPumpkin,
            containerColor = if (isClicked) pSmashedPumpkin else solidWhite,
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
                color = if (isClicked) solidWhite else neutralColor1,
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
            index = 1
        )
    }
}
