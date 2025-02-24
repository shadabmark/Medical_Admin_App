package com.example.medicalapplicationadminapp.utility

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun AnnotatedText(label: String, value: String, valueColor: Color) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color.White)) {
                append("$label: ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = valueColor)) {
                append(value)
            }
        },
        fontSize = 16.sp
    )
}