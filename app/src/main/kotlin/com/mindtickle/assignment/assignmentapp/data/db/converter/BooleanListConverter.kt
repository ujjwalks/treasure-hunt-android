package com.mindtickle.assignment.assignmentapp.data.db.converter

import android.arch.persistence.room.TypeConverter
import org.json.JSONArray

class BooleanListConverter {
    @TypeConverter
    fun toList(value: String?): List<Boolean>? {
        val list = ArrayList<Boolean>()
        value?.let {
            val jsonArray = JSONArray(value)
            for (i in 0 until jsonArray.length()) {
                list.add(jsonArray.getBoolean(i))
            }
        }
        return list
    }

    @TypeConverter
    fun toString(values: List<Boolean>?): String {
        val jsonArray = JSONArray()
        values?.forEach { value -> jsonArray.put(value) }
        return jsonArray.toString()
    }
}