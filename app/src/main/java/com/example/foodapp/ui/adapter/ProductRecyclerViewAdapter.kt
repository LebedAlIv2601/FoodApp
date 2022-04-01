package com.example.foodapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.databinding.FoodRecyclerViewItemBinding
import com.example.foodapp.domain.model.ProductDomainModel

class ProductRecyclerViewAdapter : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    var data = listOf<ProductDomainModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ProductViewHolder(private val binding: FoodRecyclerViewItemBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(item: ProductDomainModel){
                binding.productModel = item
                Glide.with(binding.root.context).load(item.image)
                    .placeholder(R.drawable.food_placeholder).into(binding.foodImageView)
            }

            companion object{
                fun from(parent: ViewGroup): ProductViewHolder{
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val view = layoutInflater.inflate(R.layout.food_recycler_view_item,
                        parent, false)
                    return ProductViewHolder(FoodRecyclerViewItemBinding.bind(view))
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}