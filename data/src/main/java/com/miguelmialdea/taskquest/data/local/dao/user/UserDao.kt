package com.miguelmialdea.taskquest.data.local.dao.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.miguelmialdea.taskquest.data.dto.user.UserDto
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: String): Flow<UserDto?>

    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserWithStats(userId: String): Flow<UserDto?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDto)

    @Update
    suspend fun updateUser(user: UserDto)

    @Query("UPDATE users SET currentXp = currentXp + :xp WHERE id = :userId")
    suspend fun addXp(userId: String, xp: Int)

    @Query("UPDATE users SET level = :level WHERE id = :userId")
    suspend fun updateLevel(userId: String, level: Int)
}