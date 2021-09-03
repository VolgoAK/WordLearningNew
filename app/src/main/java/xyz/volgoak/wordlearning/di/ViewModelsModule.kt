package xyz.volgoak.wordlearning.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.volgoak.wordlearning.screens.MainScreenViewModel
import xyz.volgoak.wordlearning.screens.dictionary.DictionaryViewModel
import xyz.volgoak.wordlearning.screens.initialization.InitializationViewModel
import xyz.volgoak.wordlearning.screens.root.RootViewModel
import xyz.volgoak.wordlearning.screens.setdetails.SetDetailsViewModel
import xyz.volgoak.wordlearning.screens.sets.SetsViewModel

val viewModelsModule = module {
    viewModel {
        InitializationViewModel(
            dbImporter = get()
        )
    }
    viewModel {
        RootViewModel(
        )
    }

    viewModel {
        MainScreenViewModel(
        )
    }

    viewModel {
        SetsViewModel(setsRepository = get())
    }

    viewModel { (setId: Long) ->
        SetDetailsViewModel(
            setId = setId,
            setsRepository = get(),
            wordsRepository = get(),
        )
    }

    viewModel {
        DictionaryViewModel(wordsRepository = get())
    }
}