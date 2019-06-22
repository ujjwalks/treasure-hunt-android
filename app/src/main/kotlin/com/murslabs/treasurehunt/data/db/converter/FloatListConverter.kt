package com.murslabs.treasurehunt.data.db.converter

import android.arch.persistence.room.TypeConverter
import org.json.JSONArray

class FloatListConverter {
    @TypeConverter
    fun toList(value: String?): List<Float>? {
        val list = ArrayList<Float>()
        value?.let {
            val jsonArray = JSONArray(value)
            for (i in 0 until jsonArray.length()) {
                list.add(jsonArray.getDouble(i).toFloat())
            }
        }
        return list
    }

    @TypeConverter
    fun toString(values: List<Float>?): String {
        val jsonArray = JSONArray()
        values?.forEach { value -> jsonArray.put(value) }
        return jsonArray.toString()
    }
}