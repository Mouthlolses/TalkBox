package com.talkboxapp.talkbox.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.core.content.edit
import com.zegocloud.zimkit.services.ZIMKit
import im.zego.zim.enums.ZIMErrorCode


fun submitCredentials(
    context: Context,
    userId: String,
    userName: String,
    onSuccess: () -> Unit
) {

    if (userId.isNotEmpty() && userName.isNotEmpty()) {
        context.getSharedPreferences("USER", MODE_PRIVATE).edit {
            putString("userId", userId)
            putString("userName", userName)
            apply()
            connectUser(context, userId, userName, onSuccess)
        }
    }
}

fun connectUser(
    context: Context,
    userId: String,
    userName: String,
    onSuccess: () -> Unit
) {
    ZIMKit.connectUser(userId, userName, "", {
        if (it.code == ZIMErrorCode.SUCCESS) {
            onSuccess()
        } else {
            val msg = it.message ?: "Unknow Error"
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }
    })
}
