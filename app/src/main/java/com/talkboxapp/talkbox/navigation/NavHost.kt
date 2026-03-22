package com.talkboxapp.talkbox.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.talkboxapp.talkbox.navigation.routes.Routes
import com.talkboxapp.talkbox.ui.screens.HomeScreen
import com.talkboxapp.talkbox.ui.screens.MessageScreen


@Composable
fun NavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HomeRoute,
    ) {
        composable<Routes.HomeRoute> {
            HomeScreen(
                onNavigate = {
                    navController.navigate(Routes.MessageRoute)
                }
            )
        }
        composable<Routes.MessageRoute> {
            MessageScreen(
                onNavigate = {
                    navController.popBackStack()
                }
            )
        }
    }
}