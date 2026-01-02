package com.mytalkbox.talkbox.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mytalkbox.talkbox.ui.screens.onboarding.OnBoardingScreen
import com.mytalkbox.talkbox.ui.screens.onboarding.login.LoginScreen
import com.mytalkbox.talkbox.ui.screens.onboarding.register.RegisterScreen


@Composable
fun TalkBoxApp() {
    OnBoardingNavigation()
}

@Composable
fun OnBoardingNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "onboarding",
        modifier = Modifier,
        enterTransition = { fadeIn() + slideInHorizontally { it } },
        exitTransition = { fadeOut() + slideOutHorizontally { -it } },
        popEnterTransition = { fadeIn() + slideInHorizontally { -it } },
        popExitTransition = { fadeOut() + slideOutHorizontally { it } }
    ) {
        composable("onboarding") {
            OnBoardingScreen(navController)
        }
        composable("register") {
            RegisterScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
    }
}