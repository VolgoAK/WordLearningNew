package xyz.volgoak.uicore

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun ViewModel.vmLaunch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(context, start, block)

fun ViewModel.launchWithProgress(
    liveData: MutableLiveData<Boolean>,
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    liveData.value = true
    return viewModelScope.launch(context, start, block).apply {
        invokeOnCompletion { liveData.value = false }
    }
}

// same as asLiveData but with max timeout and required context
fun <T> Flow<T>.toLiveData(
    context: CoroutineContext
): LiveData<T> = this.asLiveData(context, Long.MAX_VALUE)