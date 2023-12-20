package com.ch2ps385.nutrimate.presentation.screen.user.preferences

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.common.Constants
import com.ch2ps385.nutrimate.common.Constants.foodItems
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.UserData
import com.ch2ps385.nutrimate.data.remote.model.AddAllergies
import com.ch2ps385.nutrimate.data.remote.model.AddUserPreferences
import com.ch2ps385.nutrimate.di.Injection
import com.ch2ps385.nutrimate.presentation.screen.profile.editpreferences.EditUserPreferencesViewModel
import com.ch2ps385.nutrimate.presentation.screen.user.UserViewModelFactory
import com.ch2ps385.nutrimate.presentation.ui.component.button.CustomCheckBoxButton
import com.ch2ps385.nutrimate.presentation.ui.component.button.GenderButtonPackage
import com.ch2ps385.nutrimate.presentation.ui.component.other.FetchLoading
import com.ch2ps385.nutrimate.presentation.ui.component.textfields.TextFieldsPreferences
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.pSinopia
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserPreferencesScreen(
    userData : UserData?,
    navController: NavController, // Added callback for navigation to home
    viewModel : EditUserPreferencesViewModel = viewModel(
        factory = UserViewModelFactory(Injection.provideUserRepository(LocalContext.current))
    ),
    modifier : Modifier = Modifier,
){

    val successAddState = rememberUseCaseState()
    val successConfigState = State.Success(labelText = "Data Preferences has been saved!")
    StateDialog(
        state = successAddState,
        config = StateConfig(state = successConfigState),
    )

    val failedAddState = rememberUseCaseState()
    val  failedConfigState = State.Failure(labelText = "Data preferences hasn't been save!")

    StateDialog(
        state = failedAddState,
        config = StateConfig(state = failedConfigState),
    )


    viewModel.stateUserPreferences.collectAsState().value.let {state ->
        when(state){
            is Resource.Initial -> {}
            is Resource.Loading ->{
                Log.d(ContentValues.TAG, "Loading of Saving user preferences...")
                if (viewModel.isDialogLoadingShown.value) {
                    FetchLoading(closeSelection = { viewModel.onDismissLoadingDialog() })
                } else {
                    Log.d(ContentValues.TAG, "Move to else branch...")
                }
            }
            is Resource.Success -> {
                LaunchedEffect(Unit){
                    if(viewModel.isDialogShown.value){
                        successAddState.show()
                        delay(3000)
                        successAddState.finish()
                        viewModel.onDismissDialog()
                    }
                }
            }
            is Resource.Error -> {
                LaunchedEffect(Unit){
                    if(viewModel.isDialogShown.value){
                        failedAddState.show()
                        delay(3000)
                        failedAddState.finish()
                        viewModel.onDismissDialog()
                    }
                }
            }
        }
    }

    LaunchedEffect(viewModel){
        viewModel.getDataUserPref(userData?.email!!)
    }
    viewModel.stateGetDataPreference.collectAsState().value.let { state ->
        when(state){
            is Resource.Initial -> {}
            is Resource.Loading ->{
                Log.d(ContentValues.TAG, "Loading of Saving user preferences...")
                if (viewModel.isDialogLoadingShown.value) {
                    FetchLoading(closeSelection = { viewModel.onDismissLoadingDialog() })
                } else {
                    Log.d(ContentValues.TAG, "Move to else branch...")
                }
            }
            is Resource.Success -> {
//                sucessAddState.show()
                viewModel.age.value = state.data?.age.toString()
                viewModel.height.value = state.data?.height.toString()
                viewModel.weight.value = state.data?.weight.toString()
                viewModel.setGender(state.data?.gender)
                viewModel.setFoodPreferenceGet(state.data?.allergies)


                Log.d("TAG", "Berhasil disubmit save preferencesnya!")
                EditUserPreferencesContent(viewModel = viewModel, email = userData?.email!!)
            }
            is Resource.Error -> {
                failedAddState.show()
            }
        }
    }
}

@Composable
fun EditUserPreferencesContent(
    viewModel: EditUserPreferencesViewModel,
    modifier : Modifier = Modifier,
    email:String
){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
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
                    onClick = { viewModel.setGender("m") }
                )

                GenderButtonPackage(
                    text = "Perempuan",
                    isSelected = viewModel.gender.value == Constants.Gender.Female,
                    onClick = { viewModel.setGender("f") }
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
                            viewModel.setFoodPreference(index, newChecked, email)
                        },
                        index = index
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                val height = viewModel.height.value
                val weight = viewModel.weight.value
                val age = viewModel.age.value
                val gender = if (viewModel.gender.value == Constants.Gender.Male) "m" else "f"
                val allergies = foodItems.filterIndexed { index, _ ->
                    viewModel.foodPreferences.value[index]
                }


                Log.d("UserPreferenceScreen", "Height: $height, Weight: $weight, Age: $age, Gender: $gender, Allergies: $allergies")
                // Use the collected data as needed, for example, pass it to your ViewModel function
                viewModel.addUserPreferences(
                    AddUserPreferences(
                        email = email,
                        height = height.toInt(),
                        weight = weight.toInt(),
                        age = age.toInt(),
                        gender = gender,
                    )
                )
                viewModel.addAllergies(
                    AddAllergies(
                        email = email,
                        allergies = allergies
                    )
                )
                viewModel.onSavePreferencesClick()


            },
            colors = ButtonDefaults.buttonColors(containerColor = pSinopia),
            modifier = modifier
                .width(312.dp)
                .height(40.dp),

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
