package com.routinely.routinely.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.LabelError(
    labelRes: String,
) {
    Text(
        modifier = Modifier
            .padding(vertical = 15.dp)
            .align(Alignment.End),
        text = labelRes,
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        color = MaterialTheme.colorScheme.error
    )

}

@Preview(showBackground = true)
@Composable
fun LabelErrorPreview() {
    Column {
        LabelError(
            labelRes = "Error"
        )
    }
}