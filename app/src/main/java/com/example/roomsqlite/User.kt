package com.example.roomsqlite

import androidx.room.Entity
import androidx.room.PrimaryKey

// Oletuksena tietokannan taulun nimeksi tulee luokan nimi eli tässä tapauksessa User
// Jos taulun nimen haluaa määrittää itse, lisätään annotaation perään: @Entity(tableName = "customUser")
@Entity
data class User(
    // Määritellään pääavaimellinen kenttä, jonka Room-kirjasto määrittelee automaattisesti
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String?,
    // Jos kentän nimen haluaa määrittää itse, lisätään annotaatio: @ColumnInfo(name = "customName")
    val lastName: String?,
    val age: Int?
    )
