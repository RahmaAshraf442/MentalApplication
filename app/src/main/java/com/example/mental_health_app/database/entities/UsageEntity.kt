package com.example.mental_health_app.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mental_health_app.usage.model.UsageModel

@Entity(tableName = "usageTable")

data class UsageEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long?=null,
    @ColumnInfo(name = "usage") val usage:Int)
fun UsageEntity.toUsage():UsageModel
{
    return UsageModel(usage)
}
fun UsageModel.toUsageEntity():UsageEntity
{
    return UsageEntity(null, usage)
}