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
        startDestination = Routes.HomeRoute.route,
    ) {
        composable(Routes.HomeRoute.route) {
            HomeScreen(
                onNavigate = { userId, userName ->
                    navController.navigate(
                        Routes.MessageRoute.createRoute(userId, userName)
                    )
                }
            )
        }
        composable(Routes.MessageRoute.route) { backStackEntry ->

            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val userName = backStackEntry.arguments?.getString("userName") ?: ""

            MessageScreen(
                onNavigate = {
                    navController.popBackStack()
                },
                userId = userId,
                userName = userName
            )
        }
    }
}