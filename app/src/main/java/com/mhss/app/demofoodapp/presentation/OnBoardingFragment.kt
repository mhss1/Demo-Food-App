package com.mhss.app.demofoodapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.mhss.app.demofoodapp.R
import com.mhss.app.demofoodapp.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var imageAdapter: OnBoardingImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageAdapter = OnBoardingImageAdapter(
            listOf(
                R.drawable.on_boarding_img1,
                R.drawable.on_boarding_img2,
                R.drawable.on_boarding_img3
            )
        )

        setUpViewPager()
        binding.dotsIndicator.attachTo(binding.viewPager)

        binding.nextButton.setOnClickListener {
            if (binding.viewPager.currentItem + 1 < imageAdapter.itemCount) {
                binding.viewPager.currentItem += 1
            } else {
                findNavController().navigate(
                    OnBoardingFragmentDirections.onBoardingFragmentToSignUpFragment()
                )
            }
        }
    }

    private fun setUpViewPager() {

        binding.viewPager.adapter = imageAdapter

        val titles = listOf(
            "Browse your menu and order directly",
            "Even to space with us! Together",
            "Pickup delivery at your door"
        )
        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    //update the image number textview
                    binding.mainTitle.text = titles[position]
                }
            }
        )
    }

}