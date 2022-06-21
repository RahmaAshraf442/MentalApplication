package com.example.mental_health_app.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mental_health_app.yoga.model.YogaModel
import java.util.*

@Entity(tableName = "yogaTable")

data class YogaEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name") val yogaName:String,
    @ColumnInfo(name = "description") val yogaDescription:String,
    @ColumnInfo(name = "image") val yogaImage:Int,
    @ColumnInfo(name = "steps") val yogaSteps:String)

fun YogaEntity.toYoga(): YogaModel {
    return YogaModel(id, yogaName, yogaDescription, yogaImage, yogaSteps)
}

fun YogaModel.toYogaEntity(): YogaEntity {
    return YogaEntity(id, yogaName, yogaDescription, yogaImage, steps)
}