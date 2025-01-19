package com.miguelmialdea.taskquest.domain.models.user

data class UserModel(
    val id: String,
    val level: Int,
    val title: String,
    val currentXp: Int,
    val maxXp: Int,
    val stars: Int,
    val stats: UserStatsModel
)