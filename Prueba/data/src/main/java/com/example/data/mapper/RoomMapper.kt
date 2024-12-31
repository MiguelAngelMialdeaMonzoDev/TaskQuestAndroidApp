package com.example.data.mapper

import com.example.data.dto.OccupancyDTO
import com.example.data.dto.RoomDTO
import com.example.domain.model.OccupancyModel
import com.example.domain.model.RoomModel
import com.example.domain.common.Result

fun RoomDTO.toDomain(): Result<RoomModel> {
    try {
        val spaceId = spaceId ?: return Result.Error(IllegalArgumentException("Space ID is missing"))
        val spaceName = spaceName ?: return Result.Error(IllegalArgumentException("Space name is missing"))
        val occupancyCurrent = occupancy?.current ?: return Result.Error(IllegalArgumentException("Current occupancy is missing"))
        val occupancyMaximum = occupancy?.maximum ?: return Result.Error(IllegalArgumentException("Maximum occupancy is missing"))
        val lastUpdated = lastUpdated ?: return Result.Error(IllegalArgumentException("Last updated time is missing"))
        val created = created ?: return Result.Error(IllegalArgumentException("Created is missing"))

        if (occupancyCurrent > occupancyMaximum) {
            return Result.Error(IllegalArgumentException("Current occupancy cannot exceed the maximum occupancy"))
        }

        return Result.Success(
            RoomModel(
                spaceId = spaceId,
                spaceName = spaceName,
                occupancy = OccupancyModel(
                    current = occupancyCurrent,
                    maximum = occupancyMaximum
                ),
                lastUpdated = lastUpdated,
                created = created
            )
        )
    } catch (e: Exception) {
        return Result.Error(e)
    }
}