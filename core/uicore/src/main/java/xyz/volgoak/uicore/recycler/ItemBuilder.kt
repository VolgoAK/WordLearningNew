package xyz.volgoak.uicore.recycler

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import xyz.volgoak.datacore.coroutines.SerialJob
import xyz.volgoak.datacore.extensions.into
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class ItemBuilder<T> {

    private val creatingJob = SerialJob()

    open fun buildItems(
        scope: CoroutineScope,
        data: T,
        adapter: ItemAdapter<AbstractItem<*>>,
        recyclerView: RecyclerView? = null
    ) {
        scope.launch(Dispatchers.Main) {
            val items = withContext(Dispatchers.IO) {
                createItems(data)
            }
            adapter.setNewList(items)
            recyclerView?.isVisible = true
        } into creatingJob
    }

    open fun buildPageItems(
        scope: CoroutineScope,
        data: T,
        adapter: ItemAdapter<AbstractItem<*>>,
        onBuiltCallback: () -> Unit = {}
    ) {
        preparePage(data, adapter)
        scope.launch(Dispatchers.Main) {
            val items = withContext(Dispatchers.IO) {
                createItems(data)
            }
            adapter.add(items)
            onBuiltCallback.invoke()
        } into creatingJob
    }

    abstract fun createItems(data: T): List<AbstractItem<*>>

    open fun preparePage(data: T, adapter: ItemAdapter<AbstractItem<*>>) {

    }
}