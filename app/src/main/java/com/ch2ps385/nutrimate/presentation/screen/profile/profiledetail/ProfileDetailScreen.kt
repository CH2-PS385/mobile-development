package com.ch2ps385.nutrimate.presentation.screen.profile.profiledetail

import android.widget.Toast
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.data.remote.UserData
import com.ch2ps385.nutrimate.presentation.screen.auth.signin.GoogleAuthUiClient
import com.ch2ps385.nutrimate.presentation.screen.user.detailmenu.VerticalDivider
import com.ch2ps385.nutrimate.presentation.ui.component.other.ProfileOption
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor4
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@Composable
fun ProfileDetailScreen(
    userData: UserData?,
    navController: NavController,
    modifier: Modifier = Modifier,
){

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
            text = userData?.email?: stringResource(id = R.string.name),
            style = MaterialTheme.typography.headlineSmall,
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
                    text = stringResource(id = R.string.height_value),
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
                    text = stringResource(R.string.weight_value),
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
                    text = stringResource(R.string.age_value),
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
    }
}