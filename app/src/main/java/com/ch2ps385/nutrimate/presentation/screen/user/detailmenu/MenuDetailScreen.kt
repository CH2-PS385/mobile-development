package com.ch2ps385.nutrimate.presentation.screen.user.detailmenu

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.responses.Data
import com.ch2ps385.nutrimate.di.Injection
import com.ch2ps385.nutrimate.presentation.screen.user.UserViewModelFactory
import com.ch2ps385.nutrimate.presentation.ui.component.other.CircularProgressAnimated
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor4
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor6
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite

@Composable
fun MenuDetailScreen(
    foodId: Long,
    viewModel :  MenuDetailViewModel = viewModel(
        factory = UserViewModelFactory(Injection.provideUserRepository(LocalContext.current))
    ),
    navigateBack: () -> Unit,
){
    val state by viewModel.state.collectAsState()

    Log.d(TAG, "State: $state")
    viewModel.state.collectAsState(initial = Resource.Loading()).value.let { state ->
        when(state){
            is Resource.Loading ->{
                CircularProgressAnimated()
                viewModel.getMenuById(foodId.toString())
            }
            is Resource.Success -> {
                val data = state.data
                if (data != null) {
                    DetailContent(
                        data
                    )
                }
            }
            else->{}
        }
    }

}

@Composable
fun DetailContent(
    data: Data,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = solidWhite)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            Modifier
                .width(312.dp)
                .height(332.dp)
        ){
            Image(
                painter = rememberAsyncImagePainter(model = data.imgUrl),
                contentDescription = "foto menu",
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .clip(shape = RoundedCornerShape(56.dp))
                    .width(312.dp)
                    .height(312.dp)
            )
            Surface(
                color = solidWhite,
                modifier = modifier
                    .width(240.dp)
                    .height(40.dp)
                    .border(BorderStroke(2.dp, color = pSmashedPumpkin), shape = Shapes.medium)
                    .align(Alignment.BottomCenter),
                shape = Shapes.medium,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp), 
                ) {
                    Text(
                        text = data.namaMakananClean,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = modifier
                .height(28.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .widthIn(136.dp)
                    .height(28.dp)
                    .clip(shape = RoundedCornerShape(32.dp))
                    .background(color = pSmashedPumpkin)
                    .padding(
                        horizontal = 8.dp,
                        vertical = 2.dp,
                    )
            ){
                Text(
                    text = data.sumber,
                    style = MaterialTheme.typography.labelMedium,
                    color = neutralColor6
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = modifier
                .height(28.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .widthIn(136.dp)
                    .height(28.dp)
                    .clip(shape = RoundedCornerShape(32.dp))
                    .background(color = pSmashedPumpkin)
                    .padding(
                        horizontal = 8.dp,
                        vertical = 2.dp,
                    )
            ){
                if(data.fastFood == "Ya"){
                    Text(
                        text =  stringResource(R.string.fast_food),
                        style = MaterialTheme.typography.labelMedium,
                        color = neutralColor6
                    )
                } else{
                    Text(
                        text =  stringResource(R.string.healthy_food),
                        style = MaterialTheme.typography.labelMedium,
                        color = neutralColor6
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .widthIn(136.dp)
                    .height(28.dp)
                    .clip(shape = RoundedCornerShape(32.dp))
                    .background(color = pSmashedPumpkin)
                    .padding(
                        horizontal = 8.dp,
                        vertical = 2.dp,
                    )
            ){
                Text(
                    text = data.tipe,
                    style = MaterialTheme.typography.labelMedium,
                    color = neutralColor6
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ){
            Text(
                text =  stringResource(R.string.nutrition_facts),
                style = MaterialTheme.typography.titleMedium,
                color = neutralColor1,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = modifier
                    .heightIn(36.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ){
                VerticalDivider()
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .padding(horizontal = 6.dp)
                        .heightIn(40.dp)
                        .widthIn(48.dp)
                ) {
                    Text(
                        text = data.kalori,
                        style = MaterialTheme.typography.titleSmall,
                        color = neutralColor1
                    )
                    Text(
                        text =  stringResource(R.string.callories_detail),
                        style = MaterialTheme.typography.labelSmall,
                        color = neutralColor4
                    )
                }
                VerticalDivider()
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .padding(horizontal = 6.dp)
                        .heightIn(40.dp)
                        .widthIn(48.dp)
                ) {
                    Text(
                        text = stringResource(R.string.gram_template, data.protein),
                        style = MaterialTheme.typography.titleSmall,
                        color = neutralColor1
                    )
                    Text(
                        text =  stringResource(R.string.protein),
                        style = MaterialTheme.typography.labelSmall,
                        color = neutralColor4
                    )
                }
                VerticalDivider()
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .padding(horizontal = 6.dp)
                        .heightIn(40.dp)
                        .widthIn(48.dp)
                ) {
                    Text(
                        text = stringResource(R.string.gram_template, data.lemak),
                        style = MaterialTheme.typography.titleSmall,
                        color = neutralColor1
                    )
                    Text(
                        text =  stringResource(R.string.fat),
                        style = MaterialTheme.typography.labelSmall,
                        color = neutralColor4
                    )
                }
                VerticalDivider()
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .padding(horizontal = 6.dp)
                        .heightIn(40.dp)
                        .widthIn(48.dp)
                ) {
                    Text(
                        text = stringResource(R.string.gram_template, data.karbohidrat),
                        style = MaterialTheme.typography.titleSmall,
                        color = neutralColor1
                    )
                    Text(
                        text =  stringResource(R.string.carbs),
                        style = MaterialTheme.typography.labelSmall,
                        color = neutralColor4
                    )
                }
                VerticalDivider()

            }

        }


    }

}

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier
            .width(1.dp)
            .height(36.dp)
            .background(color = neutralColor4)
    )
}




@Composable
@Preview(device = Devices.PIXEL_4, showBackground = true)
fun DetailContentPreview() {
    val dummyData = Data(
        imgUrl = "",
        sumber = "Sumber",
        namaMakananClean = "Nama Makanan Clean",
        sayur = "Sayur",
        dagingAyam = "Daging Ayam",
        bijiBijian = "Biji Bijian",
        buah = "Buah",
        dagingKambing = "Daging Kambing",
        beras = "Beras",
        protein = "Protein",
        tepung = "Tepung",
        tipe = "Tipe",
        kedelai = "Kedelai",
        lemak = "Lemak",
        dagingSapi = "Daging Sapi",
        namaMakanan = "Nama Makanan",
        dagingKerbau = "Daging Kerbau",
        dagingBabi = "Daging Babi",
        foodId = "1", // Ganti dengan ID sesuai kebutuhan
        kalori = "Kalori",
        umbiUmbian = "Umbi Umbian",
        karbohidrat = "Karbohidrat",
        susu = "Susu",
        jenisOlahan = "Jenis Olahan",
        telurAyam = "Telur Ayam",
        fastFood = "Fast Food",
        ikan = "Ikan"
    )
    DetailContent(data = dummyData)
}


