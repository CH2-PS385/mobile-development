package com.ch2ps385.nutrimate.presentation.screen.auth.register

//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Check
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//@Composable
//fun CheckboxButton(
//    checked: Boolean,
//    onCheckedChange: (Boolean) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    val updatedState by rememberUpdatedState(newValue = checked)
//
//    Button(
//        onClick = { onCheckedChange(!updatedState) },
//        modifier = modifier,
//        colors = ButtonDefaults.buttonColors(
//            containerColor = if (checked) MaterialTheme.colorScheme.primary else Color.Gray,
//            contentColor = if (checked) Color.White else MaterialTheme.colorScheme.primary
//        )
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(4.dp)
//        ) {
//            if (checked) {
//                Icon(
//                    imageVector = Icons.Default.Check,
//                    contentDescription = null,
//                    modifier = Modifier.size(18.dp)
//                )
//            }
//
//            Text(
//                text = "Checkbox Button",
//                color = if (checked) Color.White else MaterialTheme.colorScheme.primary
//            )
//        }
//    }
//}
//
//@Composable
//fun CheckboxList(
//    checkboxes: List<Boolean>,
//    onCheckedChange: (List<Boolean>) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        modifier = modifier
//    ) {
//        checkboxes.chunked(3) { rowCheckboxes ->
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                for ((index, isChecked) in rowCheckboxes.withIndex()) {
//                    CheckboxButton(
//                        checked = isChecked,
//                        onCheckedChange = {
//                            val updatedCheckboxes = checkboxes.toMutableList()
//                            val absoluteIndex = checkboxes.indexOf(rowCheckboxes[0]) + index
//                            updatedCheckboxes[absoluteIndex] = it
//                            onCheckedChange(updatedCheckboxes)
//                        },
//                        modifier = Modifier.weight(1f)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun CheckboxListPreview() {
//    var checkboxes by remember { mutableStateOf(List(9) { false }) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        CheckboxList(
//            checkboxes = checkboxes,
//            onCheckedChange = { checkboxes = it }
//        )
//    }
//}