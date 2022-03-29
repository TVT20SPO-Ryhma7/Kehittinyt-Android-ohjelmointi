package com.example.roomsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    private lateinit var textViewOutput: TextView
    private lateinit var editTextUsername: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonDelete: Button
    private lateinit var buttonGetAllUsers: Button


    private lateinit var db: UserDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewOutput = findViewById(R.id.textViewOutput)
        editTextUsername = findViewById(R.id.editTextUsername)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonDelete = findViewById(R.id.buttonDelete)
        buttonGetAllUsers = findViewById(R.id.buttonGetALlUsers)

        // Alustetaan tietokanta
        db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "UserDB"
        ).allowMainThreadQueries().build()

        // Alustetaan DAO
        userDao = db.userDao()

        // Käyttäjän lisäys
        buttonAdd.setOnClickListener(){
            userDao.addUser(User(0, editTextUsername.text.toString()))
            updateDBView()
        }

        // Käyttäjän poistaminen
        buttonDelete.setOnClickListener(){
            userDao.deleteUser(userDao.getUsersByName(editTextUsername.text.toString()).first())
            updateDBView()
        }

        // Kaikkien käyttäjien haku
        buttonGetAllUsers.setOnClickListener {
            updateDBView()
        }
    }

    private fun updateDBView(){
        var dbText: String = ""
        userDao.getAllUsers().forEach(){
            dbText += "${it.username} \n"
        }

        textViewOutput.text = dbText
    }
}