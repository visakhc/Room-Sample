package com.example.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun deleteUser(id: Int) {
        userDao.deleteUser(id)
    }

    @WorkerThread
    suspend fun searchUser(searchText: String): LiveData<List<User>> {
        return userDao.searchUser(searchText)
    }
}