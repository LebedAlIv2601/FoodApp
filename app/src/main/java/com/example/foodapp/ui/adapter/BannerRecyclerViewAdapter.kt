package com.example.foodapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.databinding.BannerRecyclerViewItemBinding

class BannerRecyclerViewAdapter :
    RecyclerView.Adapter<BannerRecyclerViewAdapter.BannerViewHolder>() {


    var data = listOf<Int>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class BannerViewHolder (private val binding: BannerRecyclerViewItemBinding)
        : RecyclerView.ViewHolder(binding.root){


        fun bind(item: Int) {
            binding.bannerImageView.setImageResource(item)
        }

        companion object {
            fun from(parent: ViewGroup): BannerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.banner_recycler_view_item, parent, false)
                return BannerViewHolder(BannerRecyclerViewItemBinding.bind(view))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


}