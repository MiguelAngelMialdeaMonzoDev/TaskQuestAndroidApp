package com.miguelmialdea.taskquest.data.local.dao.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.miguelmialdea.taskquest.data.dto.user.UserStatsDto
import kotlinx.coroutines.flow.Flow

@Dao
interface UserStatsDao {
    @Query("SELECT * FROM user_stats WHERE id = :userId")
    fun getUserStats(userId: String): Flow<UserStatsDto?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserStats(stats: UserStatsDto)

    @Update
    suspend fun updateUserStats(stats: UserStatsDto)

    @Query("UPDATE user_stats SET health = :health WHERE id = :userId")
    suspend fun updateHealth(userId: String, health: Int)

    @Query("UPDATE user_stats SET focus = :focus WHERE id = :userId")
    suspend fun updateFocus(userId: String, focus: Int)

    @Query("UPDATE user_stats SET energy = :energy WHERE id = :userId")
    suspend fun updateEnergy(userId: String, energy: Int)

    @Query("UPDATE user_stats SET stamina = :stamina WHERE id = :userId")
    suspend fun updateStamina(userId: String, stamina: Int)
}