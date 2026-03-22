package com.talkboxapp.talkbox.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {

    @Serializable
    data object HomeRoute : Routes

    @Serializable
    data object MessageRoute : Routes
}