package com.example.medicalapplicationadminapp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicalapplicationadminapp.ui.theme.SecondMainColor
import com.example.medicalapplicationadminapp.utility.AnnotatedText


@Composable
fun UserCard(
    name: String,
    userID: String,
    phoneNumber: String,
    email: String,
    pinCode: String,
    isApproved: Int,
    block: Int,
    onClickApproved: () -> Unit,
    onClickBlock: () -> Unit,
    onClickUnBlock: () -> Unit,
    onClickDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = SecondMainColor,
            contentColor = Color.White
        )
    ) {
        val statusText = if (isApproved == 0) "Pending" else "Approved"
        val statusTextColor = if (isApproved == 0) Color.Yellow else Color.Green

        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            listOf(
                "Name" to name,
                "User ID" to userID,
                "Phone Number" to phoneNumber,
                "Email" to email,
                "Pin Code" to pinCode
            ).forEach { (label, value) ->
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("$label: ") }
                        append(value)
                    },
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
            }
            AnnotatedText("Status", statusText, statusTextColor)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onClickDelete,
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(text = "Delete", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                }
                when {
                    isApproved == 0 -> {
                        Button(
                            onClick = onClickApproved,
                            colors = ButtonDefaults.buttonColors(Color.Green)
                        ) {
                            Text(
                                text = "Approve",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            )
                        }
                    }

                    block == 0 -> {
                        Button(
                            onClick = onClickBlock,
                            colors = ButtonDefaults.buttonColors(Color.DarkGray)
                        ) {
                            Text(text = "Block", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                        }
                    }

                    else -> {
                        Button(
                            onClick = onClickUnBlock,
                            colors = ButtonDefaults.buttonColors(Color.Green)
                        ) {
                            Text(
                                text = "Unblock",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
