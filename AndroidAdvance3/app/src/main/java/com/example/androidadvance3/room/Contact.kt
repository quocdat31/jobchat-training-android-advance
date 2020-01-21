package com.example.androidadvance3.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey val id: Int,
    val name: String?,
    val phoneNumber: Int?
)