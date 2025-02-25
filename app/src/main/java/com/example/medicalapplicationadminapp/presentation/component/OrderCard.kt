package com.example.medicalapplicationadminapp.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
fun UserOrderCard(
    category: String,
    orderID: String,
    orderStatus: Int,
    price: Double,
    productName: String,
    expireDate: String,
    quantity: Int,
    onClickOrderStatusChange: () -> Unit,
    onClickDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = SecondMainColor,
            contentColor = Color.White
        )
    ) {
        val orderStatusText = if (orderStatus == 0) "Pending" else "Success"
        val orderStatusColor = if (orderStatus == 0) Color.Yellow else Color.Green

        val orderInfo = listOf(
            "Product Name" to productName,
            "Order ID" to orderID,
            "Category" to category,
            "Price" to price,
            "Quantity" to quantity,
            "Order Status" to orderStatusText,
            "Expire Date" to expireDate
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            orderInfo.forEach { (label, value) ->
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("$label: ")
                        }
                        append(value.toString())
                    },
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
            }

            AnnotatedText("Order Status", orderStatusText, orderStatusColor)

            if (orderStatus == 0) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { onClickDelete() },
                        colors = ButtonDefaults.buttonColors(Color.Red)
                    ) {
                        Text(text = "Cancel", fontWeight = FontWeight.SemiBold)
                    }
                    Button(
                        onClick = { onClickOrderStatusChange() },
                        colors = ButtonDefaults.buttonColors(Color.Green)
                    ) {
                        Text(text = "Approved", fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}