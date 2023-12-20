package com.ch2ps385.nutrimate.presentation.ui.component.other

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FetchLoading(closeSelection: () -> Unit) {

    val sheetState = rememberUseCaseState(visible = false, onCloseRequest = { closeSelection() })

    val state = remember {
        val startState =
            State.Loading(labelText = "Generate the new meal planner.", ProgressIndicator.Circular())
        mutableStateOf<State>(startState)
    }
    LaunchedEffect(Unit) {
        sheetState.show()
        delay(30000)
        state.value = State.Failure(labelText = "Fetching data failed. Trying again.")
        delay(2000)
        state.value =
            State.Loading(labelText = "Fetching new data...", ProgressIndicator.Circular())
        delay(2000)
        state.value = State.Success(labelText = "Data fetched..!")
        delay(2000)
        sheetState.hide()
    }

    StateDialog(
        state = sheetState,
        config = StateConfig(state = state.value)
    )
}