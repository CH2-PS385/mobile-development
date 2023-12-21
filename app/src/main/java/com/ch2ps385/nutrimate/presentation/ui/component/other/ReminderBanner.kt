package com.ch2ps385.nutrimate.presentation.ui.component.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor3
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin

@Composable
fun ReminderBanner(
    navController: NavController,
    modifier : Modifier = Modifier
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp)
            .clip(Shapes.large)
            .background(color = Color(0x40FBCB5C))
            .padding(top = 8.dp, start = 8.dp, end = 12.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.person_drink),
            contentDescription = "a person drink water",
            modifier = modifier
                .width(73.dp)
                .height(89.31765.dp)
                .align(Alignment.BottomStart)
                .offset(y = 5.dp, x = 16.dp)
        )
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = "Stay hydrated, stay healthy",
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.End,
                color = neutralColor1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)
                    .align(Alignment.End)
            )
            Spacer(modifier = modifier.height(2.dp))
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .width(160.dp),// Jarak antara teks dan garis
                color = neutralColor3
            )
            Text(
                text = "Water is the ultimate pick-me-up",
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.End,
                color = neutralColor1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)
                    .align(Alignment.End)
            )
            Text(
                text = "Did you know? Water makes up about\n60% of your body weight",
                style = TextStyle(
                    fontSize = 8.sp,
                    lineHeight = 12.8.sp,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontWeight = FontWeight(500)),
                textAlign = TextAlign.End,
                color = pSmashedPumpkin,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)
                    .align(Alignment.End)
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun ReminderBannerPreview(){
    NutriMateTheme {
        ReminderBanner(navController = rememberNavController())
    }
}