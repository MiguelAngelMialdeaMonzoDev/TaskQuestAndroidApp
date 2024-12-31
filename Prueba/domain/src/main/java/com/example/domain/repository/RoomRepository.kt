package com.example.domain.repository

import com.example.domain.model.RoomModel

interface RoomRepository {
    suspend fun getRoomData(roomId: String): Result<RoomModel>
    suspend fun createRoom(total: Int, max: Int, name: String): Result<RoomModel>
    suspend fun incrementOccupancy(roomId: String): Result<RoomModel>
    suspend fun decrementOccupancy(roomId: String): Result<RoomModel>
}