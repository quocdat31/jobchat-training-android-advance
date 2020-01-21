//package com.example.androidadvance3.room
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//
//@Dao
//interface ContactDao{
//    @Query("SELECT * FROM Contact")
//    fun getAllContact(): LiveData<List<Contact>>
//
//    @Query("SELECT * FROM Contact WHERE id IN(:contactId)")
//    fun getAllContactById(contactId: IntArray): LiveData<List<Contact>>
//
//    @Query("SELECT * FROM Contact WHERE phoneNumber LIKE :input" +
//            "OR name LIKE :input")
//    fun findContactByNameOrPhoneNumber(input: String): LiveData<List<Contact>>
//
//    @Insert
//    fun insertContact(vararg contact: Contact)
//
//    @Delete
//    fun deleteContact(contact: Contact)
//
//    @Update
//    fun updateContact(vararg contact: Contact)
//
//}