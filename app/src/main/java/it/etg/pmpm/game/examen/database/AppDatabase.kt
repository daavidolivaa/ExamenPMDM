package it.etg.pmpm.game.examen.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GamesEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao

}
