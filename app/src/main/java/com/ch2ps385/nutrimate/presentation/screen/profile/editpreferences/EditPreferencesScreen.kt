package com.ch2ps385.nutrimate.presentation.screen.profile.editpreferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.data.remote.UserData
import com.ch2ps385.nutrimate.presentation.ui.component.textfields.TextFieldsPreferences
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite

@Composable
fun EditPrefrencesScreen(
    navController: NavController, // Added callback for navigation to home
    modifier : Modifier = Modifier,
){

    var height by remember {
        mutableStateOf("")
    }
    var weight by remember {
        mutableStateOf("")
    }
    var age by remember {
        mutableStateOf("")
    }

    var foodPreferences by remember {
        mutableStateOf(List(15) { false })
    }


    var gender by remember { mutableStateOf(Gender.Male) }


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(29.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Lorem ipsum dolor sit amet consectetur. Vitae sagittis sagittis pellentesque egestas ac aenean ut eu. Imperdiet nisi pulvinar neque massa posuere maecenas.",
            maxLines = 4,
            style = TextStyle(
                fontSize = 10.sp,
                lineHeight = 16.sp,
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                fontWeight = FontWeight(500),
                color = neutralColor1,
                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextFieldsPreferences(
                placeholder = "Height",
                value = height,
                onValueChange = { newValue -> height = newValue },
                unit = "cm",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldsPreferences(
                placeholder = "Weight",
                value = weight,
                onValueChange = { newValue -> weight = newValue },
                unit = "Kg",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldsPreferences(
                placeholder = "Age",
                value = age,
                onValueChange = { newValue -> age = newValue },
                unit = "yo",
                modifier = Modifier
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .padding(horizontal = 29.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Gender",
                style = MaterialTheme.typography.titleMedium,
                color = neutralColor1,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                RadioButton(
                    selected = gender == Gender.Male,
                    onClick = { gender = Gender.Male },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = pSmashedPumpkin
                    )
                )
                Text(text = "Laki-Laki", color = neutralColor1, style = MaterialTheme.typography.labelMedium)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                RadioButton(
                    selected = gender == Gender.Female,
                    onClick = { gender = Gender.Female },
                )
                Text(text = "Perempuan", color = neutralColor1, style = MaterialTheme.typography.labelMedium)
            }
        }
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .padding(horizontal = 29.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Allergies",
                style = MaterialTheme.typography.titleMedium,
                color = neutralColor1,
            )

            val foodItems = listOf(
                "Daging Kerbau",
                "Beras",
                "Biji-bijian",
                "Buah",
                "Daging Ayam",
                "Daging Babi",
                "Daging Kambing",
                "Daging Sapi",
                "Ikan",
                "Kedelai",
                "Sayur",
                "Susu",
                "Telur Ayam",
                "Tepung",
                "Umbi-umbian"
            )

            foodItems.forEachIndexed { index, foodItem ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Checkbox(
                        checked = foodPreferences[index],
                        onCheckedChange = { newChecked ->
                            foodPreferences = foodPreferences.toMutableList().also {
                                it[index] = newChecked
                            }
                        },
                        colors = CheckboxDefaults.colors(checkmarkColor = solidWhite,    checkedColor = pSmashedPumpkin)
                    )
                    Text(
                        text = foodItem,
                        color = neutralColor1,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
        Button(onClick = {  navController.navigate(Screen.Home.route) }) {

        }
        



    }

}

enum class Gender {
    Male,
    Female
}

@Composable
@Preview(device = Devices.PIXEL_4, showBackground = true)
fun EditPreferencesScreenPreview() {
    NutriMateTheme {
//        EditPrefrencesScreen(navController = )
    }
}