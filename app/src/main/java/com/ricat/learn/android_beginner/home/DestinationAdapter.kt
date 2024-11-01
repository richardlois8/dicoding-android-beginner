package com.ricat.learn.android_beginner.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ricat.learn.android_beginner.R

class DestinationAdapter: RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_destination, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = TravelDestinations.arrDestinations[position]
        holder.bind(destination)
    }

    override fun getItemCount(): Int {
        return TravelDestinations.arrDestinations.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(destination: Destination) {

        }
    }
}