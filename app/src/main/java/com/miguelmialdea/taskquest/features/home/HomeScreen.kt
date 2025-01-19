package com.miguelmialdea.taskquest.features.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.miguelmialdea.taskquest.R
import com.miguelmialdea.taskquest.components.BottomBar
import com.miguelmialdea.taskquest.components.TopBar
import com.miguelmialdea.taskquest.domain.models.quest.EpicQuestModel
import com.miguelmialdea.taskquest.domain.models.quest.QuestModel
import com.miguelmialdea.taskquest.domain.models.user.UserModel
import com.miguelmialdea.taskquest.features.home.fab.CreateQuestDialog
import com.miguelmialdea.taskquest.features.home.fab.NewQuestFAB
import com.miguelmialdea.taskquest.navigation.Route
import com.miguelmialdea.taskquest.ui.theme.TaskQuestTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(
                title = "TaskQuest",
                showBackArrow = false,
                showCloseButton = false
            )
        },
        bottomBar = {
            BottomBar(
                currentRoute = Route.Home.path
            ) { }
        },
        floatingActionButton = {
            NewQuestFAB(
                onClick = { showDialog = true }
            )
        }
    ) { paddingValues ->
        if (showDialog) {
            CreateQuestDialog(
                onDismiss = { showDialog = false },
                onQuestCreated = { showDialog = false }
            )
        }

        when {
            uiState.isLoading -> LoadingScreen()
            uiState.error != null -> ErrorScreen(
                error = uiState.error!!,
                onRetry = viewModel::retryLoading
            )
            else -> HomeContent(
                uiState = uiState,
                onQuestCompleted = viewModel::onQuestCompleted,
                onEpicQuestProgressUpdated = viewModel::updateEpicQuestProgress,
                modifier = modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun HomeContent(
    uiState: HomeUiState,
    onQuestCompleted: (String) -> Unit,
    onEpicQuestProgressUpdated: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1A1625)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Character Stats Card
        item {
            uiState.user?.let { user ->
                CharacterStatsCard(user = user)
            }
        }

        // Current Quest Card
        item {
            uiState.currentEpicQuest?.let { epicQuest ->
                CurrentQuestCard(
                    epicQuest = epicQuest,
                    onProgressUpdated = { progress ->
                        onEpicQuestProgressUpdated(epicQuest.id, progress)
                    }
                )
            }
        }

        // Daily Quests Card
        item {
            DailyQuestsCard(
                quests = uiState.dailyQuests,
                onQuestCompleted = onQuestCompleted
            )
        }
    }
}

@Composable
private fun CharacterStatsCard(user: UserModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2D2438)
        ),
        border = BorderStroke(1.dp, Color(0xFF3D3450))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header with Level and Stars
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color(0xFF4A148C), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = user.level.toString(),
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Column {
                        Text(
                            text = user.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "Stars",
                        tint = Color(0xFFFFD700)
                    )
                    Text(
                        text = user.stars.toString(),
                        color = Color(0xFFFFD700),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Stats Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                with(user.stats) {
                    StatItem(
                        icon = R.drawable.ic_favorite,
                        label = "Health",
                        value = "$health/$maxHealth",
                        color = Color(0xFFE57373)
                    )
                    StatItem(
                        icon = R.drawable.ic_radio_button_checked,
                        label = "Focus",
                        value = "$focus/$maxFocus",
                        color = Color(0xFF64B5F6)
                    )
                    StatItem(
                        icon = R.drawable.ic_wb_sunny,
                        label = "Energy",
                        value = "$energy/$maxEnergy",
                        color = Color(0xFFFFD54F)
                    )
                    StatItem(
                        icon = R.drawable.ic_coffee,
                        label = "Stamina",
                        value = "$stamina/$maxStamina",
                        color = Color(0xFFFFB74D)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // XP Progress
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Level ${user.level} - ${user.title}",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "${user.currentXp} / ${user.maxXp} XP",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                LinearProgressIndicator(
                    progress = user.currentXp.toFloat() / user.maxXp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = Color(0xFF7E57C2),
                    trackColor = Color(0xFF4A148C)
                )
            }
        }
    }
}

