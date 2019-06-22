package com.murslabs.android.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.murslabs.android.data.db.SELECT_FROM
import com.murslabs.android.data.db.TABLE_ENTITY
import com.murslabs.android.data.db.entity.Entity
import com.murslabs.treasurehunt.data.db.base.BaseDao
import io.reactivex.Flowable

@Dao
interface EntityDao : BaseDao<Entity> {

    @Query(SELECT_FROM + TABLE_ENTITY + " WHERE id = :entityId")
    fun queryEntity(entityId: String): Flowable<Entity>
}
