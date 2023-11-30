package com.example.homework_13_jsonregister.registerFragment

import android.app.DatePickerDialog
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_13_jsonregister.databinding.ChooserFieldLayoutBinding
import com.example.homework_13_jsonregister.databinding.FieldLayoutBinding
import com.example.homework_13_jsonregister.extentions.ParentData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FieldRecyclerAdapter() :
    ListAdapter<ParentData.FieldData, RecyclerView.ViewHolder>(FieldCallback()) {

    class FieldCallback : DiffUtil.ItemCallback<ParentData.FieldData>() {
        override fun areItemsTheSame(
            oldItem: ParentData.FieldData, newItem: ParentData.FieldData
        ): Boolean {
            return oldItem.fieldId == newItem.fieldId
        }

        override fun areContentsTheSame(
            oldItem: ParentData.FieldData, newItem: ParentData.FieldData
        ): Boolean {
            return oldItem == newItem
        }
    }

    private var inputText: String = ""
    private val editTextValues = mutableMapOf<Int, String>()
    fun getEditTextValues(): MutableMap<Int, String> {
        return editTextValues
    }

    inner class FieldViewHolder(private var binding: FieldLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(field: ParentData.FieldData) {
            with(binding) {
                etInput.apply {
                    hint = field.hint
                    inputType = InputType.TYPE_CLASS_TEXT
                    addTextChangedListener {
                        inputText = it.toString()
                    }
                    editTextValues += Pair(field.fieldId, inputText)
                }
                //არარსებული ლინკია და კომენტარის სახით დავტოვებ ამას უბრალოდ
//                Glide.with(context).load(field.icon).into(ivInputIcon)
            }
        }
    }

    inner class ChooserViewHolder(private var binding: ChooserFieldLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(field: ParentData.FieldData) {
            val context = binding.root.context
            with(binding) {
                if (field.hint == "Gender") {
                    spinnerChooser.visibility = View.VISIBLE
                    val genders = arrayOf("Gender", "Male", "Female")
                    val arrayAdapter = ArrayAdapter(
                        context,
                        android.R.layout.simple_spinner_dropdown_item,
                        genders
                    )
                    spinnerChooser.adapter = arrayAdapter
                } else {
                    spinnerChooser.visibility = View.GONE
                    layoutTimer.visibility = View.VISIBLE

                    //ცარიელი რომ არ იყოს დეფოლტად მიმდინარე დრო გამოაჩინოს
                    val calendar = Calendar.getInstance()
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    etDateInput.setText(sdf.format(calendar.time))
                    etDateInput.setOnClickListener {
                        val calendar = Calendar.getInstance()
                        val year = calendar.get(Calendar.YEAR)
                        val month = calendar.get(Calendar.MONTH)
                        val day = calendar.get(Calendar.DAY_OF_MONTH)

                        val datePickerDialog = DatePickerDialog(
                            context,
                            { _, year, monthOfYear, dayOfMonth ->
                                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                                etDateInput.setText(selectedDate)
                            },
                            year,
                            month,
                            day
                        )
                        datePickerDialog.show()
                    }
                }
            }
        }
    }

    companion object {
        const val FIELD_TYPE_INPUT = 1
        const val FIELD_TYPE_CHOOSER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return when (viewType) {
            FIELD_TYPE_INPUT -> {
                FieldViewHolder(FieldLayoutBinding.inflate(inflate, parent, false))
            }

            FIELD_TYPE_CHOOSER -> {
                ChooserViewHolder(ChooserFieldLayoutBinding.inflate(inflate, parent, false))
            }

            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val field = getItem(position)
        when (holder.itemViewType) {
            FIELD_TYPE_INPUT -> {
                holder as FieldViewHolder
                holder.bind(field)
            }

            FIELD_TYPE_CHOOSER -> {
                holder as ChooserViewHolder
                holder.bind(field)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).fieldType) {
            "input" -> FIELD_TYPE_INPUT
            "chooser" -> FIELD_TYPE_CHOOSER
            else -> throw IllegalArgumentException("invalid type at $position")
        }
    }
}
