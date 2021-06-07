package xyz.volgoak.navigation

import androidx.lifecycle.MutableLiveData
import xyz.volgoak.datacore.Event


@Suppress("UNCHECKED_CAST")
class ScreenResultsBuffer {
    private val flows = mutableMapOf<Pair<String, String>, MutableLiveData<*>>()

    fun <T> getResultFlow(className: String, tag: String): MutableLiveData<Event<T>> {
        return flows.getOrPut(className to tag) { MutableLiveData<Event<T>>() } as MutableLiveData<Event<T>>
    }

    inline fun <reified T> getResultFlow(tag: String): MutableLiveData<Event<T>> {
        return getResultFlow(T::class.java.name, tag)
    }
}


