package com.murslabs.treasurehunt.data.db.converter

import android.arch.persistence.room.TypeConverter
import org.json.JSONObject

class MapStringConverter {

    @TypeConverter
    fun toMap(value: String?): Map<String, String> {
        val rootObject = JSONObject(value)
        val map = HashMap<String, String>()
        rootObject.keys().forEach { key ->
            map[key] = rootObject.getString(key)
        }
        return map
    }

    @TypeConverter
    fun toString(value: Map<String, String>): String {
        val rootObject = JSONObject()
        value.forEach { entry ->
            run {
                rootObject.put(entry.key, entry.value)
            }
        }
        return rootObject.toString()
    }
}