package com.mhss.app.demofoodapp.presentation

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhss.app.demofoodapp.databinding.OnBoardingImageItemBinding

class OnBoardingImageAdapter(
    private val images: List<Int>
): RecyclerView.Adapter<OnBoardingImageAdapter.OnBoardingImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingImageViewHolder {
        return OnBoardingImageViewHolder(
            OnBoardingImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: OnBoardingImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size


    inner class OnBoardingImageViewHolder(private var binding: OnBoardingImageItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageRes: Int) {
            binding.image.setImageResource(imageRes)
            }
        }

}