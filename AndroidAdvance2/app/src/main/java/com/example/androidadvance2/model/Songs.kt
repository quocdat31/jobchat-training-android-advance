package com.example.androidadvance2.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Songs(var title: String, var artist: String, var uri: Uri) : Parcelable