package com.example.mental_health_app.usage.model

import com.example.mental_health_app.database.dao.UsageDao
import com.example.mental_health_app.database.entities.UsageEntity
import com.example.mental_health_app.database.entities.toUsage
import com.example.mental_health_app.database.entities.toUsageEntity
import javax.inject.Inject

class UsageRepo @Inject constructor(private val dao: UsageDao) {

    fun getAllUsage(): List<UsageModel> {
        return dao.getAllUsage().map { it.toUsage() }
    }

    suspend fun insertUsage(usage: UsageModel){
        dao.insertUsage(usage.toUsageEntity())
    }
}