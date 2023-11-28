package com.example.homework_13_jsonregister.registerFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_13_jsonregister.databinding.FieldLayoutBinding
import com.example.homework_13_jsonregister.databinding.NestedLayoutBinding
import com.example.homework_13_jsonregister.extentions.ParentData

class ParentRecyclerView: RecyclerView.Adapter<ParentRecyclerView.ParentViewHolder>() {
    private var parentList: List<ParentData> = mutableListOf()

    inner class ParentViewHolder(private val binding: NestedLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(array: ParentData) {
            val childAdapter = FieldRecyclerAdapter(array.arrays)
            binding.nestedRecycler.layoutManager = LinearLayoutManager(binding.root.context)
            binding.nestedRecycler.adapter = childAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ParentViewHolder(NestedLayoutBinding.inflate(inflate, parent, false))
    }

    override fun getItemCount(): Int {
        return parentList.size
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val itemModel = parentList[position]
        holder.bind(itemModel)
    }

    fun addData(list: List<ParentData>) {
        parentList = list
        notifyDataSetChanged() // ეს შესაცვლელი იქნება
    }
}