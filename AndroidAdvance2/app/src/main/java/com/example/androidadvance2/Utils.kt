package com.example.androidadvance2

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.provider.MediaStore
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidadvance2.adapter.SongsAdapter
import com.example.androidadvance2.fragments.SongsFragment
import com.example.androidadvance2.model.Songs
import kotlin.collections.ArrayList


@RequiresApi(Build.VERSION_CODES.O)

//fun getSongsList(context: Context?): ArrayList<Songs> {
//    var songsList: ArrayList<Songs> = arrayListOf()
//
//    var ID = MediaStore.Audio.Media._ID
//    var TITLE = MediaStore.Audio.Media.TITLE
//    var ARTIST = MediaStore.Audio.Media.ARTIST
//
//    var uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI
//    var column = arrayOf(ID, TITLE, ARTIST)
//    var cursor = context?.contentResolver?.query(uri,column,null,null)
//
//    if (cursor != null) {
//
//        var columnID = cursor.getColumnIndexOrThrow(ID)
//        var columnName = cursor.getColumnIndexOrThrow(TITLE)
//        var columnArtist = cursor.getColumnIndexOrThrow(ARTIST)
//
//        while (cursor.moveToNext()) {
//
//            var artist = cursor.getString(columnArtist)
//            var title = cursor.getString(columnName)
//            var id = cursor.getLong(columnID)
//            var uri = Uri.withAppendedPath(uri, ""+id)
//
//            songsList.add(Songs(title, artist, uri))
//        }
//        cursor.close()
//    }
//    return songsList
//}

fun mCheckPermission(context: Context, permission: String): Boolean {
    val permissionGranted = PackageManager.PERMISSION_GRANTED
    return ContextCompat.checkSelfPermission(context, permission) == permissionGranted
}

fun mRequestPermission(activity: Activity, permissionList: Array<String>, permissionRequestCode: Int) {
    ActivityCompat.requestPermissions(activity, permissionList, permissionRequestCode)
}

fun timeFormat(millis: Int): String? {
    val buf = StringBuffer()
    val minutes = millis % (1000 * 60 * 60) / (1000 * 60)
    val seconds = millis % (1000 * 60 * 60) % (1000 * 60) / 1000
    buf
        .append(String.format("%02d", minutes))
        .append(":")
        .append(String.format("%02d", seconds))
    return buf.toString()
}



class GetSongList(var progressBar: ProgressBar, var context: Context, var recyclerView: RecyclerView, var songsFragment: SongsFragment): AsyncTask<Void, Int, ArrayList<Songs>>(){
    @RequiresApi(Build.VERSION_CODES.O)

    override fun doInBackground(vararg params: Void?): ArrayList<Songs> {

        var songsList: ArrayList<Songs> = arrayListOf()

        var ID = MediaStore.Audio.Media._ID
        var TITLE = MediaStore.Audio.Media.TITLE
        var ARTIST = MediaStore.Audio.Media.ARTIST

        var uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI
        var column = arrayOf(ID, TITLE, ARTIST)
        var cursor = context.contentResolver.query(uri,column,null,null)

        if (cursor != null) {

            var columnID = cursor.getColumnIndexOrThrow(ID)
            var columnName = cursor.getColumnIndexOrThrow(TITLE)
            var columnArtist = cursor.getColumnIndexOrThrow(ARTIST)

            while (cursor.moveToNext()) {

                var artist = cursor.getString(columnArtist)
                var title = cursor.getString(columnName)
                var id = cursor.getLong(columnID)
                var uri = Uri.withAppendedPath(uri, ""+id)

                songsList.add(Songs(title, artist, uri))
            }
            cursor.close()
        }
        return songsList
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: ArrayList<Songs>?) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = result?.let { SongsAdapter(it, context, null, songsFragment) }
        progressBar.visibility = View.GONE
        super.onPostExecute(result)
    }

}



