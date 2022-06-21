package com.example.mental_health_app.database.dao

import androidx.room.*
import com.example.mental_health_app.database.entities.JournalImagesEntity
import com.example.mental_health_app.database.entities.JournalViewedImagesEntity


@Dao
interface JournalViewedImagesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertViewedJournalImage(Image: JournalViewedImagesEntity)

    @Query("Delete from journalViewedImagesTable")
    fun deleteViewedJournalImage()

    @Query("Select * from journalViewedImagesTable order by id ASC")
    fun getAllViewedJournalImages(): List<JournalViewedImagesEntity>

}