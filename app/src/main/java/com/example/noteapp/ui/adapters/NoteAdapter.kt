package com.example.noteapp.ui.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.GridItemNoteBinding
import com.example.noteapp.databinding.LinearItemNoteBinding
import com.example.noteapp.ui.interfaces.OnClickItem


class NoteAdapter(
    private val onLongClick: OnClickItem,
    private val onClick: OnClickItem,
    private var isLinear : Boolean
): ListAdapter<NoteModel, RecyclerView.ViewHolder>(DiffCallback()) {

    class LinearViewHolder(private val binding: LinearItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) = with(binding){
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvDate.text = item.date
            cvNote.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(item.color)))
        }
    }

    class GridViewHolder(private val binding: GridItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) = with(binding){
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvDate.text = item.date
            cvNote.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(item.color)))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLinear) 1 else 0
    }

    fun setLinearLayout(isLinear: Boolean) {
        this.isLinear = isLinear
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            LinearViewHolder(
                LinearItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            GridViewHolder(
                GridItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LinearViewHolder) {
            holder.bind(getItem(position))
        } else if (holder is GridViewHolder) {
            holder.bind(getItem(position))
        }
        holder.itemView.setOnLongClickListener {
            onLongClick.onLongClick(getItem(holder.adapterPosition), holder.adapterPosition)
            true
        }
        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(holder.adapterPosition))
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<NoteModel>(){
        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

    }
}