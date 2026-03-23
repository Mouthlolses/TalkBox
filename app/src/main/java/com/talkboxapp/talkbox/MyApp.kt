package com.talkboxapp.talkbox

import android.app.Application
import com.talkboxapp.talkbox.utils.Keys
import com.zegocloud.zimkit.services.ZIMKit

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        ZIMKit.initWith(this, Keys.APP_ID, Keys.APP_SIGN)
        ZIMKit.initNotifications()
    }
}