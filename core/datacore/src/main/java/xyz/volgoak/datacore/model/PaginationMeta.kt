package xyz.volgoak.datacore.model

data class PaginationMeta(
    val currentPage: Int,
    val lastPage: Int,
    val perPage: Int,
    val total: Int
)