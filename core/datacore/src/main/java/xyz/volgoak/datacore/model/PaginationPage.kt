package xyz.volgoak.datacore.model

data class PaginationPage<T>(
    val page: List<T>,
    val paginationMeta: PaginationMeta
)