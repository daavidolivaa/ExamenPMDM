package it.etg.pmpm.game.examen.api

import it.etg.pmpm.game.examen.model.GamesModel
import retrofit2.http.GET

interface ApiService {
    companion object{
        const val GAMES = "games?platform=browser&category=mmorpg&sort-by=release-date"
    }
    @GET(GAMES)
    suspend fun getGames(): List<GamesModel>
}