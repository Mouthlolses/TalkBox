package com.talkboxapp.talkbox.utils

import android.app.AlertDialog
import android.content.Context
import com.zegocloud.zimkit.common.ZIMKitRouter
import com.zegocloud.zimkit.common.enums.ZIMKitConversationType
import com.zegocloud.zimkit.services.ZIMKit
import com.zegocloud.zimkit.services.callback.CreateGroupCallback
import com.zegocloud.zimkit.services.model.ZIMKitGroupInfo
import im.zego.zim.entity.ZIMError
import im.zego.zim.entity.ZIMErrorUserInfo


fun newChat(context: Context, userID: String) {
    if (userID.isNotEmpty()) {
        connectToUser(
            context = context,
            userId = userID,
            type = ZIMKitConversationType.ZIMKitConversationTypePeer
        )
    }
}

fun createGroupChatImpl(
    context: Context,
    groupName: String,
    groupId: String,
    usersIds: List<String>
) {
    if (groupName.isNotEmpty() && groupId.isNotEmpty() && usersIds.isNotEmpty()) {
        createGroupChat(
            context = context,
            name = groupName,
            id = groupId,
            users = usersIds,
            type = ZIMKitConversationType.ZIMKitConversationTypeGroup
        )
    }
}


//

private fun connectToUser(context: Context, userId: String, type: ZIMKitConversationType) {
    ZIMKitRouter.toMessageActivity(context, userId, type)
}

private fun createGroupChat(
    context: Context,
    name: String,
    id: String,
    users: List<String>,
    type: ZIMKitConversationType
) {
    ZIMKit.createGroup(name, id, users, object : CreateGroupCallback {
        override fun onCreateGroup(
            groupInfo: ZIMKitGroupInfo?,
            inveteUserErrors: ArrayList<ZIMErrorUserInfo?>?,
            error: ZIMError?
        ) {
            if (error == null) {
                ZIMKitRouter.toMessageActivity(context, id, type)
            } else {
                val msg = error.message ?: "Unknow Error"
                AlertDialog.Builder(context)
                    .setTitle("Erro")
                    .setMessage(msg)
                    .setPositiveButton("Ok") { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    })

}