package xyz.volgoak.wordlearning.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import xyz.volgoak.wordlearning.R
import xyz.volgoak.wordlearning.screens.root.Destinations

@Composable
fun MainScreen(navController: NavController) {
    Button(
        onClick = { navController.navigate(Destinations.SETS) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.main_sets)
        )
    }
    /*Column(
        modifier = Modifier.padding(all = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {navController.navigate(Destinations.SETS)},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.main_sets)
            )
        }
    }*/
}