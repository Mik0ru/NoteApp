package com.example.noteapp.ui.fragment.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.databinding.FragmentOnBoardPagerBinding

class OnBoardPagerFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardPagerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialise()
    }

    private fun initialise() = with(binding)  {
        when(requireArguments().getInt(ARG_ONBOARD_POSITION)){
            0 ->{
                txtTitle.text = "Удобство"
                txtBody.text = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
                lottie.setAnimationFromUrl("https://assets-v2.lottiefiles.com/tmp/optimize/22830e11-5d05-4d72-bf0b-610f76bdec1a/fvgizOW3Rm.json", "animation1")
            }
            1 ->{
                txtTitle.text = "Организация"
                txtBody.text = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
                lottie.setAnimationFromUrl("https://assets-v2.lottiefiles.com/tmp/optimize/65563416-76c8-4989-aadf-2a51dec5467b/YlZG5MginD.json", "animation2")
            }
            2 ->{
                txtTitle.text = "Синхронизация"
                txtBody.text = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
                lottie.setAnimationFromUrl("https://assets-v2.lottiefiles.com/tmp/optimize/7cdf28fc-1a3c-4196-a509-b4b41e1aa8b0/V4K2ObwT44.json", "animation3")
            }
        }
    }

    companion object{
        const val ARG_ONBOARD_POSITION = "onBoard"
    }

}