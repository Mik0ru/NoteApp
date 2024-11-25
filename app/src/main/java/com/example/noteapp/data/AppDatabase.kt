package com.example.noteapp.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.data.daos.NoteDao
import com.example.noteapp.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}