package com.ch2ps385.nutrimate.presentation.ui.component.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1


@Composable
fun ProfileOption(
    modifier: Modifier = Modifier,
    icon : Painter,
    text: String,
    onClick : () -> Unit
){
    Row(
        modifier = modifier
            .padding(vertical = 4.dp)
            .clickable {
                onClick()
            },

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = modifier
                .widthIn(282.dp)
                .height(32.dp)
        ){
            Image(
//                painter = painterResource(id = R.drawable.baseline_person_24),
                painter = icon,
                contentDescription = null,
                modifier = modifier
                    .padding(end = 10.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                color = neutralColor1,
                modifier = modifier
                    .align(Alignment.CenterVertically),
            )
        }
        Image(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = null,
            modifier = modifier
                .align(Alignment.CenterVertically),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileOptionPreview() {
    NutriMateTheme {
        Surface {
            ProfileOption(
                icon = painterResource(id = R.drawable.baseline_person_24),
                text = "Personal Data",
                onClick = {}
            )
        }
    }
}