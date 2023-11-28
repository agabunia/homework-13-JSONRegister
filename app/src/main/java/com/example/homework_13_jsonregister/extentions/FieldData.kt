package com.example.homework_13_jsonregister.extentions

data class ParentData(
    val arrays: MutableList<FieldData>
) {
    data class FieldData(
        val fieldId: Int,
        val hint: String,
        val fieldType: String,
        val keyboard: String,
        val required: Boolean,
        val isActive: Boolean,
        val icon: String
    )
}
