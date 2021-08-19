package xyz.volgoak.data.dao

import androidx.room.Dao
import androidx.room.Insert
import xyz.volgoak.data.entities.Link

@Dao
interface LinkDao {
    @Insert
    fun insertLinks(vararg links: Link)
}
