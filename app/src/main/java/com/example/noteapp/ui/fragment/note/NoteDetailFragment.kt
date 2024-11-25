package com.example.noteapp.ui.fragment.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.FragmentNoteDetailBinding


class NoteDetailFragment : Fragment() {

    private lateinit var binding : FragmentNoteDetailBinding
    private var noteId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentNoteDetailBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateNote()
        setupListeners()
    }

    private fun updateNote() {
        arguments?.let { args ->
            noteId = args.getInt("noteId", -1)
        }

        if (noteId != -1){
            val id = App.appDatabase?.noteDao()?.getById(noteId)
            id?.let { model ->
                binding.etTitle.setText(model.title)
                binding.etDescription.setText(model.description)
            }
        }
    }

    private fun setupListeners() = with(binding) {
        btnAddText.setOnClickListener{
            val etTitle =etTitle.text.toString()
            val etDescription = etDescription.text.toString()
            if (noteId != -1){
                val updateNote = NoteModel(etTitle, etDescription)
                updateNote.id = noteId
                App.appDatabase?.noteDao()?.updateNote(updateNote)
            }else{
                App.appDatabase?.noteDao()?.insertNote(NoteModel(etTitle, etDescription))
            }
            findNavController().navigateUp()
        }
    }


}