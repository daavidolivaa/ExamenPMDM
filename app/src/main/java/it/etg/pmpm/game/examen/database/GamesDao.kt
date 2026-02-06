package it.etg.pmpm.game.examen.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.Objects

@Dao
interface GamesDao {
    @Query("SELECT * FROM games")
    suspend fun getAllObjects():List <GamesEntity>

    @Insert
    suspend fun insertAll(games :List<GamesEntity>)

    @Query("DELETE FROM games")
    suspend fun deleteAll()
}