package xyz.volgoak.wordlearning.screens.training.result

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import xyz.volgoak.wordlearning.R
import xyz.volgoak.wordlearning.elements.WordsScaffold

@Composable
fun TrainingResultScreen(navController: NavController) {
    WordsScaffold(
        title = stringResource(R.string.training_finished),
        onNavigationClick = { navController.popBackStack()}
    ) {
        Text(text = "Training finished")
    }
}