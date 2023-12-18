package com.ch2ps385.nutrimate.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme


@Composable
fun NineRadioButtonGroup() {
    var selectedButtonIndex by remember { mutableStateOf(-1) }

    // Daftar nilai dalam ml
    val mlValues = (100..550 step 50).toList()

    // Menentukan apakah tombol "Add" dapat diaktifkan
    val isAddButtonEnabled = selectedButtonIndex != -1

    // Daftar nilai yang telah dipilih
    val selectedValues = remember { mutableStateOf<List<Int>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        repeat(3) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                repeat(3) { column ->
                    val index = row * 3 + column
                    val isSelected = index == selectedButtonIndex

                    val mlText = "${mlValues[index]} ml"

                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(40.dp)
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray)
                            .clickable {
                                selectedButtonIndex = index
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray)
                        )
                        Text(
                            text = mlText,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }

        // Tombol "Add" yang diaktifkan/dinonaktifkan berdasarkan pemilihan pengguna
        Button(
            onClick = {
                // Tambahkan nilai yang dipilih ke dalam daftar
                if (selectedButtonIndex != -1) {
                    selectedValues.value = selectedValues.value + mlValues[selectedButtonIndex]
                    selectedButtonIndex = -1 // Reset pemilihan
                }
            },
            enabled = isAddButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Add")
        }

        // Tampilkan nilai-nilai yang telah dipilih
        Text(
            text = "Selected Values: ${selectedValues.value.joinToString(", ")}",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun NineRadioButtonGroupPreview() {
    NutriMateTheme {
        NineRadioButtonGroup()
    }
}