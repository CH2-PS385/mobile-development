package com.ch2ps38.nutrimate.ui.component.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.atya.nutrimate.R
import com.ch2ps38.nutrimate.ui.theme.NutriMateTheme

@Composable
fun ProfileOption(
    modifier: Modifier = Modifier,
){
    Row(
        modifier = modifier
            .padding(vertical = 4.dp)

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = modifier
                .widthIn(282.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = null,
                modifier = modifier
                    .padding(end = 10.dp)
            )
            Text(
                text = "Personal Data",
                style = MaterialTheme.typography.titleSmall,
                modifier = modifier
                    .align(Alignment.CenterVertically),
            )
        }
        Image(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileOptionPreview() {
    NutriMateTheme {
        Surface {
            ProfileOption()
        }
    }
}