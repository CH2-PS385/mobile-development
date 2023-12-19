package com.ch2ps385.nutrimate.presentation.screen.user.home

import android.content.ContentValues
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.UserData
import com.ch2ps385.nutrimate.data.remote.model.GetMealPlanner
import com.ch2ps385.nutrimate.data.remote.responses.DataItem
import com.ch2ps385.nutrimate.data.remote.responses.GetMealPlannerResponse
import com.ch2ps385.nutrimate.data.remote.responses.TodayMenuItem
import com.ch2ps385.nutrimate.di.Injection
import com.ch2ps385.nutrimate.presentation.screen.user.UserViewModelFactory
import com.ch2ps385.nutrimate.presentation.ui.component.carditem.CardMenuItemList
import com.ch2ps385.nutrimate.presentation.ui.component.other.CircularProgressAnimated
import com.ch2ps385.nutrimate.presentation.ui.component.other.ReminderBanner
import com.ch2ps385.nutrimate.presentation.ui.component.section.HomeSection
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.interFont
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor5
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.sBabyPink
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
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
                viewModel.getTodayMenu()
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
//        HomeSection(
//            title = "Today's Plans",
//            modifier = modifier.align(Alignment.Start)
//        ) {
////            LaunchedEffect(viewModel) {
////                val currentDate = LocalDate.now()
////                val dd: Int = currentDate.dayOfMonth
////                val mm: Int = currentDate.monthValue
////                val yyyy: Int =  currentDate.year
////
////                // Lakukan apa pun yang perlu Anda lakukan dengan nilai dd, mm, dan yy di sini
////                Log.d(ContentValues.TAG, "dd: $dd, mm: $mm, yy: $yyyy")
////                viewModel.getMealPlanner(
////                    GetMealPlanner(
////                        email = userData?.username.toString(),
////                        dd = dd,
////                        mm = mm,
////                        yy =  yyyy
////                    )
////                )
////            }
//            val currentDate = LocalDate.now()
//            val dd: Int = currentDate.dayOfMonth
//            val mm: Int = currentDate.monthValue
//            val yyyy: Int =  currentDate.year
//
//            // Lakukan apa pun yang perlu Anda lakukan dengan nilai dd, mm, dan yy di sini
//            viewModel.stateGetDataMealPlanner.collectAsState(initial = Resource.Loading()).value.let { state ->
//                when(state){
//                    is Resource.Loading -> {
////                    CustomLinearProgressBar()
//                        CircularProgressAnimated()
//                        Log.d(ContentValues.TAG, "dd: $dd, mm: $mm, yy: $yyyy")
//                        viewModel.getMealPlanner(
//                            GetMealPlanner(
//                                email = userData?.email.toString(),
//                                dd = dd,
//                                mm = mm,
//                                yy =  2024
//                            )
//                        )
//                    }
//                    is Resource.Success -> {
//                        if (!state.data?.isEmpty!!){
//                            state.data?.let {
//                                state.data?.let { TodayPlanner(todayPlanner = it, navController = navController ) }
//                            }
//                        } else{
//                            Text(text = "Meal Planner hari ini tidak tersedia!")
//                        }
//                    }
//                    is Resource.Error -> {
//                        Log.d(ContentValues.TAG, "Tidak dapat mmenampilkan data!")
//                    }
//                    else -> {}
//                }
//            }
//        }
//            }

        val currentDate = LocalDate.now()
        val dd: Int = currentDate.dayOfMonth
        val mm: Int = currentDate.monthValue
        val yyyy: Int =  currentDate.year
        viewModel.stateGetDataMealPlanner.collectAsState(initial = Resource.Loading()).value.let { state ->
            when (state) {
                is Resource.Loading -> {
                    CircularProgressAnimated()
                    Log.d(ContentValues.TAG, "dd: $dd, mm: $mm, yy: $yyyy")
                    viewModel.getMealPlanner(
                        GetMealPlanner(
                            email = userData?.email.toString(),
                            dd = dd,
                            mm = mm,
                            yy = yyyy
                        )
                    )
                }
                is Resource.Success -> {
                    if (!state.data?.isEmpty!!) {
                        HomeSection(
                            title = "Todays menu",
                            modifier = modifier.align(Alignment.Start)
                        ) {
                            TodayPlanner(todayPlanner = state.data, navController = navController)
                        }
                    } else {
                        LaunchedEffect(viewModel) {
                            viewModel.getAllMenu()
                        }
                        viewModel.stateGetAllMenu.collectAsState(initial = Resource.Loading()).value.let { state ->
                            when(state){
                                is Resource.Loading -> {
                                    CircularProgressAnimated()
                                }
                                is Resource.Success -> {
                                    state.data?.let {
                                        HomeSection(
                                            title = "Our Recommendation",
                                            modifier = modifier.align(Alignment.Start)
                                        ) {
                                            RecommendMenu(menuList = state.data, navController = navController)
                                        }
                                    }
                                }
                                is Resource.Error -> {
                                    Log.d(ContentValues.TAG, "Tidak dapat mmenampilkan data!")
                                }
                                else -> {}
                            }
                        }
                    }
                }

                is Resource.Error -> {
                    Log.d(ContentValues.TAG, "Tidak dapat mmenampilkan data!")
                }

                else -> {}
            }
        }
        Spacer(modifier = modifier.height(20.dp))
        ReminderBanner(navController = navController)
        Spacer(modifier = modifier.height(16.dp))
    }
}

