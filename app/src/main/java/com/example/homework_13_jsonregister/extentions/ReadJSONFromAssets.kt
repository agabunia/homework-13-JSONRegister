package com.example.homework_13_jsonregister.extentions

import android.content.Context
import org.json.JSONArray
import java.io.IOException

fun readJSONFromAsset(context: Context, fileName: String): JSONArray? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        return JSONArray(jsonString)
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}