package com.miguelmialdea.taskquest.data.local.dao.quest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.miguelmialdea.taskquest.data.dto.quest.QuestDto
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestDao {
    @Query("SELECT * FROM quests")
    fun getAllQuests(): Flow<List<QuestDto>>

    @Query("SELECT * FROM quests WHERE type = :type")
    fun getQuestsByType(type: String): Flow<List<QuestDto>>

    @Query("SELECT * FROM quests WHERE id = :questId")
    fun getQuestById(questId: String): Flow<QuestDto?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuest(quest: QuestDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuests(quests: List<QuestDto>)

    @Update
    suspend fun updateQuest(quest: QuestDto)

    @Query("UPDATE quests SET isCompleted = :isCompleted WHERE id = :questId")
    suspend fun updateQuestCompletion(questId: String, isCompleted: Boolean)

    @Delete
    suspend fun deleteQuest(quest: QuestDto)

    @Query("DELETE FROM quests WHERE type = :type")
    suspend fun deleteQuestsByType(type: String)
}