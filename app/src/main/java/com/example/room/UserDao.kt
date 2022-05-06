package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("DELETE FROM user_table WHERE id = :userId")
    fun deleteUser(userId: Int)

    @Query("SELECT * FROM user_table WHERE firstName LIKE :searchText")
    fun searchUser(searchText: String): LiveData<List<User>>
}