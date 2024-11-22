package com.example.noteapp.ui.fragment.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.ui.adapters.OnBoardAdapter
import com.example.noteapp.utils.PreferenceHelper
import com.google.android.material.tabs.TabLayoutMediator


class OnBoardFragment : Fragment() {

    private lateinit var binding : FragmentOnBoardBinding
    private val sharedPreferences = PreferenceHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialise()
        setupListeners()
    }

    private fun initialise() {
        binding.viewpager.adapter = OnBoardAdapter(this)
        TabLayoutMediator(binding.intoTabLayout, binding.viewpager) { _, _ -> }.attach()
    }

    private fun setupListeners() = with(binding.viewpager) {
        sharedPreferences.unit(requireContext())
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.apply {if (position == 2){
                    txtSkip.visibility = View.INVISIBLE
                    btnStart.animate().translationY(0f).alpha(1f)
                    btnStart.setOnClickListener {
                        sharedPreferences.isOnboard = false
                        if (sharedPreferences.isLogged) {
                            findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
                        }else{
                            findNavController().navigate(R.id.action_onBoardFragment_to_authFragment)
                        }
                    }
                }else{
                    btnStart.animate().translationY(600f).alpha(0f)
                    txtSkip.visibility = View.VISIBLE
                    txtSkip.setOnClickListener {
                        if (currentItem < 3) {
                            setCurrentItem(currentItem + 2, true)
                        }
                    }
                }
                }
             }
         }
     )
    }
}