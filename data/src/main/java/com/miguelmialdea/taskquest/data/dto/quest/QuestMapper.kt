package com.miguelmialdea.taskquest.data.dto.quest

import com.miguelmialdea.taskquest.domain.models.quest.EpicQuestModel
import com.miguelmialdea.taskquest.domain.models.quest.QuestModel

fun QuestDto.toDomain() = QuestModel(
    id = id,
    title = title,
    timeRange = timeRange,
    type = type,
    tags = tags,
    xpReward = xpReward,
    energyReward = energyReward,
    gemsReward = gemsReward,
    titleReward = titleReward,
    isCompleted = isCompleted
)

fun QuestModel.toDto() = QuestDto(
    id = id,
    title = title,
    timeRange = timeRange,
    type = type,
    tags = tags,
    xpReward = xpReward,
    energyReward = energyReward,
    gemsReward = gemsReward,
    titleReward = titleReward,
    isCompleted = isCompleted
)

fun EpicQuestDto.toDomain() = EpicQuestModel(
    id = id,
    title = title,
    timeRemaining = timeRemaining,
    progress = progress,
    totalSteps = totalSteps,
    xpReward = xpReward,
    gemsReward = gemsReward,
    titleReward = titleReward,
    isCompleted = isCompleted
)

fun EpicQuestModel.toDto() = EpicQuestDto(
    id = id,
    title = title,
    timeRemaining = timeRemaining,
    progress = progress,
    totalSteps = totalSteps,
    xpReward = xpReward,
    gemsReward = gemsReward,
    titleReward = titleReward,
    isCompleted = isCompleted
)
