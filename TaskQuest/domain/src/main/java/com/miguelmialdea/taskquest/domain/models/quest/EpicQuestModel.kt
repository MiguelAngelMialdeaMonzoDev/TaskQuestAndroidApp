package com.miguelmialdea.taskquest.domain.models.quest

data class EpicQuestModel(
    val id: String,
    val title: String,
    val timeRemaining: String,
    val progress: Int = 0,
    val totalSteps: Int,
    val xpReward: Int,
    val gemsReward: Int? = null,
    val titleReward: String? = null,
    val isCompleted: Boolean = false
)