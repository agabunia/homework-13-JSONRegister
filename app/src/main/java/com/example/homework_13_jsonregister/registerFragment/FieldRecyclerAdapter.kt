package com.example.homework_13_jsonregister.registerFragment

import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_13_jsonregister.databinding.FieldLayoutBinding
import com.example.homework_13_jsonregister.extentions.ParentData

class FieldRecyclerAdapter(var list: MutableList<ParentData.FieldData>) :
    RecyclerView.Adapter<FieldRecyclerAdapter.FieldViewHolder>() {

    inner class FieldViewHolder(private var binding: FieldLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(field: ParentData.FieldData) {
            with(binding) {
                etInput.apply {
                    hint = field.hint
                    if (field.fieldType == "input") {
                        inputType = InputType.TYPE_CLASS_TEXT
                    } else {
                        if(field.hint == "Birthday") {
                            inputType = InputType.TYPE_CLASS_DATETIME
                        }
                    }
                }
//                 არარსებული ლინკია და კომენტარის სახით დავტოვებ ამას უბრალოდ
//                Glide.with(context).load(field.icon).into(this.ivInputIcon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return FieldViewHolder(FieldLayoutBinding.inflate(inflate, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
        val fieldModel = list[position]
        holder.bind(fieldModel)
    }
}