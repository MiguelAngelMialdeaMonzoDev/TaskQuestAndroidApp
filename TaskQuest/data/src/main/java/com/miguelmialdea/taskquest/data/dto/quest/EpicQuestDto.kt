package com.miguelmialdea.taskquest.data.dto.quest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Entity(tableName = "epic_quest")
data class EpicQuestDto(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @BsonId
    val id: String = ObjectId().toString(),

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "timeRemaining")
    val timeRemaining: String,

    @ColumnInfo(name = "progress")
    val progress: Int = 0,

    @ColumnInfo(name = "totalSteps")
    val totalSteps: Int,

    @ColumnInfo(name = "xpReward")
    val xpReward: Int,

    @ColumnInfo(name = "gemsReward")
    val gemsReward: Int? = null,

    @ColumnInfo(name = "titleReward")
    val titleReward: String? = null,

    @ColumnInfo(name = "isCompleted")
    val isCompleted: Boolean = false
)