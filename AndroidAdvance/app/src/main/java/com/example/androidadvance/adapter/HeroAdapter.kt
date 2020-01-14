package com.example.androidadvance.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.androidadvance.R
import com.example.androidadvance.model.Hero
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroAdapter(private val items: ArrayList<Hero>, private val context: Context): RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage = itemView.itemImageView!!
        val itemDescription = itemView.itemDescriptionTextView!!
        val itemContainer = itemView.itemContainer!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_hero, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemDescription.text = items[position].description
        holder.itemImage.setImageResource(items[position].image)
        holder.itemContainer.animation = AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation)
    }
}
