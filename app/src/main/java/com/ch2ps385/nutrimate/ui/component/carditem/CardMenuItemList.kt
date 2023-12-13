package com.ch2ps385.nutrimate.ui.component.carditem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.data.dummyData.Menu
import com.ch2ps385.nutrimate.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.ui.theme.Shapes
import com.ch2ps385.nutrimate.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.ui.theme.neutralColor5
import com.ch2ps385.nutrimate.ui.theme.solidWhite

@Composable
fun CardMenuItemList(
    menu : Menu,
    modifier : Modifier = Modifier,
){
    Card(
        modifier = modifier
            .clip(Shapes.extraSmall)
            .shadow(elevation = 2.dp, spotColor = Color(0x40F1805E), ambientColor = Color(0x40F1805E))
            .background(color = neutralColor1)
            .width(354.dp)
            .height(64.dp)
    ){
        Row(
            modifier = modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                painter = painterResource(id = R.drawable.menu1),
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
                    text = menu.title,
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
                            .clip(Shapes.extraSmall)
                            .background(color = neutralColor5)
                            .padding(
                                horizontal = 8.dp,
                                vertical = 2.dp,
                            )
                    ){
                        Text(
                            text = menu.calories,
                            style = TextStyle(
                                fontSize = 6.sp,
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
                            text = menu.foodType,
                            style = TextStyle(
                                fontSize = 6.sp,
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
                            text = menu.foodMEthod,
                            style = TextStyle(
                                fontSize = 6.sp,
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

@Composable
@Preview(showBackground = true)
fun CardMenuItemListPreview(){
    NutriMateTheme{
        CardMenuItemList(
            menu = Menu(R.drawable.menu1,"Rendang", "225 cal", "Real food", "Baked"),
        )
    }
}