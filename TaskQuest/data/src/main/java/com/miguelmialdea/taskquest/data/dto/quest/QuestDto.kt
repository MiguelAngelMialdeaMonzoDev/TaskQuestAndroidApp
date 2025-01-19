package com.miguelmialdea.taskquest.data.dto.quest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Entity(tableName = "quests")
data class QuestDto(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @BsonId
    val id: String = ObjectId().toString(),

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "timeRange")
    val timeRange: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "tags")
    val tags: List<String>,

    @ColumnInfo(name = "xpReward")
    val xpReward: Int,

    @ColumnInfo(name = "energyReward")
    val energyReward: Int? = null,

    @ColumnInfo(name = "gemsReward")
    val gemsReward: Int? = null,

    @ColumnInfo(name = "titleReward")
    val titleReward: String? = null,

    @ColumnInfo(name = "isCompleted")
    val isCompleted: Boolean = false
)