package com.souvik.shoeinventory.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Insert
    fun insert(data: Entity)

    @Query("Select * from ShoeStore order by id")
    fun getAll(): List<Entity>
}