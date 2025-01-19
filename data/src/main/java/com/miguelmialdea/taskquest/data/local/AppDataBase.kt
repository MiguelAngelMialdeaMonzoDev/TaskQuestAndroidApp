package com.miguelmialdea.taskquest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.miguelmialdea.taskquest.data.dto.quest.EpicQuestDto
import com.miguelmialdea.taskquest.data.dto.quest.QuestDto
import com.miguelmialdea.taskquest.data.dto.user.UserDto
import com.miguelmialdea.taskquest.data.dto.user.UserStatsDto
import com.miguelmialdea.taskquest.data.local.dao.quest.EpicQuestDao
import com.miguelmialdea.taskquest.data.local.dao.quest.QuestDao
import com.miguelmialdea.taskquest.data.local.dao.user.UserDao
import com.miguelmialdea.taskquest.data.local.dao.user.UserStatsDao

@Database(
    entities = [
        UserDto::class,
        UserStatsDto::class,
        QuestDto::class,
        EpicQuestDto::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class TaskQuestDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userStatsDao(): UserStatsDao
    abstract fun questDao(): QuestDao
    abstract fun epicQuestDao(): EpicQuestDao
}