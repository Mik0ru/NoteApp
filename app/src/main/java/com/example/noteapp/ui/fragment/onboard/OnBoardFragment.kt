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
import com.google.android.material.tabs.TabLayoutMediator


class OnBoardFragment : Fragment() {

    private lateinit var binding : FragmentOnBoardBinding

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
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2){
                    binding.txtSkip.visibility = View.INVISIBLE
                    binding.btnStart.animate().translationY(0f).alpha(1f);
                    binding.btnStart.setOnClickListener {
                        findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
                    }
                }else{
                    binding.btnStart.animate().translationY(600f).alpha(0f)
                    binding.txtSkip.visibility = View.VISIBLE
                    binding.txtSkip.setOnClickListener {
                        if (currentItem < 3){
                            setCurrentItem(currentItem + 2, true)
                        }
                    }
                }
            }
        })
    }
}