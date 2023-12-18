package com.ch2ps385.nutrimate.presentation.screen.user.reminder

import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.presentation.ui.component.button.ReminderButtonPackage
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor3
import com.ch2ps385.nutrimate.presentation.ui.theme.pNaplesYellow
import com.ch2ps385.nutrimate.presentation.ui.theme.pSinopia
import com.ch2ps385.nutrimate.presentation.ui.theme.sNyanza
import com.ch2ps385.nutrimate.presentation.ui.theme.sUnbleachedSilk
import java.util.Calendar
import java.util.concurrent.TimeUnit
//
//private lateinit var workManager: WorkManager
//private lateinit var periodicWorkRequest: PeriodicWorkRequest
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun ReminderScreen(
//    modifier: Modifier = Modifier
//) {
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val applicationContext = LocalContext.current
//    var isPermissionGranted by remember { mutableStateOf(false) }
//
//    // Request notification permissions
//    val requestPermissionLauncher =
//        rememberLauncherForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted: Boolean ->
//            if (isGranted) {
//                isPermissionGranted = true
//                Log.d("ReminderScreen", "Notifications permission granted")
//            } else {
//                Log.d("ReminderScreen", "Notifications will not show without permission")
//            }
//        }
//
//    LaunchedEffect(key1 = Unit) {
//        // Request notification permissions if not granted
//        if (isPermissionGranted) {
//            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
//        }
//
//        periodicWorkRequest = PeriodicWorkRequest.Builder(
//            ReminderNotificationWorker::class.java,
//            repeatInterval = 15,
//            repeatIntervalTimeUnit = TimeUnit.MINUTES// Delay to start at the next 6 AM
//        ).build()
//
//
//        val workManager = WorkManager.getInstance(applicationContext)
//        workManager.enqueue(periodicWorkRequest)
//
//        workManager.getWorkInfoByIdLiveData(periodicWorkRequest.id)
//            .observe(lifecycleOwner){ workInfo ->
//                Log.d("ReminderScreen", "Message: ${workInfo.state}")
//            }
//    }
//    Text(text = "Reminder")
//}


private lateinit var workManager: WorkManager
private lateinit var periodicWorkRequest: PeriodicWorkRequest
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReminderScreen(
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val applicationContext = LocalContext.current
    var isPermissionGranted by remember { mutableStateOf(false) }

    // Request notification permissions
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
    val repeatIntervalHours = 2


    LaunchedEffect(key1 = Unit) {
        // Request notification permissions if not granted
        if (isPermissionGranted) {
            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }

        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)

        if (currentHour in startHour..endHour) {
            calendar.set(Calendar.HOUR_OF_DAY, startHour)

            // If the current time is already past 6 AM, set the initial delay for the next day
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
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
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
                Text(
                    text = "500 ml",
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
            ReminderButtonPackage()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ReminderScreenPreview() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        ReminderScreen()
    }
}