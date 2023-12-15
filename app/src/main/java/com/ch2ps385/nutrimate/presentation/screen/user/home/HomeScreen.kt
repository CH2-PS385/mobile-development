package com.ch2ps385.nutrimate.presentation.screen.user.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.presentation.screen.user.menu.MenuScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
//    Box(
//        modifier = modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center,
//    ) {
//        Text("Home Screen")
//    }
    Column {
        Text(text = "Hello, Adi Prasetya")
        Text(text = "Want to make meal planner for today?")
//        HomeSection(
//
//        )
    }
}

@Composable
fun MenuOfTheDayRow(
//    listMenu : List<Menu>
    modifier: Modifier = Modifier,
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
//        items(listMenu, key )
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4
)
@Composable
fun MenuContentPreview() {

    HomeScreen()
}