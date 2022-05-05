package com.example.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(id)
        }
    }

    fun searchUser(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchUser(searchText)
        }
    }
}