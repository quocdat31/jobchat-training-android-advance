package com.example.androidadvance3

import android.annotation.SuppressLint
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.contact_list_fragment.*
import java.lang.IllegalStateException

@SuppressLint("ObsoleteSdkInt")
val FROM_COLUMN = arrayOf(
    if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)) {
        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
    } else {
        ContactsContract.Contacts.DISPLAY_NAME
    }
)

private val TO_IDS = intArrayOf(android.R.id.text1)
var PROVIDER_NAME = "com.example.androidadvance3.ContactProvider"
var URL = "content://$PROVIDER_NAME/contact"
var CONTENT_URI = Uri.parse(URL)
var mSearchString: String? = null

class ContactFragment: Fragment(), LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {

    lateinit var contactList: ListView
    var contactId: Long = 0
    var contactKey: String? = null
    var contactUri: Uri? = null
    var cursorAdapter: SimpleCursorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loaderManager.initLoader(0,null,this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_list_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity.also {
            contactList = contactListView
            cursorAdapter = SimpleCursorAdapter(
                it,
                R.layout.contact_item,
                null,
                FROM_COLUMN, TO_IDS,
                0
            )
            contactList.adapter = cursorAdapter
            contactList.onItemClickListener = this

        }

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {

        val contentUri: Uri = Uri.withAppendedPath(
            ContactsContract.Contacts.CONTENT_FILTER_URI,
            Uri.encode(searchString)
        )
        // Starts the query
        return activity?.let {
            CursorLoader(
                it,
                contentUri,
                PROJECTION,
                null,
                null,
                null
            )
        } ?: throw IllegalStateException()

    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        cursorAdapter?.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        cursorAdapter?.swapCursor(null)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val cursor = (parent?.adapter as CursorAdapter).cursor.apply {
            moveToPosition(position)
            contactId = getLong(CONTACT_ID_INDEX)
            contactKey = getString(CONTACT_KEY_INDEX)
            contactUri = ContactsContract.Contacts.getLookupUri(contactId, ContactsContract.Contacts.LOOKUP_KEY)
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private val PROJECTION: Array<out String> = arrayOf(
        ContactsContract.Contacts._ID,
        ContactsContract.Contacts.LOOKUP_KEY,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
        else
            ContactsContract.Contacts.DISPLAY_NAME
    )

    @SuppressLint("ObsoleteSdkInt")
    private val SELECTION: String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} LIKE ?"
        else
            "${ContactsContract.Contacts.DISPLAY_NAME} LIKE ?"
    private val searchString: String? = null
    private val selectionArgs = searchString?.let { arrayOf(it) }

    companion object {
        private const val CONTACT_ID_INDEX: Int = 0
        private const val CONTACT_KEY_INDEX: Int = 1
    }


}