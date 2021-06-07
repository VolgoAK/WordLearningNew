package xyz.volgoak.datacore.coroutines

import xyz.volgoak.datacore.ContentLoadingState
import xyz.volgoak.datacore.utils.Trigger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

/**
 * Not a real Flow but only a wrapper.
 * Just can't find a better name for this class
 */
class ContentLoadingFlow<R>(
    scope: CoroutineScope,
    private val fetcher: suspend () -> R
) {
    private val triggerFlow = MutableStateFlow(Trigger)
    private val loadingFlow = triggerFlow.flatMapLatest {
        flow {
            emit(ContentLoadingState.loading())
            emit(ContentLoadingState.success(fetcher()))
        }.catch {
            emit(ContentLoadingState.failed(it))
        }
    }
        .conflate()
        .broadcastIn(scope)
        .asFlow()

    fun reload() {
        triggerFlow.value = Trigger
    }

    fun errorsFlow() = loadingFlow
        .filter { it.isFailed() }
        .map { it.requireException() }

    fun progressFlow() = loadingFlow
        .map { it.isLoading() }

    fun contentFlow() = loadingFlow
        .filter { it.isSuccess() }
        .map { it.requireContent() }
}