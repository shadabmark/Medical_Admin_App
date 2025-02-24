package com.example.medicalapplicationadminapp.presentation.medical_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalapplicationadminapp.R
import com.example.medicalapplicationadminapp.presentation.navigation.MainScree
import com.example.medicalapplicationadminapp.presentation.viewModel.AppViewModel
import com.example.medicalapplicationadminapp.ui.theme.FirstMainColor
import com.example.medicalapplicationadminapp.ui.theme.SecondMainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductUI(navController: NavController, viewModel: AppViewModel = hiltViewModel()) {

    var productName by remember { mutableStateOf("") }
    var productImage by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productCategory by remember { mutableStateOf("") }
    var productStock by remember { mutableStateOf("") }
    var productExpireDate by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) }

    val productState = viewModel.addProductState.collectAsState()

    LaunchedEffect(productState.value) {
        productState.value.data?.let {
            productName = ""
            productImage = ""
            productPrice = ""
            productCategory = ""
            productStock = ""
            productExpireDate = ""
            isSuccess = true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Product",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SecondMainColor
                    )
                }
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .padding(start = 22.dp, end = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isSuccess) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Congratulation(navController)
                }
            } else {
                Spacer(modifier = Modifier.height(135.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        TextField(
                            value = productName,
                            onValueChange = { productName = it },
                            placeholder = { Text(text = "ProductName") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.LightGray
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = productImage,
                            onValueChange = { productImage = it },
                            placeholder = { Text(text = "ProductImage") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.LightGray
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = productPrice,
                            onValueChange = { productPrice = it },
                            placeholder = { Text(text = "ProductPrice") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.LightGray
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = productCategory,
                            onValueChange = { productCategory = it },
                            placeholder = { Text(text = "ProductCategory") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.LightGray
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = productStock,
                            onValueChange = { productStock = it },
                            placeholder = { Text(text = "ProductStock") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.LightGray
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = productExpireDate,
                            onValueChange = { productExpireDate = it },
                            placeholder = { Text(text = "ExpireDate") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.LightGray
                            )
                        )
                        Spacer(modifier = Modifier.height(26.dp))

                        Button(
                            onClick = {
                                viewModel.createProduct(
                                    productName = productName,
                                    productImage = productImage,
                                    productPrice = productPrice,
                                    productCategory = productCategory,
                                    productStock = productStock,
                                    expireDate = productExpireDate
                                )
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = FirstMainColor,
                                contentColor = Color.White
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "AddNow")
                        }
                    }
                    item { Spacer(modifier = Modifier.height(86.dp)) }
                }
            }
        }
    }
}

@Composable
fun Congratulation(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.comp2),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Product Added Successfully!",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Green
            )
            Spacer(modifier = Modifier.height(22.dp))
            Button(
                onClick = { navController.navigate(MainScree) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = FirstMainColor,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Done!", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
