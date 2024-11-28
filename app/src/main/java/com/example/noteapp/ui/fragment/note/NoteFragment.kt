package com.example.noteapp.ui.fragment.note

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.databinding.AlertDialogBinding
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.ui.adapters.NoteAdapter
import com.example.noteapp.ui.interfaces.OnClickItem
import com.example.noteapp.utils.PreferenceHelper
import org.checkerframework.checker.index.qual.Positive


class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private lateinit var activityBinding : ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter
    private val sharedPreferences = PreferenceHelper()
    private var isLinear = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        sharedPreferences.unit(requireContext())
        isLinear = sharedPreferences.isLinear
        noteAdapter = NoteAdapter(this, this, isLinear)
        initialise()
        setupListeners()
        getData()
    }

    private fun initialise() {
        binding.apply {
            if (isLinear){
                rvNote.layoutManager = LinearLayoutManager(requireContext())
            } else {
                    rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
            }
            rvNote.adapter = noteAdapter
        }
    }

    private fun setupListeners() = with(binding) {
        btnAdd.setOnClickListener(){
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }

        btnChangeView.setOnClickListener{
            isLinear = !isLinear
            if (isLinear){
                rvNote.layoutManager = LinearLayoutManager(requireContext())
            } else {
                rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
            }
            noteAdapter.setLinearLayout(isLinear)
            sharedPreferences.isLinear = isLinear

        }

        btnMenu.setOnClickListener{
            if ((activityBinding.main).isDrawerOpen(GravityCompat.START)) {
                (activityBinding.main).closeDrawer(GravityCompat.START)
            } else {
                (activityBinding.main).openDrawer(GravityCompat.START)
            }
        }

    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner){item ->
            noteAdapter.submitList(item)
        }
    }

    override fun onLongClick(noteModel: NoteModel, position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        val view = AlertDialogBinding.inflate(layoutInflater)
        builder.setView(view.root)
        val alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        view.btnDismiss.setOnClickListener {
            alertDialog.dismiss()
        }
        view.btnConfirm.setOnClickListener {
            alertDialog.dismiss()
            App.appDatabase?.noteDao()?.deleteNote(noteModel)
        }
        alertDialog.show()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)

    }


}