@Composable
fun MenuOfTheDayRow(
    menuList: List<TodayMenuItem>,
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
fun RecommendMenu(
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
            com.ch2ps385.nutrimate.presentation.ui.component.carditem.CardMenuItemGrid(
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
    todayPlanner: GetMealPlannerResponse,
    modifier: Modifier = Modifier,
    navController: NavController
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
//        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
            .height(288.dp)
    ){
        if (todayPlanner.data != null){
            items(todayPlanner.data){ menu ->
                CardMenuItemList(
                    planner = menu,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.MenuDetail.createRoute(menu.foodId.toLong()))
                        }
                )
            }
        }
    }
}

@Composable
fun CardMenuItemGrid(
    menu: TodayMenuItem,
    modifier: Modifier = Modifier
){

    Card(
        modifier = modifier
            .shadow(
                elevation = 3.dp,
                shape = Shapes.small,
                spotColor = pSmashedPumpkin,
                ambientColor = pSmashedPumpkin
            )
            .clip(Shapes.extraSmall)
            .height(230.dp)
            .width(175.dp)
//            .shadow(elevation = 20.dp, shape = Shapes.small)
            .background(color = sBabyPink)
            .border(BorderStroke(1.dp, color = sBabyPink), Shapes.extraSmall)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = modifier
                .fillMaxSize()
                .background(color = solidWhite)
                .padding(5.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = menu.imageUrl,
//                        onLoading = {
//                            CircularProgressAnimated()
//                        }
                ),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .width(170.dp)
                    .height(124.dp)
                    .clip(Shapes.extraSmall)
            )
            Row(
                modifier = modifier
                    .heightIn(32.dp)
                    .fillMaxWidth(),
//                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = menu.namaMakananClean,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 4.dp)
                    )
                }

            }
            Row(
                modifier = modifier
                    .heightIn(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Box(
                    modifier = modifier
                        .widthIn(50.dp)
                        .clip(Shapes.extraSmall)
                        .background(color = pSmashedPumpkin)
                        .padding(
                            horizontal = 8.dp,
                            vertical = 2.dp,
                        )
                ) {
                    Text(
                        text = stringResource(R.string.calorries_menu, menu.kalori),
                        style = TextStyle(
                            color = solidWhite,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            fontWeight = FontWeight(700),
                        )
                    )
                }
                Box(
                    modifier = modifier
                        .clip(Shapes.extraSmall)
                        .background(color = neutralColor5)
                        .padding(
                            horizontal = 8.dp,
                            vertical = 2.dp,
                        )
                ){
                    Text(
                        text = menu.jenisOlahan,
                        style = TextStyle(
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            fontWeight = FontWeight(700),
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = modifier
                    .heightIn(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ){
                Box(
                    modifier = modifier
                        .clip(Shapes.extraSmall)
                        .background(color = neutralColor5)
                        .padding(
                            horizontal = 8.dp,
                            vertical = 2.dp,
                        )
                ){
                    Text(
                        text = menu.tipe,
                        style = TextStyle(
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            fontWeight = FontWeight(700),
                        )
                    )
                }
            }
        }
    }
}
