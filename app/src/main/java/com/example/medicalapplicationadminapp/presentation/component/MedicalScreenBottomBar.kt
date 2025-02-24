package com.example.medicalapplicationadminapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MedicalScreenBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val navItemList = listOf(
        NavItem("AllUsers", Icons.Default.VerifiedUser),
        NavItem("History", Icons.Filled.ShoppingBag),
        NavItem("Add", Icons.Filled.AddShoppingCart)
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFF6FC1FF), Color(0xFF3498DB))
                )
            ),
        containerColor = Color.Transparent
    ) {
        navItemList.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    onItemSelected(index)
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = null,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                },
                label = {
                    Text(
                        text = navItem.level,
                        color = if (selectedIndex == index) Color.White else Color.LightGray
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.LightGray,
                    indicatorColor = Color(0xFF0D47A1)
                )
            )
        }
    }
}

data class NavItem(
    val level: String,
    val icon: ImageVector
)


