package com.example.mental_health_app.database.dao

import androidx.room.*
import com.example.mental_health_app.database.entities.JournalImagesEntity


@Dao
interface JournalImagesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJournalImage(Image: JournalImagesEntity)

    @Delete
    suspend fun deleteJournalImage(Image: JournalImagesEntity)

    @Query("Select * from journalImagesTable order by id ASC")
    fun getAllJournalImages(): List<JournalImagesEntity>

}