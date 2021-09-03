package xyz.volgoak.wordlearning.screens.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import xyz.volgoak.wordlearning.screens.MainScreen
import xyz.volgoak.wordlearning.screens.dictionary.DictionaryScreen
import xyz.volgoak.wordlearning.screens.initialization.InitializationScreen
import xyz.volgoak.wordlearning.screens.setdetails.SetDetailsScreen
import xyz.volgoak.wordlearning.screens.sets.SetsScreen

@Composable
fun WordsComposeApp() {
    ApplicationTheme {
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        WordsNavHost(navController)
    }
}

@Composable
fun WordsNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.INITIALIZATION
    ) {
        composable(Destinations.MAIN) {
            MainScreen(navController)
        }
        composable(Destinations.SETS) {
            SetsScreen(navController)
        }
        composable(Destinations.INITIALIZATION) {
            InitializationScreen(navController)
        }
        composable(Destinations.SET_DETAILS_ROUTE) {
            val setId = it.arguments?.getString(Destinations.SET_ID_KEY)
                ?.toLongOrNull() ?: 0L
            SetDetailsScreen(
                navController = navController,
                setId = setId
            )
        }
        composable(Destinations.DICTIONARY) {
            DictionaryScreen(navController)
        }
    }
}