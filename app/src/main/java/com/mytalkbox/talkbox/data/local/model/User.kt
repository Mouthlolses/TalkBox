package com.mytalkbox.talkbox.data.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "users",
    indices = [
        Index(value = ["email"], unique = true),
        Index(value = ["userName"], unique = true),
        Index(value = ["phoneNumber"], unique = true)
    ]
)
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String,
    val userName: String,
    val phoneNumber: String? = null,
    val email: String? = null,
    val password: String,
)
