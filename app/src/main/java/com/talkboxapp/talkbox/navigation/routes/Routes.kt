package com.talkboxapp.talkbox.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String){

    @Serializable
    object HomeRoute : Routes("home")

    @Serializable
    object MessageRoute : Routes("message/{userId}/{userName}"){

        fun createRoute(userId: String, userName: String) =
            "message/$userId/$userName"
    }
}