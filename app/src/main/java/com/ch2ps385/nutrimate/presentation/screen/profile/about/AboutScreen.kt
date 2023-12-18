package com.ch2ps385.nutrimate.presentation.screen.profile.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor3
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor6
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.sUnbleachedSilk

@Composable
fun AboutScreen(
    modifier : Modifier = Modifier,
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(neutralColor6)
    ) {
        Text(
            text = stringResource(id = R.string.nutrimate),
            style = MaterialTheme.typography.headlineSmall,
            color = neutralColor1
        )
        Text(
            text = stringResource(id = R.string.nutrimate_desc),
            style = MaterialTheme.typography.titleSmall,
            color = sUnbleachedSilk
        )
        Text(
            text = stringResource(id = R.string.version),
            style = MaterialTheme.typography.labelSmall,
            color = neutralColor3
        )
        Image(
            painter = painterResource(id = R.drawable.logo_orange),
            contentDescription = "logo orange",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .width(125.dp)
                .height(125.dp)
        )
        Text(
            text = stringResource(id = R.string.copyright),
            style = MaterialTheme.typography.labelSmall,
            color = neutralColor3
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AboutScreenPreview() {
    NutriMateTheme {
        AboutScreen()
    }
}