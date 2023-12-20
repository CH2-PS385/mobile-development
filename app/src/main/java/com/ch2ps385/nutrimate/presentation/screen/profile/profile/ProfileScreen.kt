package com.ch2ps385.nutrimate.presentation.screen.profile.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.UserData
import com.ch2ps385.nutrimate.data.remote.responses.DataGetUser
import com.ch2ps385.nutrimate.di.Injection
import com.ch2ps385.nutrimate.presentation.screen.auth.signin.GoogleAuthUiClient
import com.ch2ps385.nutrimate.presentation.screen.user.UserViewModelFactory
import com.ch2ps385.nutrimate.presentation.screen.user.detailmenu.MenuDetailViewModel
import com.ch2ps385.nutrimate.presentation.screen.user.detailmenu.VerticalDivider
import com.ch2ps385.nutrimate.presentation.ui.component.other.CircularProgressAnimated
import com.ch2ps385.nutrimate.presentation.ui.component.other.ProfileOption
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor3
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor4
import com.google.android.gms.auth.api.identity.Identity
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    userData: UserData?,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel : ProfileViewModel = viewModel(
        factory = UserViewModelFactory(Injection.provideUserRepository(LocalContext.current))
    ),
){
    val coroutineScope = rememberCoroutineScope()
//    val applicationContext = LocalContext.current
//    val googleAuthUiClient by lazy {
//        GoogleAuthUiClient(
//            context = applicationContext,
//            oneTapClient = Identity.getSignInClient(applicationContext)
//        )
//    }

        viewModel.stateGetDataPreference.collectAsState(initial = Resource.Loading()).value.let {state ->
            when(state){
                is Resource.Loading ->{
                    CircularProgressAnimated()
                    viewModel.getDataUserPref(userData?.email!!)
                }
                is Resource.Success -> {
                    val result = state.data
                    if (result != null) {
                        ProfileContent(userData = userData, navController = navController, prefData = result, coroutineScope = coroutineScope )
                    }
                }

                is Resource.Error -> {

                }
                else -> {}
            }
        }
//
//    Column(
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//            .fillMaxSize()
//    ) {
//        Spacer(modifier = Modifier.height(24.dp))
//        Text(
//            text = stringResource(id = R.string.profile),
//            style = MaterialTheme.typography.headlineSmall,
//            color = neutralColor1,
//            modifier = modifier
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        AsyncImage(
//            model = userData?.profilePictureUrl,
//            contentDescription = "Profile picture",
//            modifier = Modifier
//                .size(150.dp)
//                .clip(CircleShape),
//            contentScale = ContentScale.Crop
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(
////            text = stringResource(id = R.string.name),
//            text = userData?.username ?: stringResource(id = R.string.name),
//            style = MaterialTheme.typography.headlineSmall,
//            modifier = modifier
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(
////            text = stringResource(id = R.string.name),
//            text = userData?.email ?: stringResource(id = R.string.name),
//            style = MaterialTheme.typography.labelMedium,
//            color = neutralColor3,
//            modifier = modifier
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//        Row(
//            modifier = modifier
//                .heightIn(38.dp)
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center,
//        ){
//            VerticalDivider()
//            Column(
//                verticalArrangement = Arrangement.Top,
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = modifier
//                    .padding(horizontal = 16.dp)
//                    .heightIn(40.dp)
//                    .widthIn(48.dp)
//            ) {
//                Text(
////                    text = stringResource(id = R.string.height_value, "175"),
//                    text = .data?.height.toString(),
//                    style = MaterialTheme.typography.titleMedium,
//                    color = neutralColor1
//                )
//                Text(
//                    text =  stringResource(R.string.height_title),
//                    style = MaterialTheme.typography.labelSmall,
//                    color = neutralColor4
//                )
//            }
//            VerticalDivider()
//            Column(
//                verticalArrangement = Arrangement.Top,
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = modifier
//                    .padding(horizontal = 16.dp)
//                    .heightIn(40.dp)
//                    .widthIn(48.dp)
//            ) {
//                Text(
////                    text = stringResource(R.string.weight_value, "67"),
//                    text = getDataPreference.data?.weight.toString(),
//                    style = MaterialTheme.typography.titleMedium,
//                    color = neutralColor1
//                )
//                Text(
//                    text =  stringResource(R.string.weight_title),
//                    style = MaterialTheme.typography.labelSmall,
//                    color = neutralColor4
//                )
//            }
//            VerticalDivider()
//            Column(
//                verticalArrangement = Arrangement.Top,
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = modifier
//                    .padding(horizontal = 16.dp)
//                    .heightIn(40.dp)
//                    .widthIn(48.dp)
//            ) {
//                Text(
////                    text = stringResource(R.string.age_value, "27"),
//                    text = getDataPreference.data?.age.toString(),
//                    style = MaterialTheme.typography.titleMedium,
//                    color = neutralColor1
//                )
//                Text(
//                    text =  stringResource(R.string.age_title),
//                    style = MaterialTheme.typography.labelSmall,
//                    color = neutralColor4
//                )
//            }
//            VerticalDivider()
//        }
//        Spacer(modifier = Modifier.height(24.dp))
//        Column(
//
//        ){
////            ProfileOption(
////                icon = painterResource(id = R.drawable.baseline_person_24),
////                text = stringResource(id = R.string.personal_data),
////                onClick = {
////                    navController.navigate(Screen.ProfileDetail.route)
////                }
////            )
////            Spacer(modifier = Modifier.height(16.dp))
//            ProfileOption(
//                icon = painterResource(id = R.drawable.baseline_settings_24),
//                text = stringResource(id = R.string.change_preferences),
//                onClick = {
//                    navController.navigate(Screen.ProfileDetail.route)
//                }
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            ProfileOption(
//                icon = painterResource(id = R.drawable.baseline_info_24),
//                text = stringResource(id = R.string.about),
//                onClick = {
//                    navController.navigate(Screen.About.route)
//                }
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            ProfileOption(
//                icon = painterResource(id = R.drawable.baseline_logout_24),
//                text = stringResource(id = R.string.sign_out),
//                onClick = {
//                        coroutineScope.launch {
//                            googleAuthUiClient.signOut()
//                            Toast.makeText(
//                                applicationContext,
//                                "Signed out",
//                                Toast.LENGTH_LONG
//                            ).show()
////                            navController.popBackStack(Screen.SignIn.route, true)
//                            navController.popBackStack()
////                            navController.navigate(Screen.SignIn.route)
//                        }
//                }
//            )
//
//        }
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    userData: UserData?,
    modifier: Modifier = Modifier,
    navController: NavController,
    prefData : DataGetUser,
    coroutineScope:CoroutineScope
){
    val applicationContext = LocalContext.current
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    val successAddState = rememberUseCaseState()
    val successConfigState = State.Success(labelText = "Yeay, logged out succeed")
    StateDialog(
        state = successAddState,
        config = StateConfig(state = successConfigState),
    )

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.profile),
            style = MaterialTheme.typography.headlineSmall,
            color = neutralColor1,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(16.dp))
        AsyncImage(
            model = userData?.profilePictureUrl,
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
//            text = stringResource(id = R.string.name),
            text = userData?.username ?: stringResource(id = R.string.name),
            style = MaterialTheme.typography.headlineSmall,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
//            text = stringResource(id = R.string.name),
            text = userData?.email ?: stringResource(id = R.string.name),
            style = MaterialTheme.typography.labelMedium,
            color = neutralColor3,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = modifier
                .heightIn(38.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ){
            VerticalDivider()
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .heightIn(40.dp)
                    .widthIn(48.dp)
            ) {
                Text(
//                    text = stringResource(id = R.string.height_value, "175"),
                    text = prefData.height.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = neutralColor1
                )
                Text(
                    text =  stringResource(R.string.height_title),
                    style = MaterialTheme.typography.labelSmall,
                    color = neutralColor4
                )
            }
            VerticalDivider()
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .heightIn(40.dp)
                    .widthIn(48.dp)
            ) {
                Text(
//                    text = stringResource(R.string.weight_value, "67"),
                    text = prefData.weight.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = neutralColor1
                )
                Text(
                    text =  stringResource(R.string.weight_title),
                    style = MaterialTheme.typography.labelSmall,
                    color = neutralColor4
                )
            }
            VerticalDivider()
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .heightIn(40.dp)
                    .widthIn(48.dp)
            ) {
                Text(
//                    text = stringResource(R.string.age_value, "27"),
                    text = prefData.age.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = neutralColor1
                )
                Text(
                    text =  stringResource(R.string.age_title),
                    style = MaterialTheme.typography.labelSmall,
                    color = neutralColor4
                )
            }
            VerticalDivider()
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column(

        ){
//            ProfileOption(
//                icon = painterResource(id = R.drawable.baseline_person_24),
//                text = stringResource(id = R.string.personal_data),
//                onClick = {
//                    navController.navigate(Screen.ProfileDetail.route)
//                }
//            )
//            Spacer(modifier = Modifier.height(16.dp))
            ProfileOption(
                icon = painterResource(id = R.drawable.baseline_settings_24),
                text = stringResource(id = R.string.change_preferences),
                onClick = {
                    navController.navigate(Screen.EditUserPreferences.route)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileOption(
                icon = painterResource(id = R.drawable.baseline_info_24),
                text = stringResource(id = R.string.about),
                onClick = {
                    navController.navigate(Screen.About.route)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileOption(
                icon = painterResource(id = R.drawable.baseline_logout_24),
                text = stringResource(id = R.string.sign_out),
                onClick = {
                    coroutineScope.launch {
                        googleAuthUiClient.signOut()
                        successAddState.show()
                        delay(2000)
                        successAddState.finish()
                        navController.popBackStack(Screen.Home.route, true)
                        navController.navigate(Screen.WelcomeScreen.route)
                    }
                }
            )

        }
    }
}

@Composable
@Preview(device = Devices.PIXEL_4, showBackground = true)
fun ProfileScreenPreview() {
    NutriMateTheme {
//        ProfileScreen(
//            userData = UserData(
//                userId = "123",
//                username = "John Doe",
//                profilePictureUrl = ""
//                // Anda bisa menambahkan properti lain sesuai kebutuhan
//            )
//        )
    }
}
