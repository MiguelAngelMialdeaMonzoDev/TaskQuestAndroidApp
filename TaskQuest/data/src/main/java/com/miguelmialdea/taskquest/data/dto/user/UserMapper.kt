package com.miguelmialdea.taskquest.data.dto.user

import com.miguelmialdea.taskquest.domain.models.user.UserModel
import com.miguelmialdea.taskquest.domain.models.user.UserStatsModel

fun UserDto.toDomain(stats: UserStatsDto) = UserModel(
    id = id,
    level = level,
    title = title,
    currentXp = currentXp,
    maxXp = maxXp,
    stars = stars,
    stats = stats.toDomain()
)

fun UserModel.toDto() = UserDto(
    id = id,
    level = level,
    title = title,
    currentXp = currentXp,
    maxXp = maxXp,
    stars = stars
)

fun UserStatsDto.toDomain() = UserStatsModel(
    health = health,
    maxHealth = maxHealth,
    focus = focus,
    maxFocus = maxFocus,
    energy = energy,
    maxEnergy = maxEnergy,
    stamina = stamina,
    maxStamina = maxStamina
)

fun UserStatsModel.toDto(userId: String) = UserStatsDto(
    userId = userId,
    health = health,
    maxHealth = maxHealth,
    focus = focus,
    maxFocus = maxFocus,
    energy = energy,
    maxEnergy = maxEnergy,
    stamina = stamina,
    maxStamina = maxStamina
)