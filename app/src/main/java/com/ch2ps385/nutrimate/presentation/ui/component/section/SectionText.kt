package com.ch2ps385.nutrimate.presentation.ui.component.section

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SectionText(
    title : String,
    modifier: Modifier = Modifier
){
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
            .padding(top= 20.dp, start = 25.dp, end = 25.dp, bottom = 0.dp)
    )
}