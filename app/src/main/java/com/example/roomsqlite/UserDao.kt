package com.example.roomsqlite

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE firstName LIKE :firstName")
    fun getUsersByName(firstName: String): List<User>

    @Insert
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)



    // Esimerkki käännösaikaisesta kyselyn tarkistuksesta:
    //@Query("SELECT * FROM userss")
    //fun getAll(): List<User>
}