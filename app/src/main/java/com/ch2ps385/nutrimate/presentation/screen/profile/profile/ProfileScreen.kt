package com.ch2ps385.nutrimate.presentation.screen.profile.profile

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.presentation.screen.user.detailmenu.VerticalDivider
import com.ch2ps385.nutrimate.presentation.ui.component.other.ProfileOption
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor4

@Composable
fun ProfileScreen(
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
        Image(
            painter = painterResource(id = R.drawable.menu1),
            contentDescription = "test",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
        )
        //        AsyncImage(
//            model = userData.profilePictureUrl,
//            contentDescription = "Profile picture",
//            modifier = Modifier
//                .size(150.dp)
//                .clip(CircleShape),
//            contentScale = ContentScale.Crop
//        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.name),
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
        Spacer(modifier = Modifier.height(24.dp))
        Column(

        ){
            ProfileOption(
                icon = painterResource(id = R.drawable.baseline_person_24),
                text = stringResource(id = R.string.personal_data),
                onClick = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileOption(
                icon = painterResource(id = R.drawable.baseline_settings_24),
                text = stringResource(id = R.string.change_preferences),
                onClick = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileOption(
                icon = painterResource(id = R.drawable.baseline_info_24),
                text = stringResource(id = R.string.about),
                onClick = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileOption(
                icon = painterResource(id = R.drawable.baseline_logout_24),
                text = stringResource(id = R.string.sign_out),
                onClick = {}
            )

        }
    }
}

@Composable
@Preview(device = Devices.PIXEL_4, showBackground = true)
fun ProfileScreenPreview() {
    NutriMateTheme {
        ProfileScreen()
    }
}
