package com.example.medicalapplicationadminapp.presentation.medical_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalapplicationadminapp.presentation.component.UserOrderCard
import com.example.medicalapplicationadminapp.presentation.viewModel.AppViewModel
import com.example.medicalapplicationadminapp.ui.theme.SecondMainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetAllUserOrderProductUI(
    navController: NavController,
    viewModel: AppViewModel = hiltViewModel(),
) {
    val state by viewModel.getAllUserOrderProductState.collectAsState()
    val addUserStockState by viewModel.addUserStockState.collectAsState()
    val deleteState by viewModel.deleteUserOrderState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(addUserStockState) {
        if (addUserStockState.data != null) {
            Toast.makeText(context, "Order Approved!", Toast.LENGTH_SHORT).show()
        }
        if (addUserStockState.error != null) {
            Toast.makeText(context, addUserStockState.error, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(deleteState) {
        if (deleteState.data != null) {
            Toast.makeText(context, "User Deleted Successfully", Toast.LENGTH_SHORT).show()
            viewModel.getAllUserOrderProduct()
        } else if (deleteState.error != null) {
            Toast.makeText(context, "Error: ${deleteState.error}", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "User Order",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SecondMainColor
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                state.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Error: ${state.error}")
                    }
                }
                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(state.data) { orderData ->
                            UserOrderCard(
                                category = orderData.category,
                                orderID = orderData.order_id,
                                orderStatus = orderData.order_status,
                                price = orderData.price,
                                productName = orderData.product_name,
                                expireDate = orderData.expire_date,
                                quantity = orderData.quantity,
                                onClickOrderStatusChange = {
                                    viewModel.approveUserOrder(orderData.order_id)
                                    viewModel.updateAdminStock(
                                        orderData.product_id,
                                        orderData.quantity
                                    )
                                    viewModel.addUserStock(
                                        productId = orderData.product_id,
                                        productName = orderData.product_name,
                                        productCategory = orderData.category,
                                        expireDate = orderData.expire_date,
                                        productPrice = orderData.price.toString(),
                                        productStock = orderData.quantity.toString()
                                    )
                                },
                                onClickDelete = {
                                    viewModel.deleteUserOrder(orderData.order_id)
                                }
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(86.dp))
                        }
                    }
                }
            }
        }
    }
}

