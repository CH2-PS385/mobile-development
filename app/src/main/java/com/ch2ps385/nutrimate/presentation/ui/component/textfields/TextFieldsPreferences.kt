package com.ch2ps385.nutrimate.presentation.ui.component.textfields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.interFont
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor4

@Composable
fun TextFieldsPreferences(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    unit: String,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
//            .fillMaxWidth()
            .width(275.dp)
            .height(40.dp)
    ){
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = modifier
                .weight(4f)
                .height(40.dp)
                .clip(Shapes.medium),
            textStyle = TextStyle(
                fontSize = 12.sp,
                lineHeight = 19.2.sp,
                fontFamily = interFont,
                fontWeight = FontWeight(400),
                color = neutralColor1
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier // margin left and right
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = neutralColor4,
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = MaterialTheme.typography.bodySmall,
                            color = neutralColor4
                        )
                    }
                    innerTextField()
                }
            }
        )
        Box(
            modifier = modifier
                .weight(1f)
//                .widthIn(80.dp)
                .height(40.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = unit,
                style = MaterialTheme.typography.labelSmall,
                color = neutralColor1,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TextFieldsPreferencesPreview() {
    var value by remember { mutableStateOf("Initial Value") }

    TextFieldsPreferences(
        placeholder = "Enter Value",
        value = value,
        onValueChange = { newValue -> value = newValue },
        unit = "cm",
    )
}