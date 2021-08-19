package xyz.volgoak.wordlearning.screens.initialization

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel

@Composable
fun InitializationScreen(navController: NavController) {
    val viewModel = getViewModel<InitializationViewModel>()
    val state = viewModel.navigationFlow.collectAsState(initial = null)

    state.value?.content()?.let {
        navController.navigate(it)
    }
    Text(text = "hello")
}