@Composable
private fun CurrentQuestCard(
    epicQuest: EpicQuestModel,
    onProgressUpdated: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2D2438)
        ),
        border = BorderStroke(1.dp, Color(0xFF3D3450))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_epic_quest),
                    contentDescription = "Epic Quest",
                    tint = Color(0xFFFFD54F)
                )
                Text(
                    text = "Epic Quest In Progress",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_timer),
                        contentDescription = "Time remaining",
                        tint = Color(0xFFFFD54F)
                    )
                    Text(
                        text = epicQuest.timeRemaining,
                        color = Color(0xFFB39DDB)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF362F45), RoundedCornerShape(8.dp))
                    .border(1.dp, Color(0xFF4A148C), RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = epicQuest.isCompleted,
                                onCheckedChange = {},
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(0xFF7E57C2),
                                    uncheckedColor = Color(0xFFB39DDB)
                                )
                            )
                            Column {
                                Text(
                                    text = epicQuest.title,
                                    color = Color.White,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        color = Color(0xFF4A148C),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Text(
                                            text = "Epic",
                                            modifier = Modifier.padding(
                                                horizontal = 8.dp,
                                                vertical = 4.dp
                                            ),
                                            color = Color.White,
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.Star,
                                            contentDescription = "XP",
                                            tint = Color(0xFFFFD54F),
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            text = "${epicQuest.xpReward} XP",
                                            color = Color(0xFFB39DDB),
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = Color(0xFF4A148C))
                    Spacer(modifier = Modifier.height(16.dp))

                    // Progress
                    Text(
                        text = "Progress: ${epicQuest.progress}/${epicQuest.totalSteps}",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = epicQuest.progress.toFloat() / epicQuest.totalSteps,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = Color(0xFF7E57C2),
                        trackColor = Color(0xFF4A148C)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Rewards
                    Text(
                        text = "Rewards:",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        RewardChip(
                            icon = R.drawable.ic_star,
                            text = "${epicQuest.xpReward} XP",
                            color = Color(0xFFFFD54F)
                        )
                        epicQuest.gemsReward?.let {
                            RewardChip(
                                icon = R.drawable.ic_diamond,
                                text = "$it Gems",
                                color = Color(0xFF64B5F6)
                            )
                        }
                        epicQuest.titleReward?.let {
                            RewardChip(
                                icon = R.drawable.ic_emoji_events,
                                text = it,
                                color = Color(0xFFFFD54F)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DailyQuestsCard(
    quests: List<QuestModel>,
    onQuestCompleted: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2D2438)
        ),
        border = BorderStroke(1.dp, Color(0xFF3D3450))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_daily_quest),
                    contentDescription = "Daily Quest",
                    tint = Color(0xFFFFD54F)
                )
                Text(
                    text = "Daily Quests",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Text(
                    text = "${quests.count { it.isCompleted }}/${quests.size} Completed",
                    color = Color(0xFFB39DDB)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Quest items
            quests.forEach { quest ->
                QuestItem(
                    quest = quest,
                    onQuestCompleted = { onQuestCompleted(quest.id) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun QuestItem(
    quest: QuestModel,
    onQuestCompleted: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                if (quest.isCompleted) Color(0xFF2D2438) else Color(0xFF362F45),
                RoundedCornerShape(8.dp)
            )
            .border(
                width = if (!quest.isCompleted) 1.dp else 0.dp,
                color = Color(0xFF4A148C),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
// Status indicator bar
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(48.dp)
                .background(
                    when {
                        quest.isCompleted -> Color(0xFF4CAF50)
                        else -> Color(0xFFFFD54F)
                    },
                    RoundedCornerShape(2.dp)
                )
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = quest.title,
                        color = if (quest.isCompleted) Color.Gray else Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = quest.timeRange,
                        color = Color(0xFFB39DDB),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "XP",
                        tint = Color(0xFFFFD54F),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${quest.xpReward} XP",
                        color = Color(0xFFB39DDB),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Tags
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Quest Type Tag
                Surface(
                    color = Color(0xFF4A148C),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = quest.type,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // Custom Tags
                quest.tags.forEach { tag ->
                    Surface(
                        color = Color(0xFF4A148C),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = tag,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                // Reward Tags
                quest.energyReward?.let { energy ->
                    Surface(
                        color = Color(0xFF4A148C),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "+$energy Energy",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                quest.gemsReward?.let { gems ->
                    Surface(
                        color = Color(0xFF4A148C),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "+$gems Gems",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatItem(
    icon: Int,
    label: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color(0xFF362F45), RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFF4A148C), RoundedCornerShape(8.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )

        Text(
            text = value,
            color = color,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun RewardChip(
    icon: Int,
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = Color(0xFF362F45),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = text,
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1A1625)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Animación de rotación
            val infiniteTransition = rememberInfiniteTransition()
            val rotation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1500, easing = LinearEasing)
                )
            )

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_sword),
                contentDescription = "Loading",
                tint = Color(0xFFFFD54F),
                modifier = Modifier
                    .size(48.dp)
                    .rotate(rotation)
            )

            Text(
                text = "Loading your quest...",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun ErrorScreen(
    error: String,
    onRetry: () -> Unit,  // Añadido este parámetro
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1A1625)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_error_shield),
                contentDescription = "Error",
                tint = Color(0xFFE57373),
                modifier = Modifier.size(48.dp)
            )

            Text(
                text = "Quest Failed",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = error,
                color = Color(0xFFB39DDB),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = onRetry,  // Actualizado para usar onRetry
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7E57C2)
                ),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Try Again")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    TaskQuestTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun StatItemPreview() {
    TaskQuestTheme {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatItem(
                icon = R.drawable.ic_favorite,
                label = "Health",
                value = "80/100",
                color = Color(0xFFE57373)
            )
            StatItem(
                icon = R.drawable.ic_radio_button_checked,
                label = "Focus",
                value = "60/100",
                color = Color(0xFF64B5F6)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RewardChipPreview() {
    TaskQuestTheme {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RewardChip(
                icon = R.drawable.ic_star,
                text = "100 XP",
                color = Color(0xFFFFD54F)
            )
            RewardChip(
                icon = R.drawable.ic_diamond,
                text = "5 Gems",
                color = Color(0xFF64B5F6)
            )
        }
    }
}