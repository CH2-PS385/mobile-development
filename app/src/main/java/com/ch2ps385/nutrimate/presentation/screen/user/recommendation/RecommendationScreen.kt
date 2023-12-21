package com.ch2ps385.nutrimate.presentation.screen.user.recommendation


import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.UserData
import com.ch2ps385.nutrimate.data.remote.model.AddMealPlanner
import com.ch2ps385.nutrimate.data.remote.model.GetMealPlanner
import com.ch2ps385.nutrimate.data.remote.responses.AddMealPlannerResponse
import com.ch2ps385.nutrimate.data.remote.responses.GetMealPlannerResponse
import com.ch2ps385.nutrimate.di.Injection
import com.ch2ps385.nutrimate.presentation.screen.user.UserViewModelFactory
import com.ch2ps385.nutrimate.presentation.ui.component.carditem.CardRecommendationItem
import com.ch2ps385.nutrimate.presentation.ui.component.other.FetchLoading
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor3
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor4
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor6
import com.ch2ps385.nutrimate.presentation.ui.theme.pSinopia
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationScreen(
    userData: UserData?,
    navController: NavController,
    viewModel : RecommendationViewModel = viewModel(
        factory = UserViewModelFactory(Injection.provideUserRepository(LocalContext.current))
    ),
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    var selectedDate by remember {
        mutableStateOf<LocalDate?>(null)
    }

    val successAddState = rememberUseCaseState()
    val successConfigState = State.Success(labelText = "Meal planner has been generated sucessfully!")
    StateDialog(
        state = successAddState,
        config = StateConfig(state = successConfigState),
    )

    val failedAddState = rememberUseCaseState()
    val  failedConfigState = State.Failure(labelText = "Meal planner Failed!")

    StateDialog(
        state = failedAddState,
        config = StateConfig(state = failedConfigState),
    )

    val calendarState = rememberUseCaseState()
    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date { date ->
            Log.d(TAG, "Selected Date: $date")
            selectedDate = date
        }
    )

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Meal Planner",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 22.1.sp,
                fontFamily = FontFamily(Font(R.font.inter_bold)),
                fontWeight = FontWeight(700),
                color = neutralColor1,
                ),
            color = neutralColor1
        )
        Spacer(modifier = Modifier.height(32.dp))



        LaunchedEffect(selectedDate) {
            if (selectedDate != null) {
                Log.e(TAG, "LAUNCHED EFFECT : $selectedDate")
                val date = selectedDate!!
                val dd: Int = date.dayOfMonth
                val mm: Int = date.monthValue
                val yyyy: Int = date.year

                viewModel.getMealPlanner(
                    GetMealPlanner(
                        email = userData?.email!!,
                        dd = dd,
                        mm = mm,
                        yy = yyyy,
                    )
                )
            } else {
                val currentDate = LocalDate.now()
                val dd: Int = currentDate.dayOfMonth
                val mm: Int = currentDate.monthValue
                val yyyy: Int = currentDate.year

                viewModel.getMealPlanner(
                    GetMealPlanner(
                        email = userData?.email!!,
                        dd = dd,
                        mm = mm,
                        yy = yyyy,
                    )
                )
            }
        }


        Button(
            onClick = {

                calendarState.show()
                Log.d(TAG, "[Selected Date Button: $selectedDate]")

                      },
            colors = ButtonDefaults.buttonColors(containerColor = pSinopia),
            modifier = modifier
                .fillMaxWidth()
                .height(32.dp)
        ) {
            Image(
                painterResource(id = R.drawable.ic_calendar),
                contentDescription = "Date",
                modifier = Modifier.size(20.dp)
            )
            Text(
//                text = "Today, November 28",
                text = selectedDate?.let {
                    // Format tanggal jika selectedDate tidak null
                    it.format(DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.getDefault()))
                } ?: LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.getDefault())),
                Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (selectedDate != null) {
                    Log.e(TAG, "LAUNCHED EFFECT : $selectedDate")
                    val date = selectedDate!!
                    val dd: Int = date.dayOfMonth
                    val mm: Int = date.monthValue
                    val yyyy: Int = date.year

                    viewModel.addMealPlanner(
                        AddMealPlanner(
                            email = userData?.email!!,
                            dd = dd,
                            mm = mm,
                            yy = yyyy,
                        )
                    )
                    viewModel.onGenerateClick()
                } else {
                    val currentDate = LocalDate.now()
                    val dd: Int = currentDate.dayOfMonth
                    val mm: Int = currentDate.monthValue
                    val yyyy: Int = currentDate.year

                    viewModel.addMealPlanner(
                        AddMealPlanner(
                            email = userData?.email!!,
                            dd = dd,
                            mm = mm,
                            yy = yyyy,
                        )
                    )
                    viewModel.onGenerateClick()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = pSmashedPumpkin),
            modifier = modifier
                .fillMaxWidth()
                .height(32.dp)
        ) {
            Image(
                painterResource(id = R.drawable.ic_refresh),
                contentDescription = "Generate",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Generate a New Meal Planner",
                Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }

        viewModel.stateGetDataMealPlanner.collectAsState().value.let { state ->
            when(state){
                is Resource.Loading ->{
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            color = pSmashedPumpkin,
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
                is Resource.Success -> {
                    if (!state.data?.isEmpty!!) {
                        GetRecommendationContent( plannerList = state.data, navigateToDetail = navigateToDetail)
                    } else{
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.no_data),
                                contentDescription = "No Data",
                                modifier = Modifier
                                    .height(128.dp)
                                    .width(128.dp)
                                    .graphicsLayer(alpha = 0.25f)
                            )
                            Spacer(modifier = modifier.height(16.dp))
                            Text(
                                text =  stringResource(R.string.not_found),
                                style = MaterialTheme.typography.labelSmall,
                                color = neutralColor4
                            )
                        }
                    }
                }
                is Resource.Error -> {

                }
                else->{}
            }
        }

        viewModel.stateRecommendationMeal.collectAsState().value.let { state ->
            when(state){
                is Resource.Initial -> {}
                is Resource.Loading ->{
                    Log.d(TAG, "Loading of Generating New Planner...")

                    if (viewModel.isDialogLoadingShown.value) {
                        FetchLoading(closeSelection = { viewModel.onDismissLoadingDialog() })
                    }
                }
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        val currentDate = selectedDate ?: LocalDate.now()
                        val dd: Int = currentDate.dayOfMonth
                        val mm: Int = currentDate.monthValue
                        val yyyy: Int = currentDate.year

                        viewModel.getMealPlanner(
                            GetMealPlanner(
                                email = userData?.email!!,
                                dd = dd,
                                mm = mm,
                                yy = yyyy,
                            )
                        )
                    }
                    PostRecommendationContent(plannerList = state.data!!, navigateToDetail = navigateToDetail)
                    LaunchedEffect(Unit){
                        if(viewModel.isDialogShown.value){
                            successAddState.show()
                            delay(2000)
                            successAddState.finish()
                            viewModel.onDismissDialog()
                        }
                    }
                }

                is Resource.Error -> {
                    LaunchedEffect(Unit){
                        if(viewModel.isDialogShown.value){
                            failedAddState.show()
                            delay(1000)
                            failedAddState.finish()
                            viewModel.onDismissDialog()
                        }
                    }
                }
                else->{}
            }
        }
    }
}

