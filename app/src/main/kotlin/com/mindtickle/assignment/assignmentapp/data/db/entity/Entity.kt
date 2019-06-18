package com.mindtickle.android.data.db.entity

import android.arch.persistence.room.Entity
import com.mindtickle.android.data.db.TABLE_ENTITY

@Entity(tableName = TABLE_ENTITY,
        primaryKeys = ["id", "userId", "companyName"])
class Entity(var id: String, var name: String, var userId: String, var companyName: String,
             var type: String, var subType: String, var score: Int, var totalScore: Int)