package com.example.noteapp.ui.interfaces

import com.example.noteapp.data.models.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel, position: Int)

    fun onClick(noteModel: NoteModel)
}