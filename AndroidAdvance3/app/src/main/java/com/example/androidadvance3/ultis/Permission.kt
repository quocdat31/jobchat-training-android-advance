package com.example.androidadvance3.ultis

import android.app.Activity
import android.widget.Toast
import androidx.core.app.ActivityCompat


object Permission {
    fun enableRuntimePermission(activity: Activity, requestCode: Int) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                android.Manifest.permission.READ_CONTACTS
            )
        ) {
            Toast.makeText(
                activity,
                "CONTACTS permission allows us to Access CONTACTS app",
                Toast.LENGTH_LONG
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                activity, arrayOf(android.Manifest.permission.READ_CONTACTS
                ), requestCode
            )
        }
    }

}

