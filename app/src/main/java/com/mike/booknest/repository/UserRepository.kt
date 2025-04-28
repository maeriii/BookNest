package com.mike.booknest.repository

import com.mike.booknest.data.UserDao
import com.mike.booknest.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}