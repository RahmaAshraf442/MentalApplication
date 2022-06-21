package com.example.mental_health_app.database.dao

import androidx.room.*
import com.example.mental_health_app.database.entities.YogaEntity


@Dao
interface YogaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(yoga: YogaEntity)

    @Query("Select * from yogaTable order by id ASC")
    fun getAllYoga(): List<YogaEntity>

    @Query("SELECT * FROM yogaTable WHERE id=:id ")
    fun getYogaDetails(id: Int): YogaEntity
}