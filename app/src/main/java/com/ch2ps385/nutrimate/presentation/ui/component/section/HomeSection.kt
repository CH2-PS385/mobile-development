package com.ch2ps385.nutrimate.presentation.ui.component.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeSection(
    title : String,
    modifier : Modifier = Modifier,
    content : @Composable () -> Unit
){
    Column( modifier ) {
        SectionText(
            title = title, modifier
        )
        Spacer(modifier = modifier.height(16.dp))
        content()
    }
}