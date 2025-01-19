package com.miguelmialdea.taskquest.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelmialdea.taskquest.domain.models.quest.EpicQuestModel
import com.miguelmialdea.taskquest.domain.models.quest.QuestModel
import com.miguelmialdea.taskquest.domain.models.user.UserModel
import com.miguelmialdea.taskquest.domain.repository.quest.EpicQuestRepository
import com.miguelmialdea.taskquest.domain.repository.quest.QuestRepository
import com.miguelmialdea.taskquest.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository,
    private val questRepository: QuestRepository,
    private val epicQuestRepository: EpicQuestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }

                // Combinar los flujos para mantener la consistencia
                combine(
                    userRepository.getUserByIdRemote("users_001"),
                    epicQuestRepository.getCurrentEpicQuest(),
                    questRepository.getQuestsByType("daily")
                ) { user, epicQuest, quests ->
                    HomeUiState(
                        user = user,
                        currentEpicQuest = epicQuest,
                        dailyQuests = quests,
                        isLoading = false,
                        error = null
                    )
                }.catch { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "An unexpected error occurred"
                        )
                    }
                }.collect { newState ->
                    _uiState.value = newState
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "An unexpected error occurred"
                    )
                }
            }
        }
    }

    fun retryLoading() {
        loadData()
    }

    fun onQuestCompleted(questId: String) {
        viewModelScope.launch {
            try {
                questRepository.completeQuest(questId)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = "Failed to complete quest: ${e.message}")
                }
            }
        }
    }

    fun updateEpicQuestProgress(questId: String, progress: Int) {
        viewModelScope.launch {
            try {
                epicQuestRepository.updateQuestProgress(questId, progress)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = "Failed to update quest progress: ${e.message}")
                }
            }
        }
    }
}

data class HomeUiState(
    val user: UserModel? = null,
    val currentEpicQuest: EpicQuestModel? = null,
    val dailyQuests: List<QuestModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)