package xyz.volgoak.wordlearning.screens.initialization

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import org.koin.androidx.compose.getViewModel
import xyz.volgoak.wordlearning.screens.root.Destinations

@Composable
fun InitializationScreen(navController: NavController) {
    val viewModel = getViewModel<InitializationViewModel>()
    val state = viewModel.navigationFlow.collectAsState(initial = null)

    state.value?.content()?.let {
        navController.navigate(it) {
            popUpTo(Destinations.INITIALIZATION) {
                inclusive = true
            }
        }
    }
    Text(text = "hello")
}
