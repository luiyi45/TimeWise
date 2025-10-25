package com.example.timewize.iu.screens

import com.example.timewize.R
import android.view.LayoutInflater
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var btnTodo: Button
    private lateinit var btnHecho: Button
    private lateinit var btnProgreso: Button
    private lateinit var btnColecciones: Button
    private lateinit var tasksContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Inicializamos vistas
        btnTodo = findViewById(R.id.btnTodo)
        btnHecho = findViewById(R.id.btnHecho)
        btnProgreso = findViewById(R.id.btnProgreso)
        btnColecciones = findViewById(R.id.btnColecciones)
        tasksContainer = findViewById(R.id.tasksContainer)

        val botones = listOf(btnTodo, btnHecho, btnProgreso, btnColecciones)

        // Listener compartido para los botones
        val filterListener = View.OnClickListener { view ->
            botones.forEach { it.isSelected = false } // todos deseleccionados (blancos)
            val selected = view as Button
            selected.isSelected = true // el tocado se pone azul

            // Cambiar texto según estado
            botones.forEach { it.setTextColor(resources.getColor(R.color.black)) }
            selected.setTextColor(resources.getColor(android.R.color.white))

            val filtro = selected.text.toString()
            mostrarTareas(filtro)
        }

        // Asignar listeners y fondo selector a todos los botones
        botones.forEach {
            it.setOnClickListener(filterListener)
            it.setBackgroundResource(R.drawable.button_filter_selector)
        }

        // Seleccionar por defecto el primero ("Todo")
        btnTodo.isSelected = true
        btnTodo.setTextColor(resources.getColor(android.R.color.white))
        mostrarTareas("Todo")
    }

    // Muestra tareas según el filtro
    private fun mostrarTareas(filtro: String) {
        tasksContainer.removeAllViews()

        when (filtro) {
            "Todo" -> {
                agregarTarea("Grocery shopping app design", "Market Research", "Hecho")
                agregarTarea("Grocery shopping app design", "Competitive Analysis", "En progreso")
                agregarTarea("Uber Eats redesign challenge", "Create Low-fidelity Wireframe", "Empezar")
            }
            "Hecho" -> agregarTarea("Grocery shopping app design", "Market Research", "Hecho")
            "En progreso" -> agregarTarea("Grocery shopping app design", "Competitive Analysis", "En progreso")
            "Colecciones" -> agregarTarea("Uber Eats redesign challenge", "Create Low-fidelity Wireframe", "Empezar")
        }
    }

    // Crea una vista (tarjeta)
    private fun agregarTarea(titulo: String, subtitulo: String, estado: String) {
        val inflater = LayoutInflater.from(this)
        val card = inflater.inflate(R.layout.item_task_card, null)

        val txtTitulo = card.findViewById<TextView>(R.id.txtTitulo)
        val txtSubtitulo = card.findViewById<TextView>(R.id.txtSubtitulo)
        val txtEstado = card.findViewById<TextView>(R.id.txtEstado)

        txtTitulo.text = titulo
        txtSubtitulo.text = subtitulo
        txtEstado.text = estado

        tasksContainer.addView(card)
    }
}
