package com.ch2ps385.nutrimate.presentation.ui.component.carditem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.data.remote.responses.GetDataMealPlanner
import com.ch2ps385.nutrimate.data.remote.responses.GetMealPlannerResponse
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor5
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.sBabyPink
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite

@Composable
fun CardMenuItemList(
    planner: GetDataMealPlanner,
    modifier: Modifier = Modifier
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = solidWhite
        ),
        modifier = modifier
            .shadow(elevation = 3.dp,shape = Shapes.small, spotColor = pSmashedPumpkin, ambientColor = pSmashedPumpkin)
            .clip(Shapes.extraSmall)
            .border(BorderStroke(1.dp, color = sBabyPink), Shapes.extraSmall)
            .widthIn(360.dp)
            .height(64.dp)
    ){
        Row(
            modifier = modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                painter = rememberAsyncImagePainter(
                    model = planner.imageUrl
//                        onLoading = {
//                            CircularProgressAnimated()
//                        }
                ),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .padding(horizontal = 4.dp)
                    .width(54.dp)
                    .height(54.dp)
                    .clip(Shapes.extraSmall)
            )

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = modifier
                    .fillMaxSize()
                    .background(color = solidWhite)
                    .padding(5.dp)
            ) {
                Text(
                    text = planner.namaMakananClean,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp)
                )
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 6.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
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
                            text = stringResource(R.string.calorries_menu, planner.kalori),
                            style = TextStyle(
                                color = solidWhite,
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
                            text = planner.tipe,
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
                            text = planner.jenisOlahan,
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

}

//@Composable
//@Preview(showBackground = true)
//fun CardMenuItemListPreview(){
//    NutriMateTheme{
//        CardMenuItemList(
//            menu = Menu(R.drawable.menu1,"Rendang", "225 cal", "Real food", "Baked"),
//        )
//    }
//}