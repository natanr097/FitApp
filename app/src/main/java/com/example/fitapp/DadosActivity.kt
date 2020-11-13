package com.example.fitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_dados.*

class DadosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados)

        val atividade = intent.getSerializableExtra("atividade") as Atividade

        txtTitulo.text = atividade.titulo
        txtDescricao.text = atividade.descricao
        txtData.text = atividade.data
        txtTempo.text = atividade.tempo
        txtTipo.text = atividade.tipo
        txtDistancia.text = atividade.distancia.toString()

        if(atividade.tipo.equals("Academia")) {
            txtDistancia.visibility = View.INVISIBLE
        }

    }
}
