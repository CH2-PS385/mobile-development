package com.ch2ps385.nutrimate.presentation.screen.user.recommendation


import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.presentation.screen.user.detailmenu.VerticalDivider
import com.ch2ps385.nutrimate.presentation.ui.component.carditem.CardRecommendationItem
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor3
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor4
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor6
import com.ch2ps385.nutrimate.presentation.ui.theme.pSinopia
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationScreen(
    modifier: Modifier = Modifier
) {

    val calendarState = rememberUseCaseState()
    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date {date ->
            Log.d(TAG,"Selected Date: $date")
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
        Button(
            onClick = { calendarState.show() },
            colors = ButtonDefaults.buttonColors(containerColor = pSinopia),
            modifier = modifier
                .fillMaxWidth()
                .height(32.dp)
        ) {
            Image(
                painterResource(id = R.drawable.ic_save),
                contentDescription = "Date",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Today, November 28",
                Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = pSmashedPumpkin),
            modifier = modifier
                .fillMaxWidth()
                .height(32.dp)
        ) {
            Image(
                painterResource(id = R.drawable.ic_save),
                contentDescription = "Date",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Regenerate a New Meal Planner",
                Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
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
                        text = "991 Callories",
                        style = MaterialTheme.typography.titleSmall,
                        color = neutralColor1
                    )
                    Text(
                        text = "150g Protein | 200g Fat | 29g Carbs",
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
        Spacer(modifier = Modifier.height(16.dp))
        CardRecommendationItem()
        Spacer(modifier = Modifier.height(16.dp))
        CardRecommendationItem()
        Spacer(modifier = Modifier.height(16.dp))
        CardRecommendationItem()
        Spacer(modifier = Modifier.height(16.dp))
        CardRecommendationItem()

    }
}

@Composable
@Preview(showBackground = true)
fun RecommendationScreenPreview() {
    RecommendationScreen()
}