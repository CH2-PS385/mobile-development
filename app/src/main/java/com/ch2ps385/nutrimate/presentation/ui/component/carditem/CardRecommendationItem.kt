package com.ch2ps385.nutrimate.presentation.ui.component.carditem

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.common.Constants
import com.ch2ps385.nutrimate.data.remote.responses.GetDataMealPlanner
import com.ch2ps385.nutrimate.data.remote.responses.PostDataMealPlanner
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor5
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.sBabyPink
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite

@Composable
fun CardRecommendationItem(
    callories : Int,
    protein: Int,
    fat : Int,
    carbs : Int,
    name : String,
    imageUrl : String,
    tipe : String,
    modifier: Modifier = Modifier,
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
            .fillMaxWidth()
            .height(120.dp)
            .border(BorderStroke(1.dp, color = sBabyPink), Shapes.extraSmall),
        colors = CardDefaults.cardColors(
            containerColor = solidWhite
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = modifier
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, end = 8.dp)
            ){
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Image(
                        painter = painterResource(R.drawable.baseline_fastfood_24),
                        contentDescription ="Menu 1",
                        contentScale = ContentScale.FillBounds,
                        modifier = modifier
                            .padding(start = 16.dp, end = 8.dp)
                            .width(22.dp)
                            .height(22.dp)
                            .clip(Shapes.extraSmall)
                    )
                    Text(
                        text = tipe,
                        style = MaterialTheme.typography.titleSmall,
                        color = pSmashedPumpkin,
                        modifier = Modifier
                    )
                }
                Box(
                    modifier = modifier
                        .widthIn(50.dp)
                        .clip(Shapes.extraSmall)
                        .background(color = pSmashedPumpkin)
                        .padding(
                            horizontal = 8.dp,
                            vertical = 2.dp,
                        )
                ){
                    Text(
                        text = stringResource(id = R.string.recommend_card_calorries, callories.toString()),
                        style = TextStyle(
                            color = solidWhite,
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            fontWeight = FontWeight(700),
                        )
                    )
                }
            }
            Spacer(modifier = modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
                    .padding(end = 8.dp)
            ){
                Image(
                    painter = rememberAsyncImagePainter(
                        model = imageUrl,
//                        onLoading = {
//                            CircularProgressAnimated()
//                        }
                    ),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier
                        .padding(horizontal = 8.dp)
                        .width(40.dp)
                        .height(40.dp)
                        .clip(Shapes.extraSmall)
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = neutralColor1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp)
                )
            }
            Spacer(modifier = modifier.height(12.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ){
                Box(
                    modifier = modifier
                        .widthIn(50.dp)
                        .clip(Shapes.extraSmall)
                        .background(color = neutralColor5)
                        .padding(
                            horizontal = 8.dp,
                            vertical = 2.dp,
                        )
                ) {
                    Text(
                        text = stringResource(id = R.string.recommend_card_protein, protein.toString()),
                        style = TextStyle(
                            color = neutralColor1,
                            fontSize = 8.sp,
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
                        text = stringResource(id = R.string.recommend_card_fat, fat.toString()),
                        style = TextStyle(
                            fontSize = 8.sp,
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
                        text = stringResource(id = R.string.reccomend_card_carbs, carbs.toString()),
                        style = TextStyle(
                            fontSize = 8.sp,
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

@Preview(showBackground = true)
@Composable
fun CardRecommendationItemPreview() {
    NutriMateTheme {
//        CardRecommendationItem()
    }
}