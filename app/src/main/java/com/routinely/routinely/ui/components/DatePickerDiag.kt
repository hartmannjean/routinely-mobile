package com.routinely.routinely.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import com.routinely.routinely.ui.theme.Gray80
import com.routinely.routinely.ui.theme.GrayRoutinely
import com.routinely.routinely.ui.theme.PurpleRoutinely
import com.routinely.routinely.util.validators.DateTimeInputValid

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Composable
fun DatePickerDiag(
    onValueChange: (String) -> Unit,
    labelRes: String,
    error: DateTimeInputValid,
    modifier: Modifier
) {
    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState(yearRange = 2013..2023)
    var selectedDate by remember {
        mutableStateOf("")
    }
    val confirmEnabled by remember { derivedStateOf { datePickerState.selectedDateMillis != null } }

    when {
        showDatePickerDialog -> {
            val clearFocus = LocalFocusManager.current.clearFocus()
            DatePickerDialog(
                onDismissRequest = {
                    showDatePickerDialog = false
                    clearFocus
                },
                confirmButton = {
                    Button(
                        onClick = {
                            datePickerState
                                .selectedDateMillis?.let { millis ->
                                    selectedDate = millis.toBrazilianDateFormat()
                                    onValueChange(millis.toApiDateFormat())
                                }
                            showDatePickerDialog = false
                        },
                        enabled = confirmEnabled
                    ) {
                        Text(text = "Confirma")
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = {
                            showDatePickerDialog = false
                        }) {
                        Text(text = "Cancela")
                    }
                },
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false
                )
            }
        }
    }

    OutlinedTextField(
        onValueChange = {},
        value = selectedDate.takeIf { it.isNotEmpty() } ?: "__/__/____",
        label = {
            Text(
                text = labelRes,
                style = TextStyle(color = Color.Black)
            )
        },
        isError = error is DateTimeInputValid.Error,
        supportingText = {
            if (error is DateTimeInputValid.Error) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Formato inválido!",
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Gray80,
            unfocusedTextColor = Gray80,
            focusedBorderColor = PurpleRoutinely,
            unfocusedBorderColor = GrayRoutinely
        ),
        readOnly = true,
        modifier = modifier
            .onFocusEvent {
                if (it.isFocused)
                    showDatePickerDialog = true
            }
    )
}
