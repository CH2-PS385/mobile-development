package com.ch2ps385.nutrimate.presentation.screen.user.reminder

import android.content.ContentValues
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.UserData
import com.ch2ps385.nutrimate.data.remote.model.AddWaterIntake
import com.ch2ps385.nutrimate.data.remote.model.GetWaterIntake
import com.ch2ps385.nutrimate.di.Injection
import com.ch2ps385.nutrimate.presentation.screen.user.UserViewModelFactory
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor3
import com.ch2ps385.nutrimate.presentation.ui.theme.pSinopia
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.sBabyPink
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.util.Calendar
import java.util.concurrent.TimeUnit


private lateinit var workManager: WorkManager
private lateinit var periodicWorkRequest: PeriodicWorkRequest
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReminderScreen(
    modifier: Modifier = Modifier,
    userData: UserData?,
    viewModel : ReminderViewModel = viewModel(
        factory = UserViewModelFactory(Injection.provideUserRepository(LocalContext.current))
    ),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val applicationContext = LocalContext.current
    var isPermissionGranted by remember { mutableStateOf(false) }

    val successAddState = rememberUseCaseState()
    val successConfigState = State.Success(labelText = "Amount of water has been added!")

    StateDialog(
        state = successAddState,
        config = StateConfig(state = successConfigState),
    )

    val failedAddState = rememberUseCaseState()
    val  failedConfigState = State.Failure(labelText = "Opss, try again!")

    StateDialog(
        state = failedAddState,
        config = StateConfig(state = failedConfigState),
    )

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                isPermissionGranted = true
                Log.d("ReminderScreen", "Notifications permission granted")
            } else {
                Log.d("ReminderScreen", "Notifications will not show without permission")
            }
        }

    val startHour = 6
    val endHour = 22

    LaunchedEffect(key1 = true){
        val currentDate = LocalDate.now()
        val dd: Int = currentDate.dayOfMonth
        val mm: Int = currentDate.monthValue
        val yyyy: Int = currentDate.year

        viewModel.getWaterIntake(
            GetWaterIntake(
                email = userData?.email!!,
                dd = dd,
                mm = mm,
                yy = yyyy,
            )
        )
    }



    LaunchedEffect(key1 = Unit) {
        if (isPermissionGranted) {
            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }

        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)

        if (currentHour in startHour..endHour) {
            calendar.set(Calendar.HOUR_OF_DAY, startHour)

            if (System.currentTimeMillis() > calendar.timeInMillis) {
                calendar.add(Calendar.DAY_OF_YEAR, 1)
            }

            val initialDelay = calendar.timeInMillis - System.currentTimeMillis()

            periodicWorkRequest = PeriodicWorkRequest.Builder(
                ReminderNotificationWorker::class.java,
                2,
                TimeUnit.HOURS
            )
                .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
                .build()

            val workManager = WorkManager.getInstance(applicationContext)
            workManager.enqueue(periodicWorkRequest)

            workManager.getWorkInfoByIdLiveData(periodicWorkRequest.id)
                .observe(lifecycleOwner) { workInfo ->
                    Log.d("ReminderScreen", "Message: ${workInfo.state}")
                }
        } else {
            Log.d("ReminderScreen", "WorkManager won't work after 10 PM")
        }
    }

    var isSuccessStateVisible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = viewModel){
        viewModel.stateGetWaterIntake
    }

    viewModel.stateGetWaterIntake.collectAsState().value.let { state ->
        when(state){
            is Resource.Loading ->{
                Log.d(ContentValues.TAG, "Loading...")
                val currentDate = LocalDate.now()
                val dd: Int = currentDate.dayOfMonth
                val mm: Int = currentDate.monthValue
                val yyyy: Int = currentDate.year

                viewModel.getWaterIntake(
                    GetWaterIntake(
                        email = userData?.email!!,
                        dd = dd,
                        mm = mm,
                        yy = yyyy,
                    )
                )
            }
            is Resource.Success -> {
                val result = (state.data?.data?.sum!!).toString()
                ReminderContent(
                    viewModel = viewModel,
                    userData = userData,
                    successAddState = successAddState,
                    failedAddState = failedAddState,
                    sumWaterIntake = result)
            }

            is Resource.Error -> {
                LaunchedEffect(Unit){
                    failedAddState.show()
                    delay(2000)
                    failedAddState.finish()
                }
            }
            else->{}
        }
    }

    viewModel.stateAddWaterIntake.collectAsState(initial = Resource.Initial()).value.let { state ->
        when(state){
            is Resource.Initial -> {}
            is Resource.Loading ->{
                Log.d(ContentValues.TAG, "Loading...")
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
            }
            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    val currentDate = LocalDate.now()
                    val dd: Int = currentDate.dayOfMonth
                    val mm: Int = currentDate.monthValue
                    val yyyy: Int = currentDate.year

                    viewModel.getWaterIntake(
                        GetWaterIntake(
                            email = userData?.email!!,
                            dd = dd,
                            mm = mm,
                            yy = yyyy,
                        )
                    )
                }
                LaunchedEffect(Unit){
                    if(viewModel.isDialogShown.value){
                        successAddState.show()
                        delay(3000)
                        successAddState.finish()
                        viewModel.onDismissDialog()
                    }
                }
            }

            is Resource.Error -> {
                LaunchedEffect(Unit){
                    if(viewModel.isDialogShown.value){
                        failedAddState.show()
                        delay(3000)
                        failedAddState.finish()
                        viewModel.onDismissDialog()
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReminderContent(
    viewModel: ReminderViewModel,
    userData : UserData?,
    successAddState : UseCaseState,
    failedAddState : UseCaseState,
    sumWaterIntake: String
){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color(0x40FBCB5C),
                    shape = RoundedCornerShape(bottomStart = 56.dp, bottomEnd = 56.dp)
                )
                .fillMaxWidth()
                .height(400.dp)
        ){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 60.dp)
            ) {

                val selectedValues = remember { mutableStateOf<List<Int>>(emptyList()) }
                Text(
                    text = stringResource(id = R.string.waterIntakeSum, sumWaterIntake) ?: "0 ml",
                    style = MaterialTheme.typography.headlineMedium,
                    color = neutralColor1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Has been drunk from the total target of 2000 ml",
                    style = MaterialTheme.typography.labelSmall,
                    color = neutralColor3
                )
            }
            Image(
                painter = painterResource(id = R.drawable.person_drink_hd),
                contentDescription = "A person drink water",
                modifier = Modifier
                    .width(255.dp)
                    .height(312.dp)
                    .offset(y = 120.dp, x = 72.dp)
            )

        }
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier.padding(horizontal = 30.dp)
        ){
            Text(
                text = "Drink",
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                color = pSinopia,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.inter_bold)),
                    fontWeight = FontWeight(700)
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Choose the amount of water you drink",
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                color = neutralColor3,
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 19.2.sp,
                    fontFamily = FontFamily(Font(R.font.inter_medium)),
                    fontWeight = FontWeight(500),
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            var selectedButtonIndex by remember { mutableStateOf(-1) }

            val mlValues = (100..550 step 50).toList()

            // Menentukan apakah tombol "Add" dapat diaktifkan
            val isAddButtonEnabled = selectedButtonIndex != -1


            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                repeat(3) { row ->
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        repeat(3) { column ->
                            val index = row * 3 + column
                            val isSelected = index == selectedButtonIndex

                            val mlText = "${mlValues[index]} ml"

                            Box(
                                modifier = Modifier
                                    .widthIn(104.dp)
                                    .height(48.dp)
                                    .padding(4.dp)
                                    .shadow(
                                        elevation = 1.dp,
                                        shape = Shapes.medium,
                                        spotColor = pSmashedPumpkin,
                                        ambientColor = pSmashedPumpkin
                                    )
                                    .clip(Shapes.medium)
                                    .background(if (isSelected) pSmashedPumpkin else solidWhite)
                                    .border(
                                        width = 1.dp,
                                        shape = Shapes.medium,
                                        color = if (!isSelected) sBabyPink else pSmashedPumpkin
                                    )
                                    .clickable {
                                        selectedButtonIndex = index
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(Shapes.medium)
                                        .background(if (isSelected) pSmashedPumpkin else solidWhite)
                                )
                                Text(
                                    text = mlText,
                                    color = (if (isSelected) solidWhite else neutralColor1),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        lineHeight = 24.sp,
                                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                                        fontWeight = FontWeight(600),)
                                )
                            }
                        }
                    }
                }
                val selectedValues = remember { mutableStateOf<List<Int>>(emptyList()) }
                Button(
                    onClick = {
                        if (selectedButtonIndex != -1) {
                            val selectedWaterIntake = mlValues[selectedButtonIndex]
                            selectedValues.value = selectedValues.value + selectedWaterIntake
                            selectedButtonIndex = -1 


                            val currentDate = LocalDate.now()
                            val dd: Int = currentDate.dayOfMonth
                            val mm: Int = currentDate.monthValue
                            val yyyy: Int = currentDate.year
                            viewModel.addWaterIntake(
                                AddWaterIntake(
                                    email = userData?.email!!,
                                    dd = dd,
                                    mm = mm,
                                    yy = yyyy,
                                    intake = selectedWaterIntake
                                )
                            )
                            viewModel.onAddClick()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = pSinopia
                    ),
                    enabled = isAddButtonEnabled,
                    modifier = Modifier
                        .width(240.dp)
                        .height(56.dp)
                        .padding(top = 16.dp)

                ) {
                    Text(
                        text = "Add",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFAF2E9),
                            textAlign = TextAlign.Center,)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ReminderScreenPreview() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val userData = UserData(
            email = "example@example.com",
            userId = "1213",
            username = "adi",
            profilePictureUrl = "klandnadabdw"
        ) // Gantilah dengan data sesuai kebutuhan
        ReminderScreen(userData = userData, viewModel = viewModel(
                factory = UserViewModelFactory(Injection.provideUserRepository(LocalContext.current))
                ),)
    }
}