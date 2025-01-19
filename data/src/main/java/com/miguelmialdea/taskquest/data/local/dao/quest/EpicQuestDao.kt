package com.miguelmialdea.taskquest.data.local.dao.quest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.miguelmialdea.taskquest.data.dto.quest.EpicQuestDto
import kotlinx.coroutines.flow.Flow

@Dao
interface EpicQuestDao {
    @Query("SELECT * FROM epic_quest")
    fun getAllEpicQuests(): Flow<List<EpicQuestDto>>

    @Query("SELECT * FROM epic_quest WHERE id = :questId")
    fun getEpicQuestById(questId: String): Flow<EpicQuestDto?>

    @Query("SELECT * FROM epic_quest WHERE isCompleted = 0 LIMIT 1")
    fun getCurrentEpicQuest(): Flow<EpicQuestDto?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpicQuest(quest: EpicQuestDto)

    @Update
    suspend fun updateEpicQuest(quest: EpicQuestDto)

    @Query("UPDATE epic_quest SET progress = :progress WHERE id = :questId")
    suspend fun updateQuestProgress(questId: String, progress: Int)

    @Query("UPDATE epic_quest SET isCompleted = :isCompleted WHERE id = :questId")
    suspend fun updateQuestCompletion(questId: String, isCompleted: Boolean)

    @Delete
    suspend fun deleteEpicQuest(quest: EpicQuestDto)
}