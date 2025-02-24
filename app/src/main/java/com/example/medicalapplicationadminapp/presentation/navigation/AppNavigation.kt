package com.example.medicalapplicationadminapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medicalapplicationadminapp.presentation.medical_screen.AddProductUI
import com.example.medicalapplicationadminapp.presentation.medical_screen.GetAllUserOrderProductUI
import com.example.medicalapplicationadminapp.presentation.medical_screen.GetAllUserUI
import com.example.medicalapplicationadminapp.presentation.medical_screen.MainScreenUI

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainScree){
        composable<MainScree> {
            MainScreenUI(navController = navController)
        }
        composable<GetAllUserScreen> {
            GetAllUserUI(navController = navController)
        }
        composable<ViewUserOrderScreen> {
            GetAllUserOrderProductUI(navController = navController)
        }
        composable<AddProductScreen> {
            AddProductUI(navController = navController)
        }
    }
}