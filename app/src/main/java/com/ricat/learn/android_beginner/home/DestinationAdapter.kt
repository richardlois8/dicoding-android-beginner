package com.ricat.learn.android_beginner.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ricat.learn.android_beginner.databinding.ItemDestinationBinding
import coil.load
import coil.size.Scale
import com.ricat.learn.android_beginner.R

class DestinationAdapter(
    private var listener: RvClickListener
): RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {
    private var destinations: List<Destination> = emptyList()

    class ViewHolder(private val binding: ItemDestinationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(destination: Destination, listener: RvClickListener) {
            binding.destinationImg.load(destination.imageUrl){
                scale(Scale.FILL)
                crossfade(true)
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
            }
            binding.destinationName.text = destination.name
            binding.destinationDistance.text = destination.distance
            binding.destinationLocation.text = binding.root.context.getString(R.string.destination_location, destination.location)
            binding.root.setOnClickListener{
                listener.onItemClick(destination)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDestinationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = TravelDestinations.arrDestinations[position]
        holder.bind(destination, listener)
    }

    override fun getItemCount(): Int {
        return destinations.size
    }

    fun setDestinations(newDestinations: List<Destination>) {
        val diffCallback = DiffCallback(destinations, newDestinations)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        destinations = newDestinations
        diffResult.dispatchUpdatesTo(this)
    }

    class DiffCallback(
        private val oldList: List<Destination>,
        private val newList: List<Destination>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}