package it.etg.pmpm.game.examen.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import androidx.room.RoomDatabase
import it.etg.pmpm.game.examen.R
import it.etg.pmpm.game.examen.api.RetrofitInstance
import it.etg.pmpm.game.examen.database.AppDatabase
import it.etg.pmpm.game.examen.database.GamesEntity
import it.etg.pmpm.game.examen.databinding.ActivityMainBinding
import it.etg.pmpm.game.examen.ui.adapter.GamesAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    companion object{
        const val NOMBRE_BD = "app_bd"
        const val TAG = "ORIGEN"
        const val MSG_REST = "Actualizando desde REST"
        const val MSG_BD_VACIA = "BD vacia -> REST"
        const val MSG_BD_DATOS = "BD con datos -> cargar BD"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var bd : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRoom()
        comprobarDatosIniciales()

        binding.btnActualizar.setOnClickListener {
            actualizarDesdeRest()
        }
        }

    private fun setRoom(){
        bd = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, NOMBRE_BD
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    private fun comprobarDatosIniciales(){
        lifecycleScope.launch {
            val datosBD = bd.gamesDao().getAllObjects()

            if (datosBD.isEmpty()){
                Log.d(TAG, MSG_BD_VACIA)
                val datosApi = RetrofitInstance.api.getGames()

                val entidades = datosApi.map {
                    GamesEntity(it.id, it.title, it.genre, it.developer)
                }
                bd.gamesDao().insertAll(entidades)
                mostrarEnListView(entidades)
            } else{
                Log.d(TAG, MSG_BD_DATOS)
                mostrarEnListView(datosBD)
            }
        }
    }

    private fun actualizarDesdeRest(){
        lifecycleScope.launch {
            Log.d(TAG, MSG_REST)
            bd.gamesDao().deleteAll()
            val datosApi = RetrofitInstance.api.getGames()
            val entidades = datosApi.map {
                GamesEntity(it.id, it.title, it.genre, it.developer)
            }
            bd.gamesDao().insertAll(entidades)
            mostrarEnListView(entidades)
        }
    }
    private fun mostrarEnListView(lista: List<GamesEntity>){
        val adapter = GamesAdapter(this, lista)
        binding.listView.adapter = adapter
    }
}
