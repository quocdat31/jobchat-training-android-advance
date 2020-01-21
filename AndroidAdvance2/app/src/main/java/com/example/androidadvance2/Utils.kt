package com.example.androidadvance2

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.provider.MediaStore
import android.util.Log
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



class GetSongList(
    var progressBar: ProgressBar,
    var context: Context,
    var recyclerView: RecyclerView,
    var songsFragment: SongsFragment)
    : AsyncTask<Void, Int, ArrayList<Songs>>(){

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doInBackground(vararg params: Void?): ArrayList<Songs> {

        val songsList: ArrayList<Songs> = arrayListOf()
        val ID = MediaStore.Audio.Media._ID
        val TITLE = MediaStore.Audio.Media.TITLE
        val ARTIST = MediaStore.Audio.Media.ARTIST
        val uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI
        val column = arrayOf(ID, TITLE, ARTIST)
        val cursor = context.contentResolver.query(uri,column,null,null)

        if (cursor != null) {

            val columnID = cursor.getColumnIndexOrThrow(ID)
            val columnName = cursor.getColumnIndexOrThrow(TITLE)
            val columnArtist = cursor.getColumnIndexOrThrow(ARTIST)

            while (cursor.moveToNext()) {

                val artist = cursor.getString(columnArtist)
                val title = cursor.getString(columnName)
                val id = cursor.getLong(columnID)
                val uri = Uri.withAppendedPath(uri, ""+id)
                Log.d("asd","$uri")

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



