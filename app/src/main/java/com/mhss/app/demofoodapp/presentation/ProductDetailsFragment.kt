package com.mhss.app.demofoodapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.transition.MaterialContainerTransform
import com.mhss.app.demofoodapp.R
import com.mhss.app.demofoodapp.data.Product
import com.mhss.app.demofoodapp.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding

    private lateinit var product: Product
    private val args: ProductDetailsFragmentArgs by navArgs()

    private var quantity = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        product = args.product
        sharedElementEnterTransition = MaterialContainerTransform()
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.productImageCard.transitionName = product.thumbnail


        binding.productName.text = product.title
        binding.price.text = "﹩${product.price}"
        binding.description.text = product.description
        binding.quantity.text = String.format("%02d", quantity)
        binding.rating.text = product.rating.toString()

        Glide.with(requireContext()).load(product.thumbnail).into(binding.productImage)
        binding.backCard.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.plusButton.setOnClickListener {
            quantity++
            binding.quantity.text = String.format("%02d", quantity)
        }
        binding.minusButton.setOnClickListener {
            if (quantity > 1) {
                quantity--
                binding.quantity.text = String.format("%02d", quantity)
            }
        }
    }
}