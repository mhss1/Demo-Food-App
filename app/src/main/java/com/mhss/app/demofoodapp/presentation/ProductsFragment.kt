package com.mhss.app.demofoodapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialContainerTransform
import com.mhss.app.demofoodapp.data.ApiResponseState
import com.mhss.app.demofoodapp.databinding.FragmentProductsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private lateinit var productsAdapter: ProductsAdapter

    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedElementReturnTransition = MaterialContainerTransform()
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsAdapter = ProductsAdapter { product, image ->
            findNavController().navigate(
                ProductsFragmentDirections.productsFragmentToProductDetailsFragment(product),
                FragmentNavigatorExtras(
                    image to image.transitionName,
                )
            )
        }
        binding.productsRecyclerView.adapter = productsAdapter
        binding.topBar.backCard.setOnClickListener {
            findNavController().navigateUp()
        }

        subscribeToObservers()
        binding.refreshLayout.setOnRefreshListener { viewModel.refreshProducts() }

        postponeEnterTransition()
        binding.productsRecyclerView.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    private fun subscribeToObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect { state ->
                    when(state) {
                        is ApiResponseState.Success -> {
                            displayProgressBar(false)
                            productsAdapter.submitList(state.data.products)
                        }
                        is ApiResponseState.Error -> {
                            displayProgressBar(false)
                            toast(state.error)
                        }
                        ApiResponseState.Loading -> {
                            displayProgressBar(true)
                        }
                    }
                }
            }
        }
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.refreshLayout.isRefreshing = isDisplayed
    }
}