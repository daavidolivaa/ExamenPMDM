package it.etg.pmpm.game.examen.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GamesEntity(
    @PrimaryKey val id:Int,
    val title:String,
    val genre:String,
    val developer:String
)

