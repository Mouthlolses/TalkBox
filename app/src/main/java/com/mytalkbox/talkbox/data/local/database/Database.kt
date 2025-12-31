package com.mytalkbox.talkbox.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mytalkbox.talkbox.data.local.dao.UserDao
import com.mytalkbox.talkbox.data.local.model.User

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao
}