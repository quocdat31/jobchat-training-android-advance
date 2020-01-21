package com.example.androidadvance3.ultis

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Build
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.androidadvance3.Contact
import com.example.androidadvance3.ContactAdapter

class LoadContact(
    var adapter: ContactAdapter?,
    var context: Context
) : AsyncTask<Void, Void, Void>() {

    lateinit var progressDialog: ProgressDialog
    lateinit var recyclerView: RecyclerView
    lateinit var textView: TextView
    var arrayList = ArrayList<Contact>()

    override fun doInBackground(vararg params: Void?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)

        if (!progressDialog.isShowing) progressDialog.show()

        if (arrayList.size > 0) {
            if (adapter == null) {
                adapter = ContactAdapter(context, arrayList)
                recyclerView.adapter = adapter
            }
            adapter?.notifyDataSetChanged()
        } else {
            textView.visibility = View.VISIBLE
        }

        if (progressDialog.isShowing) progressDialog.dismiss()
    }

    override fun onPreExecute() {
        super.onPreExecute()

        progressDialog.show()
    }

    fun readContact(): ArrayList<Contact> {

        var contactList = ArrayList<Contact>()
        var uri = ContactsContract.Contacts.CONTENT_URI
        var cursor = context.contentResolver.query(
            uri,
            null,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME + " ASC "
        )

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var contactId = cursor.getLong(cursor.getColumnIndex("_ID"))
                    var uri =  ContactsContract.Data.CONTENT_URI
                    var datacursor =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        context.contentResolver.query(uri,null,null,null)
                    } else {
                        Toast.makeText(context,"SDK VERSION REQUIRE",Toast.LENGTH_SHORT).show()
                    }
                } while (cursor.moveToNext())
            }
        }

        return contactList
    }


}