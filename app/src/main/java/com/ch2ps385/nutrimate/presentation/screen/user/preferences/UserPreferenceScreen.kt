package com.ch2ps385.nutrimate.presentation.screen.user.preferences

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.common.Constants
import com.ch2ps385.nutrimate.common.Constants.foodItems
import com.ch2ps385.nutrimate.data.remote.UserData
import com.ch2ps385.nutrimate.data.remote.model.AddUserByEmail
import com.ch2ps385.nutrimate.di.Injection
import com.ch2ps385.nutrimate.presentation.screen.user.UserViewModelFactory
import com.ch2ps385.nutrimate.presentation.screen.user.menu.MenuViewModel
import com.ch2ps385.nutrimate.presentation.ui.component.button.CustomCheckBoxButton
import com.ch2ps385.nutrimate.presentation.ui.component.button.GenderButtonPackage
import com.ch2ps385.nutrimate.presentation.ui.component.textfields.TextFieldsPreferences
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.pSinopia
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite


@Composable
fun UserPreferenceScreen(
    userData : UserData?,
    navController: NavController, // Added callback for navigation to home
    viewModel : UserPreferencesViewModel = viewModel(
        factory = UserViewModelFactory(Injection.provideUserRepository(LocalContext.current))
    ),
    modifier : Modifier = Modifier,
){

//    if (userData!= null){
//        viewModel.addUserByEmail(
//            AddUserByEmail(
//                email = userData.email ?: "test", // Provide a default value if email is null
//                name = userData.username ?: "tester"
//            )
//        )
//    }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(29.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "NutriMate employs the Resting Metabolic Rate (RMR) methodology to calculate your calorie allocation, utilizing factors such as height, weight, biological sex, and age as key inputs",
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
                value = viewModel.height.value,
                onValueChange = { newValue ->
                    viewModel.height.value = newValue
                    viewModel.setHeight(newValue)
                },
                unit = "cm",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldsPreferences(
                placeholder = "Weight",
                value = viewModel.weight.value,
                onValueChange = { newValue ->
                    viewModel.weight.value = newValue
                    viewModel.setWeight(newValue)
                },
                unit = "Kg",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldsPreferences(
                placeholder = "Age",
                value = viewModel.age.value,
                onValueChange = { newValue ->
                    viewModel.age.value = newValue
                    viewModel.setAge(newValue)
                },
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
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                GenderButtonPackage(
                    text = "Laki-Laki",
                    isSelected = viewModel.gender.value == Constants.Gender.Male,
                    onClick = { viewModel.setGender(Constants.Gender.Male) }
                )

                GenderButtonPackage(
                    text = "Perempuan",
                    isSelected = viewModel.gender.value == Constants.Gender.Female,
                    onClick = { viewModel.setGender(Constants.Gender.Female) }
                )
            }
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
                text = "Allergies",
                style = MaterialTheme.typography.titleMedium,
                color = neutralColor1,
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
                    .height(515.dp)
            ){
                itemsIndexed(foodItems){ index, item ->
                    CustomCheckBoxButton(
                        text = item,
                        checked =  viewModel.foodPreferences.value[index],
                        onCheckedChange = { newChecked ->
                            viewModel.setFoodPreference(index, newChecked)
                        },
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                    navController.navigate(Screen.Home.route)
                        if (userData!= null){
                            viewModel.addUserByEmail(
                                AddUserByEmail(
                                    email = userData.email!!, // Provide a default value if email is null
                                    name = userData.username!!
                                )
                            )
                        }
                      },
            colors = ButtonDefaults.buttonColors(containerColor = pSinopia),
            modifier = modifier
                .width(312.dp)
                .height(40.dp)
        ) {
            Image(
                painterResource(id = R.drawable.ic_save),
                contentDescription = "Cart button icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Done",
                Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}


//        Column(
//            verticalArrangement = Arrangement.Top,
//            horizontalAlignment = Alignment.Start,
//            modifier = modifier
//                .padding(horizontal = 29.dp)
//                .fillMaxWidth()
//        ) {
//            Text(
//                text = "Gender",
//                style = MaterialTheme.typography.titleMedium,
//                color = neutralColor1,
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .padding(8.dp)
//                    .fillMaxWidth()
//                    .border(width = 1.dp, color = pSmashedPumpkin, shape = Shapes.large)
//            ) {
//                RadioButton(
//                    selected = viewModel.gender.value == Gender.Male,
//                    onClick = {
//                        viewModel.setGender(Gender.Male)
//                    },
//                    colors = RadioButtonDefaults.colors(
//                        selectedColor = pSmashedPumpkin
//                    )
//                )
//                Text(
//                    text = "Laki-Laki",
//                    color = neutralColor1,
//                    style = MaterialTheme.typography.labelMedium
//                )
//            }
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .padding(8.dp)
//                    .fillMaxWidth()
//                    .border(width = 1.dp, color = pSmashedPumpkin, shape = Shapes.large)
//            ) {
//                RadioButton(
//                    selected = viewModel.gender.value == Gender.Female,
//                    onClick = {
//                        viewModel.setGender(Gender.Female)
//                    },
//                )
//                Text(
//                    text = "Perempuan",
//                    color = neutralColor1,
//                    style = MaterialTheme.typography.labelMedium
//                )
//            }
//        }



//foodItems.forEachIndexed { index, foodItem ->
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth()
//            .border(width = 1.dp, color = pSmashedPumpkin, shape = Shapes.large)
//    ) {
//        Checkbox(
//            checked = viewModel.foodPreferences.value[index],
//            onCheckedChange = { newChecked ->
//                viewModel.setFoodPreference(index, newChecked)
//            },
//            colors = CheckboxDefaults.colors(
//                checkmarkColor = solidWhite,
//                checkedColor = pSmashedPumpkin
//            ),
//            modifier = modifier
//                .clip(shape = RoundedCornerShape(100))
//        )
//        Text(
//            text = foodItem,
//            color = neutralColor1,
//            style = MaterialTheme.typography.labelMedium,
//            modifier = Modifier.padding(start = 8.dp)
//        )
//    }
//}
//}
