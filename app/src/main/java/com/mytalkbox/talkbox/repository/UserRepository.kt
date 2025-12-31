package com.mytalkbox.talkbox.repository

import com.mytalkbox.talkbox.data.local.dao.UserDao
import com.mytalkbox.talkbox.data.local.model.User
import com.mytalkbox.talkbox.response.CreateUserResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun createUser(user: User): CreateUserResponse {
        return try {
            userDao.insert(user)
            CreateUserResponse(success = true, message = "Usuário criado com sucesso")
        } catch (e: Exception) {
            CreateUserResponse(success = false, e.message ?: "Erro inesperado ao criar usuário")
        }
    }
}