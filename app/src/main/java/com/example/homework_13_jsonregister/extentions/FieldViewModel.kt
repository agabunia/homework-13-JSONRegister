package com.example.homework_13_jsonregister.extentions

import android.content.Context
import android.util.Log
import android.util.Log.d
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject

class FieldViewModel : ViewModel() {

    fun parseFieldData(jsonObject: JSONObject): ParentData.FieldData {
        val fieldId = jsonObject.optInt("field_id")
        val hint = jsonObject.optString("hint")
        val fieldType = jsonObject.optString("field_type")
        val keyboard = jsonObject.optString("keyboard")
        val required = jsonObject.optBoolean("required")
        val isActive = jsonObject.optBoolean("is_active")
        val icon = jsonObject.optString("icon")
        return ParentData.FieldData(fieldId, hint, fieldType, keyboard, required, isActive, icon)
    }

    fun getJSONData(context: Context, fileName: String): MutableList<ParentData> {
        val data = mutableListOf<ParentData>()
        val nestedJSONArray = readJSONFromAsset(context, fileName)

        nestedJSONArray?.let {
            for (i in 0 until it.length()) {
                val jsonInnerArray = it.getJSONArray(i)
                val childDataList = mutableListOf<ParentData.FieldData>()

                for (j in 0 until jsonInnerArray.length()) {
                    val jsonObject = jsonInnerArray.getJSONObject(j)
                    val fieldData = parseFieldData(jsonObject)
                    childDataList.add(fieldData)
                }

                val parentData = ParentData(i, childDataList)
                data.add(parentData)
            }
        }

        return data
    }

    private val userMap = mutableMapOf<Int, String>()
    fun getDataFromFragment(map: MutableMap<Int, String>) {
        userMap += map
        checkInputs()
    }

    fun checkInputs() {}

    fun returnData(): MutableMap<Int, String> {
        return userMap
    }
}