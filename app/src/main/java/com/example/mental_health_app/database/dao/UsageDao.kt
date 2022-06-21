package com.example.mental_health_app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mental_health_app.database.entities.UsageEntity


@Dao
interface UsageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsage(usage: UsageEntity)

    @Query("Select * from usageTable")
    fun getAllUsage(): List<UsageEntity>
}