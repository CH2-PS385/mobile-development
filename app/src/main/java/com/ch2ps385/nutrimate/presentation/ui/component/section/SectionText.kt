package com.ch2ps385.nutrimate.presentation.ui.component.section

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1

@Composable
fun SectionText(
    title : String,
    modifier: Modifier = Modifier
){
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = neutralColor1,
        modifier = modifier
            .padding(top= 20.dp, bottom = 0.dp)
    )
}