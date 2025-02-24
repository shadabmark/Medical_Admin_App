package com.example.medicalapplicationadminapp.presentation.medical_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.medicalapplicationadminapp.presentation.component.MedicalScreenBottomBar

@Composable
fun MainScreenUI(navController: NavController) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            MedicalScreenBottomBar(
                selectedIndex = selectedIndex,
                onItemSelected = { index -> selectedIndex = index }
            )
        }
    ) {
        ContentScreen(
            modifier = Modifier.padding(it),
            selectedIndex = selectedIndex,
            navController = navController
        )
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier,
    selectedIndex: Int,
    navController: NavController
) {
    when (selectedIndex) {
        0 -> GetAllUserUI(navController)
        1 -> GetAllUserOrderProductUI(navController)
        2 -> AddProductUI(navController)
    }
}