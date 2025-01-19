package com.miguelmialdea.taskquest.data.dto.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.bson.codecs.pojo.annotations.BsonId

@Entity(tableName = "user_stats")
data class UserStatsDto(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @BsonId
    val userId: String,

    @ColumnInfo(name = "health")
    val health: Int,

    @ColumnInfo(name = "maxHealth")
    val maxHealth: Int,

    @ColumnInfo(name = "focus")
    val focus: Int,

    @ColumnInfo(name = "maxFocus")
    val maxFocus: Int,

    @ColumnInfo(name = "energy")
    val energy: Int,

    @ColumnInfo(name = "maxEnergy")
    val maxEnergy: Int,

    @ColumnInfo(name = "stamina")
    val stamina: Int,

    @ColumnInfo(name = "maxStamina")
    val maxStamina: Int
)