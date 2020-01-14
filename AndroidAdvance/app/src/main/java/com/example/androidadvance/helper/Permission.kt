package com.example.androidadvance.helper

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun checkPermission(context: Context, permission: String): Boolean {
    val permissionGranted = PackageManager.PERMISSION_GRANTED
    return ContextCompat.checkSelfPermission(context, permission) == permissionGranted
}

fun requestPermission(activity: Activity, permissionList: Array<String>, permissionRequestCode: Int) {
    ActivityCompat.requestPermissions(activity, permissionList, permissionRequestCode)
}

fun mToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}
