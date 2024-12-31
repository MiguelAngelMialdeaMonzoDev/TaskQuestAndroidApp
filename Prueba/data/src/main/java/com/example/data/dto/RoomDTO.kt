package com.example.data.dto

import com.google.gson.annotations.SerializedName

data class RoomDTO(
    @SerializedName("space_id")
    val spaceId: String?,
    @SerializedName("space_name")
    val spaceName: String?,
    val occupancy: OccupancyDTO?,
    val created: String?,
    @SerializedName("last_updated")
    val lastUpdated: String?
)

data class OccupancyDTO(
    val current: Int?,
    val maximum: Int?
)