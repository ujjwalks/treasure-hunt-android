package com.murslabs.treasurehunt.data.db.converter

import android.arch.persistence.room.TypeConverter
import org.json.JSONArray

class StringListConverter {
    @TypeConverter
    fun toList(value: String?): List<String>? {
        val list = ArrayList<String>()
        value?.let {
            val jsonArray = JSONArray(value)
            for (i in 0 until jsonArray.length()) {
                list.add(jsonArray.getString(i))
            }
        }
        return list
    }

    @TypeConverter
    fun toString(values: List<String>?): String {
        val jsonArray = JSONArray()
        values?.forEach { value: String? -> jsonArray.put(value) }
        return jsonArray.toString()
    }
}