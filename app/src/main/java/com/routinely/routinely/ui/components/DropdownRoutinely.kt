package com.routinely.routinely.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.routinely.routinely.ui.theme.Gray80
import com.routinely.routinely.ui.theme.GrayRoutinely
import com.routinely.routinely.ui.theme.PurpleRoutinely
import com.routinely.routinely.ui.theme.RoutinelyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownRoutinely(
    labelRes: String,
    onValueChange: (String) -> Unit,
    list: List<String>,
    optionColors: Map<String,
            Color>? = null,
    modifier: Modifier = Modifier// Parâmetro opcional para as cores
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val listWithLabel = listOf(labelRes) + list
    var selectedOptionText by remember { mutableStateOf(listWithLabel[0]) }
    var selectedOptionColor by remember { mutableStateOf(optionColors?.get(selectedOptionText) ?: Color.Black) }
    ExposedDropdownMenuBox(
        modifier = modifier.fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = selectedOptionText.takeIf { it.isNotEmpty() } ?: labelRes,
            onValueChange = {  },
            label = {
                Text(
                    text = labelRes,
                    style = TextStyle(color = Color.Black)
                )
            },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Gray80,
                unfocusedTextColor = Gray80,
                focusedBorderColor = PurpleRoutinely,
                unfocusedBorderColor = GrayRoutinely
            ),
            textStyle = TextStyle(color = selectedOptionColor) // Defina a cor do texto aqui
        )

        ExposedDropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            listWithLabel.forEachIndexed { index, selectionOption ->
                val isGrayedOut = index == 0
                val textColor = if (!isGrayedOut) {
                    optionColors?.get(selectionOption) ?: Color.DarkGray
                } else {
                    Color.Gray
                }
                DropdownMenuItem(
                    text = {
                        Text(
                            selectionOption,
                            color = if (!isGrayedOut) {
                                Color.DarkGray
                            } else {
                                Color.Gray
                            }
                        )
                    },
                    onClick = {
                        if (!isGrayedOut) {
                            selectedOptionText = selectionOption
                            selectedOptionColor = textColor // Atualize a cor do texto
                            onValueChange(selectedOptionText)
                        }
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    enabled = !isGrayedOut
                )
            }
        }
    }
}
@Preview(showBackground = false)
@Composable
fun DropdownRoutinelyPreview() {
    RoutinelyTheme {
        DropdownRoutinely("Teste", onValueChange = {}, listOf("Opção 1", "Opção 2") )
    }
}