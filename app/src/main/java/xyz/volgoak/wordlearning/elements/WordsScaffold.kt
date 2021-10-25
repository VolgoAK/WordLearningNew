package xyz.volgoak.wordlearning.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import xyz.volgoak.wordlearning.R

@Composable
fun WordsScaffold(
    title: String,
    onNavigationClick: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit

) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
                },
                navigationIcon = onNavigationClick?.let {
                    {
                        IconButton(onClick = onNavigationClick) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.core_back),
                                tint = MaterialTheme.colors.onSurface
                            )
                        }
                    }
                },
            )
        },
        content = content
    )
}