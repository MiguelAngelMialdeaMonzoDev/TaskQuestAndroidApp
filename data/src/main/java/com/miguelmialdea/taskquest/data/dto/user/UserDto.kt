package com.miguelmialdea.taskquest.data.dto.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Entity(tableName = "users")
data class UserDto(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @BsonId
    val id: String = ObjectId().toString(),

    @ColumnInfo(name = "level")
    val level: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "currentXp")
    val currentXp: Int,

    @ColumnInfo(name = "maxXp")
    val maxXp: Int,

    @ColumnInfo(name = "stars")
    val stars: Int
)