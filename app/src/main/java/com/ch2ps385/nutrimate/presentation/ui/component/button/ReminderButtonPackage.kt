package com.ch2ps385.nutrimate.presentation.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.presentation.ui.component.NineRadioButtonGroup
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.pSinopia
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.sBabyPink
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite

@Composable
fun ReminderButtonPackage(

){
    var selectedButtonIndex by remember { mutableStateOf(-1) }

    val mlValues = (100..550 step 50).toList()

    // Menentukan apakah tombol "Add" dapat diaktifkan
    val isAddButtonEnabled = selectedButtonIndex != -1

    // Daftar nilai yang telah dipilih
    val selectedValues = remember { mutableStateOf<List<Int>>(emptyList()) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        repeat(3) { row ->
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                repeat(3) { column ->
                    val index = row * 3 + column
                    val isSelected = index == selectedButtonIndex

                    val mlText = "${mlValues[index]} ml"

                    Box(
                        modifier = Modifier
                            .widthIn(104.dp)
                            .height(48.dp)
                            .padding(4.dp)
                            .shadow(elevation = 1.dp,shape = Shapes.medium, spotColor = pSmashedPumpkin, ambientColor = pSmashedPumpkin)
                            .clip(Shapes.medium)
                            .background(if (isSelected) pSmashedPumpkin else solidWhite)
                            .border(
                                width = 1.dp,
                                shape = Shapes.medium,
                                color = if (!isSelected) sBabyPink else pSmashedPumpkin
                            )
                            .clickable {
                                selectedButtonIndex = index
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(Shapes.medium)
                                .background(if (isSelected) pSmashedPumpkin else solidWhite)
                        )
                        Text(
                            text = mlText,
                            color = (if (isSelected) solidWhite else neutralColor1),
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontWeight = FontWeight(600),)
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
            colors = ButtonDefaults.buttonColors(
                containerColor = pSinopia
            ),
            enabled = isAddButtonEnabled,
            modifier = Modifier
                .width(240.dp)
                .height(56.dp)
                .padding(top = 16.dp)

        ) {
            Text(
                text = "Add",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.inter_bold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFAF2E9),
                    textAlign = TextAlign.Center,)
            )
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
fun ReminderButtonPackagePreview() {
    NutriMateTheme {
        ReminderButtonPackage()
    }
}