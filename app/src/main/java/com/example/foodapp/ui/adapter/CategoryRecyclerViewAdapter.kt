package com.example.foodapp.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.databinding.CategoryRecyclerViewItemBinding

class CategoryRecyclerViewAdapter(private val click: (Int)->Unit   )
    : RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder>() {

    var clickPosition = 0

    var clickEnable = false

    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class CategoryViewHolder (private val binding: CategoryRecyclerViewItemBinding)
        : RecyclerView.ViewHolder(binding.root){


        fun bind(item: String, position: Int, clickPosition: Int) {
            binding.categoryTextView.text = item

            if(clickPosition == position){
                binding.categoryTextView.setTextColor(Color.parseColor("#FD3A69"))
                binding.categoryLayout.setBackgroundColor(Color.parseColor("#33FD3A69"))
            }else{
                binding.categoryTextView.setTextColor(Color.parseColor("#C3C4C9"))
                binding.categoryLayout.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }

        companion object {
            fun from(parent: ViewGroup): CategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.category_recycler_view_item, parent, false)
                return CategoryViewHolder(CategoryRecyclerViewItemBinding.bind(view))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val viewHolder = CategoryViewHolder.from(parent)
        viewHolder.itemView.setOnClickListener {
            if(viewHolder.adapterPosition != RecyclerView.NO_POSITION){
                if(clickEnable) {
                    click(viewHolder.adapterPosition)
                    clickPosition = viewHolder.adapterPosition
                    notifyDataSetChanged()
                }
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(data[position], position, clickPosition)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}