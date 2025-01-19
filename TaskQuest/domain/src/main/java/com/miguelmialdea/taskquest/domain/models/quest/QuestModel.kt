package com.miguelmialdea.taskquest.domain.models.quest

data class QuestModel(
    val id: String,
    val title: String,
    val timeRange: String,
    val type: String,
    val tags: List<String>,
    val xpReward: Int,
    val energyReward: Int? = null,
    val gemsReward: Int? = null,
    val titleReward: String? = null,
    val isCompleted: Boolean = false
)