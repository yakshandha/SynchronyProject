package com.example.synchronyproject.Helpers.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.synchronyproject.Pages.DetailScreen.DetailScreen
import com.example.synchronyproject.Pages.ListScreen.ListScreen
import com.example.synchronyproject.Pages.ProfileScreen.ProfileScreen

@Composable
fun NavigationController(
    navController : NavHostController
) {
    NavHost(navController = navController, startDestination = "ProfileScreen")
    {
        composable(route = "ProfileScreen"){
            ProfileScreen(navController)
        }
        composable(route = "DetailScreen"){
            DetailScreen(navController)
        }
        composable(route = "ListScreen"){
            ListScreen(navController)
        }
    }
}