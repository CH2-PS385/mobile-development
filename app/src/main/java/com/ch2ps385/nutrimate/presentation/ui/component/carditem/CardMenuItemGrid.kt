package com.ch2ps385.nutrimate.presentation.ui.component.carditem

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.data.remote.responses.DataItem
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor5
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.sBabyPink
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite

@Composable
fun CardMenuItemGrid(
    menu: DataItem,
    modifier: Modifier = Modifier
){

    Card(
        modifier = modifier
            .shadow(elevation = 3.dp,shape = Shapes.small, spotColor = pSmashedPumpkin, ambientColor = pSmashedPumpkin)
            .clip(Shapes.extraSmall)
            .height(230.dp)
            .width(175.dp)
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
                    model = menu.imgUrl,
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

@Composable
private fun CircularProgressAnimated(){
    val progressValue = 0.75f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,animationSpec = infiniteRepeatable(animation = tween(900))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Adjust the padding as needed
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(progress = progressAnimationValue)
    }
}

@Composable
@Preview(showBackground = true)
fun CardMenuItemGridPreview(){
    NutriMateTheme{
        CardMenuItemGrid(
            menu =  DataItem(
                imgUrl = "https://unsplash.com/photos/sandwich-with-boiled-egg-fdlZBWIP0aM",
                sumber = "Sumber",
                namaMakananClean = "Babi Hutan Masak Rica",
                sayur = "Sayur",
                dagingAyam = "Daging Ayam",
                bijiBijian = "Biji Bijian",
                buah = "Buah",
                dagingKambing = "Daging Kambing",
                beras = "Beras",
                protein = "Protein",
                tepung = "Tepung",
                tipe = "Makanan Berat",
                kedelai = "Kedelai",
                lemak = "Lemak",
                dagingSapi = "Daging Sapi",
                namaMakanan = "Nama Makanan",
                dagingKerbau = "Daging Kerbau",
                dagingBabi = "Daging Babi",
                foodId = "1", // Ganti dengan ID sesuai kebutuhan
                kalori = "234",
                umbiUmbian = "Umbi Umbian",
                karbohidrat = "Karbohidrat",
                susu = "Susu",
                jenisOlahan = "Panggang",
                telurAyam = "Telur Ayam",
                fastFood = "Fast Food",
                ikan = "Ikan"
            )
        )
    }
}