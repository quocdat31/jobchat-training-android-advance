package com.example.androidadvance2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.androidadvance2.R
import com.example.androidadvance2.model.Songs
import kotlinx.android.synthetic.main.item_music.view.*


interface OnItemClickListener{
    fun onItemClick(songs: Songs)
}

class SongsAdapter(
    var songsList: ArrayList<Songs>,
    var context: Context,
    var songsFilteredList: ArrayList<Songs>? = null,
    private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<SongsAdapter.ViewHolder>(),
    Filterable {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.musicTitleTextView!!
        val artist = itemView.musicArtistTextView!!

        fun bind(songs: Songs, clickListener: OnItemClickListener) {
            title.text = songs.title
            artist.text = songs.artist

            itemView.setOnClickListener {
                clickListener.onItemClick(songs)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_music, parent, false))
    }

    override fun getItemCount(): Int {
        return songsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = songsList[position].title
        holder.artist.text = songsList[position].artist
        val songs = songsList[position]
        holder.bind(songs, itemClickListener)
    }

    override fun getFilter(): Filter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}