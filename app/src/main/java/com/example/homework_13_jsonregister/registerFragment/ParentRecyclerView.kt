package com.example.homework_13_jsonregister.registerFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_13_jsonregister.databinding.NestedLayoutBinding
import com.example.homework_13_jsonregister.extentions.ParentData

class ParentRecyclerView :
    ListAdapter<ParentData, ParentRecyclerView.ParentViewHolder>(ParentFieldCallback()) {

    class ParentFieldCallback : DiffUtil.ItemCallback<ParentData>() {
        override fun areItemsTheSame(oldItem: ParentData, newItem: ParentData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ParentData, newItem: ParentData): Boolean {
            return oldItem == newItem
        }
    }

    fun getDataFromFieldAdapter(): MutableMap<Int, String> {
        return FieldRecyclerAdapter().getEditTextValues()
    }

    inner class ParentViewHolder(private val binding: NestedLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(array: ParentData) {
            val childAdapter = FieldRecyclerAdapter()
            binding.nestedRecycler.layoutManager = LinearLayoutManager(binding.root.context)
            childAdapter.submitList(array.arrays)
            binding.nestedRecycler.adapter = childAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ParentViewHolder(NestedLayoutBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val field = getItem(position)
        holder.bind(field)
    }
}
