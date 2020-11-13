package com.example.fitapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AtividadeDAO {

    @Insert
    fun salvar(a: Atividade)

    @Query("SELECT * FROM Atividade")
    fun listar(): List<Atividade>

    @Query("SELECT * FROM Atividade ORDER BY data")
    fun listarData(): List<Atividade>

    @Query("SELECT * FROM Atividade WHERE tipo = :t")
    fun listarPorTipo(t: String): List<Atividade>
}