package com.example.roomsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {
   // Layout component references
   private lateinit var buttonAdd: Button
   private lateinit var buttonDelete: Button
   private lateinit var databaseView: TextView
   private lateinit var queryField: EditText
   private lateinit var eventOutput: TextView


    private lateinit var db: UserDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize components
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonDelete=findViewById(R.id.buttonDelete)
        databaseView = findViewById(R.id.databaseView)
        queryField = findViewById(R.id.editTextDatabaseQuery)
        eventOutput = findViewById(R.id.textViewEventOutput)

        // Initialize SQLITE
        db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "TestDB"
        ).allowMainThreadQueries().build()

        userDao = db.userDao()



        //val users: List<User> = userDao.getAllUsers()


        // Setup listenera
        buttonAdd.setOnClickListener(){
            userDao.addUser(User(queryField.text.toString(),null,null))
            Log.i("DB",userDao.getAllUsers().toString())
            updateDBView()
        }
        buttonDelete.setOnClickListener(){
            userDao.deleteUser(userDao.getUsersByName(queryField.text.toString()).first())
            Log.i("DB","User "+ queryField.text.toString())
            updateDBView()
        }

    }

    private fun updateDBView(){
        var dbText: String = ""
        userDao.getAllUsers().forEach(){
            dbText += it.firstName + " "+ it.lastName +"\n"
        }

        databaseView.text = dbText
    }

}