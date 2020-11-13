package com.example.fitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        lstAtividades.setOnItemClickListener { parent, view, position, id ->

            val atividadeClicada = lstAtividades.getItemAtPosition(position) as Atividade

            val i = Intent(this, DadosActivity::class.java)

            i.putExtra("atividade", atividadeClicada)

            startActivity(i)

        }

        var tipos = resources.getStringArray(R.array.tipos)

        var tp = arrayListOf<String>()

        tp.add("Todos")
        tp.addAll(tipos)

        val adp = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tp)
        spnFiltro.adapter = adp

        spnFiltro.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long ) {

                filtrarLista(spnFiltro.selectedItem.toString())

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        //lstAtividades.setOnItemLongClickListener { parent, view, position, id ->

           // Toast.makeText(this, "Item: " + btnAdd.get(position) + ", removido", Toast.LENGTH_LONG)
              //  .show()

           // btnAdd.remove(btnAdd.get(position))

            //AtualizarLista()


        //}


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menuNormal) {
            carregarLista("normal")
        }

        if(item.itemId == R.id.menuData) {
            carregarLista("data")
        }


        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        carregarLista("normal")
    }

    private fun filtrarLista(tipo: String) {
        val atividadeDAO = FitAppDatabase.getInstance(this)?.atividadeDao()
        val listaAtividades: List<Atividade>

        if(tipo.equals("Todos")) {
            listaAtividades = atividadeDAO!!.listar()
        } else {
            listaAtividades = atividadeDAO!!.listarPorTipo(tipo)
        }

        val adp = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaAtividades)

        lstAtividades.adapter = adp
    }

    private fun carregarLista(ordem: String) {
        val atividadeDAO = FitAppDatabase.getInstance(this)?.atividadeDao()
        val listaAtividades: List<Atividade>

        if(ordem.equals("normal")) {
            listaAtividades = atividadeDAO!!.listar()
        } else if(ordem.equals("data")) {
            listaAtividades = atividadeDAO!!.listarData()
        } else {
            listaAtividades = atividadeDAO!!.listar()
        }

        val adp = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaAtividades)

        lstAtividades.adapter = adp
    }
}
