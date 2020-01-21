package com.example.androidadvance3

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactAdapter(var context: Context, var contactList: ArrayList<Contact>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactImage = itemView.contactImage
        val contactName = itemView.contactTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false))
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contactName.text = "${contactList[position].contactName} - ${contactList[position].contactName}"
        holder.contactImage.setImageResource(contactList[position].contactImage.toInt())
    }


}