@Composable
fun PostRecommendationContent(
    plannerList: AddMealPlannerResponse,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
){

    if(plannerList != null){
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ){
            item{
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(color = neutralColor6)
                        .border(width = 1.dp, color = pSinopia, shape = Shapes.medium),
                    contentAlignment = Alignment.Center
                ){
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.calories_icon),
                            contentDescription = "Calories Icon",
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = stringResource(id = R.string.recommend_calorries_banner, plannerList.nutrition.calories),
                                style = MaterialTheme.typography.titleSmall,
                                color = neutralColor1
                            )
                            Text(
                                text = stringResource(id = R.string.recommend_subcontent_banner, plannerList.nutrition.protein, plannerList.nutrition.fat, plannerList.nutrition.carbs),
                                style = TextStyle(
                                    fontSize = 8.sp,
                                    lineHeight = 12.8.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_medium)),
                                    fontWeight = FontWeight(500),
                                ),
                                color = neutralColor3
                            )
                        }
                    }
                }
            }
            items(plannerList.data){ menu ->
                CardRecommendationItem(
                    callories = menu.kalori.toString(),
                    protein = menu.protein.toString(),
                    fat = menu.lemak.toString(),
                    carbs = menu.karbohidrat.toString(),
                    name = menu.namaMakananClean,
                    tipe = menu.tipe,
                    imageUrl = menu.imageUrl,
                    modifier = Modifier
                        .clickable {
                            navigateToDetail(menu.foodId.toLong())
                        }
                )
            }
        }
    } else{
        Text(text = "DATA KOSONG")
    }
}


@Composable
fun GetRecommendationContent(
    plannerList: GetMealPlannerResponse,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
){
    if(!plannerList.isEmpty){
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(vertical = 16.dp),
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ){
            item{
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(color = neutralColor6)
                        .border(width = 1.dp, color = pSinopia, shape = Shapes.medium),
                    contentAlignment = Alignment.Center
                ){
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.calories_icon),
                            contentDescription = "Calories Icon",
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = stringResource(id = R.string.recommend_calorries_banner, plannerList.nutrition.calories),
                                style = MaterialTheme.typography.titleSmall,
                                color = neutralColor1
                            )
                            Text(
                                text = stringResource(id = R.string.recommend_subcontent_banner, plannerList.nutrition.protein, plannerList.nutrition.fat, plannerList.nutrition.carbs),
                                style = TextStyle(
                                    fontSize = 8.sp,
                                    lineHeight = 12.8.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_medium)),
                                    fontWeight = FontWeight(500),
                                ),
                                color = neutralColor3
                            )
                        }
                    }
                }
            }
            items(plannerList.data){ menu ->
                CardRecommendationItem(
                    callories = menu.kalori.toString(),
                    protein = menu.protein.toString(),
                    fat = menu.lemak.toString(),
                    carbs = menu.karbohidrat.toString(),
                    name = menu.namaMakananClean,
                    tipe = menu.tipe,
                    imageUrl = menu.imageUrl,
                    modifier = Modifier
                        .clickable {
                            navigateToDetail(menu.foodId.toLong())
                        }
                )
            }
        }
    } else{
        Text(text = "DATA KOSONG")
    }
}
