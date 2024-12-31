package com.example.domain.model

data class RoomModel(
    val spaceId: String?,
    val spaceName: String?,
    val occupancy: OccupancyModel?,
    val created: String?,
    val lastUpdated: String?
)

data class OccupancyModel(
    val current: Int?,
    val maximum: Int?
)