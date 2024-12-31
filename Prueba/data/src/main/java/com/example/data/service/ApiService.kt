package com.example.data.service

import com.example.data.dto.RoomDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("space/{location}")
    suspend fun getRoomResponse(@Path("location") roomId: String): Response<RoomDTO>

    @PUT("space/new/occupancy/current/{total}/max/{max}/name/{name}")
    suspend fun createRoom(@Path("total") total: Int, @Path("max") max: Int, @Path("name") name: String): Response<RoomDTO>

    @PUT("space/{location}/increment")
    suspend fun incrementOccupancy(@Path("location") roomId: String): Response<RoomDTO>

    @PUT("space/{location}/decrement")
    suspend fun decrementOccupancy(@Path("location") roomId: String): Response<RoomDTO>
}