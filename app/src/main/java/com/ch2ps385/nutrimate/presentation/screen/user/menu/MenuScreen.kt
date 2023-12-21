package com.ch2ps385.nutrimate.presentation.screen.user.menu

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.responses.DataItem
import com.ch2ps385.nutrimate.di.Injection
import com.ch2ps385.nutrimate.presentation.screen.user.UserViewModelFactory
import com.ch2ps385.nutrimate.presentation.ui.component.carditem.CardMenuItemGrid
import com.ch2ps385.nutrimate.presentation.ui.component.other.CircularProgressAnimated
import com.ch2ps385.nutrimate.presentation.ui.component.other.SearchBar
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor4


@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    viewModel :  MenuViewModel = viewModel(
        factory = UserViewModelFactory(Injection.provideUserRepository(LocalContext.current))
    ),
    navigateToDetail: (Long) -> Unit,
){
    val query by viewModel.query

    Column(
        modifier = modifier
            .padding(vertical = 24.dp, horizontal = 0.dp)
    ) {
        Text(
            text ="Menu",
            style = MaterialTheme.typography.headlineSmall,
            modifier = modifier
                .padding(start = 24.dp)
        )
        SearchBar(
            query = query,
            onQueryChange = { newQuery ->
                viewModel.search(newQuery)
            },
            modifier = modifier
        )
        Log.d(TAG, "MENU SCREEN SHOW")

        LaunchedEffect(viewModel) {
            viewModel.getAllMenu()
        }
        viewModel.state.collectAsState(initial = Resource.Loading()).value.let { state ->
            when(state){
                is Resource.Loading -> {
//                    CustomLinearProgressBar()
                    CircularProgressAnimated()
                }
                is Resource.Success -> {
                    state.data?.let {
                        state.data?.let { MenuContent(menuList = it, navigateToDetail = navigateToDetail) }
                    }
                }
                is Resource.Error -> {
                    Log.d(TAG, "Tidak dapat mmenampilkan data!")
                }
                else -> {}
            }
        }
    }
}


@Composable
fun MenuContent(
    menuList: List<DataItem>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    ){
    if(menuList.isNotEmpty()){
        LazyVerticalGrid(
            columns = GridCells.Adaptive(130.dp),
            contentPadding = PaddingValues(24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ){
            items(menuList){ menu ->
                CardMenuItemGrid(
                    menu = menu,
                    modifier = Modifier
                        .clickable {
                            navigateToDetail(menu.foodId.toLong())
                        }
                    )
            }
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.no_results),
                contentDescription = "No Result Image",
                modifier = Modifier.height(128.dp).width(128.dp).graphicsLayer(alpha = 0.25f)
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

