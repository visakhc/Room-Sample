package com.example.room.repository

import com.example.room.dao.UserDao
import com.example.room.entity.User

class UserRepository(private val userDao: UserDao) {

    val readAllData/*:LiveData<List<User>>*/ = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}