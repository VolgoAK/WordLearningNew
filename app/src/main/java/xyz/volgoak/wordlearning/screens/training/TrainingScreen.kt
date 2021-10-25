package xyz.volgoak.wordlearning.screens.training

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import org.koin.androidx.compose.getViewModel
import xyz.volgoak.wordlearning.R
import xyz.volgoak.wordlearning.elements.WordsScaffold
import xyz.volgoak.wordlearning.screens.root.Colors
import xyz.volgoak.wordlearning.screens.root.Destinations

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun TrainingScreen(navController: NavController) {
    val viewModel = getViewModel<TrainingViewModel>()
    val state = viewModel.state.collectAsState().value

    state.openResultsEvent?.content()?.let {
        navController.navigate(Destinations.TRAINING_RESULTS) {
            popUpTo(Destinations.TRAINING) { inclusive = true}
        }
    }

    WordsScaffold(
        title = stringResource(R.string.main_trainings),
        onNavigationClick = { navController.popBackStack() }
    ) {
        Column {
            Text(text = state.currentWord?.word.orEmpty())
            TrainingProgress(
                answered = state.answeredWords,
                count = state.wordsCount
            )
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
            ) {
                items(state.currentWord?.variants?.size ?: 0) { index ->
                    AnswerButton(
                        word = state.currentWord?.variants?.getOrNull(index).orEmpty(),
                        answered = state.answered && state.latestAnswerIndex == index,
                        correct = state.correct
                    ) {
                        viewModel.checkAnswer(it, index)
                    }
                }
            }

            if (state.answered) {
                Button(
                    onClick = { viewModel.nextWord() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.training_next)
                    )
                }
            }
        }
    }
}

@Composable
fun AnswerButton(
    word: String,
    answered: Boolean,
    correct: Boolean,
    onClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = when {
                    !answered -> Colors.AnswerDefault
                    correct -> Colors.AnswerCorrect
                    else -> Colors.AnswerWrong
                },
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick(word) }
            .padding(16.dp)
    ) {
        Text(text = word)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TrainingProgress(
    answered: Int,
    count: Int,
    modifier: Modifier = Modifier
) {
    val size = animateFloatAsState(
        targetValue = answered.toFloat() / (count.takeIf { it != 0 } ?: 1)
    )
    Box(
        modifier = modifier
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(12.dp)
            )
            .height(20.dp)
            .fillMaxWidth(1f)
    ) {
        if (count != 0) {
            Box(
                modifier = modifier
                    .background(
                        color = Color.Blue,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxHeight(1f)
                    .fillMaxWidth(
                        size.value
                    )
            )
        }
        Text(
            text = "$answered/$count",
            style = MaterialTheme.typography.caption,
            modifier = modifier.align(Alignment.CenterEnd)
        )
    }
}
