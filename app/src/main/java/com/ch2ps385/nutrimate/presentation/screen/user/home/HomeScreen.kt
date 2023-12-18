package com.ch2ps385.nutrimate.presentation.screen.user.home

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.UserData
import com.ch2ps385.nutrimate.data.remote.responses.DataItem
import com.ch2ps385.nutrimate.data.remote.responses.DataRecommendation
import com.ch2ps385.nutrimate.di.Injection
import com.ch2ps385.nutrimate.presentation.screen.user.UserViewModelFactory
import com.ch2ps385.nutrimate.presentation.ui.component.carditem.CardMenuItemGrid
import com.ch2ps385.nutrimate.presentation.ui.component.carditem.CardMenuItemList
import com.ch2ps385.nutrimate.presentation.ui.component.other.CircularProgressAnimated
import com.ch2ps385.nutrimate.presentation.ui.component.other.ReminderBanner
import com.ch2ps385.nutrimate.presentation.ui.component.section.HomeSection
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen
import com.ch2ps385.nutrimate.presentation.ui.theme.interFont
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin

@Composable
fun HomeScreen(
    userData: UserData?,
    viewModel : HomeViewModel = viewModel(
        factory = UserViewModelFactory(Injection.provideUserRepository(LocalContext.current))
    ),
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = modifier.height(33.dp))
        Text(
            text = stringResource(id = R.string.greeting_user, userData?.username.toString()),
            style = MaterialTheme.typography.headlineMedium,
            color = neutralColor1,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = neutralColor1,
                    fontFamily = interFont,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500)
                )) {
                    append("Want to ")
                }

                withStyle(style = SpanStyle(
                    color = pSmashedPumpkin,
                    fontFamily = interFont,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500)
                )) {
                    append("make meal planner ")
                }

                withStyle(style = SpanStyle(
                    color = neutralColor1,
                    fontFamily = interFont,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500)
                )) {
                    append("for ")
                }

                withStyle(style = SpanStyle(
                    color = pSmashedPumpkin,
                    fontFamily = interFont,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500)
                )) {
                    append("today?")
                }

            },
            modifier = Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.labelMedium,
            color = neutralColor1,
        )
//        MultipleStylesInText()
        HomeSection(
            title = "Menu of The Day",
            modifier = modifier.align(Alignment.Start)
        ) {
            LaunchedEffect(viewModel) {
                viewModel.getAllMenu()
            }
            viewModel.stateDataItem.collectAsState(initial = Resource.Loading()).value.let { state ->
                when(state){
                    is Resource.Loading -> {
//                    CustomLinearProgressBar()
                        CircularProgressAnimated()
                    }
                    is Resource.Success -> {
                        state.data?.let {
                            state.data?.let { MenuOfTheDayRow(menuList = it, navController = navController) }
                        }
                    }
                    is Resource.Error -> {
                        Log.d(ContentValues.TAG, "Tidak dapat mmenampilkan data!")
                    }
                    else -> {}
                }
            }
        }
        HomeSection(
            title = "Today's Plans",
            modifier = modifier.align(Alignment.Start)
        ) {
            LaunchedEffect(viewModel) {
                viewModel.getRecommendationMeal()
            }
            viewModel.stateDataRecommendation.collectAsState(initial = Resource.Loading()).value.let { state ->
                when(state){
                    is Resource.Loading -> {
//                    CustomLinearProgressBar()
                        CircularProgressAnimated()
                    }
                    is Resource.Success -> {
                        state.data?.let {
                            state.data?.let { TodayPlanner(menuList = it, navController = navController ) }
                        }
                    }
                    is Resource.Error -> {
                        Log.d(ContentValues.TAG, "Tidak dapat mmenampilkan data!")
                    }
                    else -> {}
                }
            }
        }
        Spacer(modifier = modifier.height(20.dp))
        ReminderBanner()
        Spacer(modifier = modifier.height(16.dp))
    }
}

@Composable
fun MenuOfTheDayRow(
    menuList: List<DataItem>,
    modifier: Modifier = Modifier,
    navController: NavController
){
    val shuffledMenuList = remember { menuList.shuffled() }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
//        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(shuffledMenuList.take(5)) { menu ->
            CardMenuItemGrid(
                menu = menu,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.MenuDetail.createRoute(menu.foodId.toLong()))
                    }
            )
        }
    }
}

@Composable
fun TodayPlanner(
    menuList: List<DataRecommendation>,
    modifier: Modifier = Modifier,
    navController: NavController
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
//        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
            .height(288.dp)
    ){
        items(menuList){ menu ->
            CardMenuItemList(
                menu = menu,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.MenuDetail.createRoute(menu.foodId.toLong()))
                    }
            )
        }
    }
}

@Composable
fun MultipleStylesInText() {
    Text(
        textAlign = TextAlign.Start,
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = neutralColor1,
                fontFamily = interFont,
                fontSize = 14.sp,
                fontWeight = FontWeight(500)
            )) {
                append("Want to ")
            }

            withStyle(style = SpanStyle(
                color = pSmashedPumpkin,
                fontFamily = interFont,
                fontSize = 14.sp,
                fontWeight = FontWeight(500)
            )) {
                append("make meal planner ")
            }

            withStyle(style = SpanStyle(
                color = neutralColor1,
                fontFamily = interFont,
                fontSize = 14.sp,
                fontWeight = FontWeight(500)
            )) {
                append("for ")
            }

            withStyle(style = SpanStyle(
                color = pSmashedPumpkin,
                fontFamily = interFont,
                fontSize = 14.sp,
                fontWeight = FontWeight(500)
            )) {
                append("today")
            }

        }
    )
}


//@Preview(
//    showBackground = true,
//    device = Devices.PIXEL_4
//)
//@Composable
//fun MenuContentPreview() {
//
//    HomeScreen()
//}