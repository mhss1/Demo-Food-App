package com.mhss.app.demofoodapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.mhss.app.demofoodapp.data.Product
import com.mhss.app.demofoodapp.databinding.ProductCardItemBinding
import com.mhss.app.demofoodapp.databinding.ProductsFoundTextBinding

class ProductsAdapter(
    val onClick: (Product, View) -> Unit
)
    : ListAdapter<Product, ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == 0)
            ProductsFoundViewHolder(
                ProductsFoundTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        else
            ProductsViewHolder(
            ProductCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        if (holder is ProductsViewHolder) {
            holder.bind(current)
        }else if (holder is ProductsFoundViewHolder) {
            holder.bind(currentList.size)
        }
    }

    inner class ProductsViewHolder(private var binding: ProductCardItemBinding) :
        ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                productImageCard.transitionName = product.thumbnail

                productTitle.text = product.title
                productDescription.text = product.brand
                productPriceCard.productPrice.text = product.price.toString()
                productRating.text = product.rating.toString()

                Glide.with(itemView.context).load(product.thumbnail).into(productImage)

                productCard.setOnClickListener {
                    onClick(product, productImageCard)
                }
            }
        }
    }
    inner class ProductsFoundViewHolder(private var binding: ProductsFoundTextBinding) :
        ViewHolder(binding.root) {
        fun bind(found: Int) {
            binding.apply {
                foundResults.text = "Found \n$found results"
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }
}