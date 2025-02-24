package com.example.medicalapplicationadminapp.presentation.medical_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.example.medicalapplicationadminapp.presentation.component.UserCard
import com.example.medicalapplicationadminapp.presentation.viewModel.AppViewModel
import com.example.medicalapplicationadminapp.ui.theme.SecondMainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetAllUserUI(navController: NavController, viewModel: AppViewModel = hiltViewModel()) {
    val state by viewModel.getAllUsersState.collectAsState()
    val deleteState by viewModel.deleteUserState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(deleteState) {
        if (deleteState.data != null) {
            Toast.makeText(context, "User Deleted Successfully", Toast.LENGTH_SHORT).show()
            viewModel.getAllUsers()
        } else if (deleteState.error != null) {
            Toast.makeText(context, "Error: ${deleteState.error}", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "All Users",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SecondMainColor
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                state.error != null -> Text(text = "Error: ${state.error}")
                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(state.data) { userData ->
                            UserCard(
                                name = userData.name,
                                userID = userData.user_id,
                                phoneNumber = userData.phone_number,
                                email = userData.email,
                                pinCode = userData.pinCode,
                                isApproved = userData.isApproved,
                                block = userData.block,
                                onClickApproved = {
                                    viewModel.approveUserDetails(
                                        userID = userData.user_id,
                                        isApproved = 1
                                    )
                                },
                                onClickBlock = {
                                    viewModel.blockUserDetails(userID = userData.user_id, block = 1)
                                },
                                onClickUnBlock = {
                                    viewModel.blockUserDetails(userID = userData.user_id, block = 0)
                                },
                                onClickDelete = {
                                    viewModel.deleteUser(userData.user_id)
                                }
                            )
                        }
                        item { Spacer(modifier = Modifier.height(76.dp)) }
                    }
                }
            }
        }
    }
}
