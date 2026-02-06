package it.etg.pmpm.game.examen.ui.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import it.etg.pmpm.game.examen.R
import it.etg.pmpm.game.examen.database.GamesEntity

class GamesAdapter(context : Context, private val lista:List<GamesEntity>)
    : ArrayAdapter<GamesEntity>(context,CERO,lista) {

        companion object{
            const val CERO = 0
        }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView?: LayoutInflater.from(context).inflate(R.layout.item_games, parent,false)

        val item = lista[position]

        val txtId = view.findViewById<TextView>(R.id.txtId)
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtGenre = view.findViewById<TextView>(R.id.txtGenre)
        val txtDeveloper = view.findViewById<TextView>(R.id.txtDeveloper)

        txtId.text =  "ID: ${item.id}"
        txtTitle.text =  "Title: ${item.title}"
        txtGenre.text =  "Genre: ${item.genre}"
        txtDeveloper.text =  "Developer: ${item.developer}"

        return view

        }
    }
