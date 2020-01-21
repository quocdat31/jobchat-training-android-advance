package com.example.androidadvance3


import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.androidadvance3.ultis.Permission
class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Permission.enableRuntimePermission(this, REQUEST_CODE)


    }

//    var mProjection = arrayOf(
//        ContactsContract.Profile._ID,
//        ContactsContract.Profile.DISPLAY_NAME_PRIMARY,
//        ContactsContract.Profile.LOOKUP_KEY,
//        ContactsContract.Profile.PHOTO_THUMBNAIL_URI
//    )
//
//    var mProfileCursor =
//    applicationContext.contentResolver.query(
//    ContactsContract.Profile.CONTENT_URI,
//    mProjection ,
//    null,
//    null,
//    null)
//
//    var mContactUri = Uri.withAppendedPath(
//        ContactsContract.Contacts.CONTENT_URI,
//        ContactsContract.Contacts.Entity.CONTENT_DIRECTORY
//    )



}
