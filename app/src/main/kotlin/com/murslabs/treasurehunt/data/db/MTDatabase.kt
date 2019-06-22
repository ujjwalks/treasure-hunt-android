package com.murslabs.android.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.murslabs.android.data.db.dao.EntityDao
import com.murslabs.android.data.db.entity.Entity
import com.murslabs.treasurehunt.data.db.converter.BooleanListConverter
import com.murslabs.treasurehunt.data.db.converter.FloatListConverter
import com.murslabs.treasurehunt.data.db.converter.MapStringConverter
import com.murslabs.treasurehunt.data.db.converter.StringListConverter

@Database(entities = [
    (Entity::class)
], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class, FloatListConverter::class, BooleanListConverter::class,
        MapStringConverter::class)
abstract class MTDatabase : RoomDatabase() {

    abstract fun entityDao(): EntityDao
}