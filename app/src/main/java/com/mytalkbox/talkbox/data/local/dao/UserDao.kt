package com.mytalkbox.talkbox.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mytalkbox.talkbox.data.local.model.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM users WHERE email = :emailUser AND password = :passwordUser LIMIT 1")
    suspend fun login(emailUser: String, passwordUser: String): User?

    @Query("SELECT * FROM users WHERE userName = :userName LIMIT 1")
    suspend fun getByUserName(userName: String): User?

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)
}