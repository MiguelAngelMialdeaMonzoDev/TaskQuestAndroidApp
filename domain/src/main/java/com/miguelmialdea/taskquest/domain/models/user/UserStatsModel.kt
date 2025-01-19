package com.miguelmialdea.taskquest.domain.models.user

data class UserStatsModel(
    val health: Int,
    val maxHealth: Int,
    val focus: Int,
    val maxFocus: Int,
    val energy: Int,
    val maxEnergy: Int,
    val stamina: Int,
    val maxStamina: Int
)