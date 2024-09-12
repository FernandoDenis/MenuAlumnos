package com.example.recyclerviewstudents.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewstudents.Alumno
import com.example.recyclerviewstudents.databinding.ItemAlumnoBinding

class AlumnoViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemAlumnoBinding.bind(view)

    fun render(alumnoModel: Alumno,onClickListener:(Alumno) -> Unit){
        binding.tvAlumnoName.text = alumnoModel.alumno
        binding.tvEmail.text = alumnoModel.correo
        binding.tvCuenta.text = alumnoModel.cuenta
        Glide.with(binding.ivAlumno.context).load(alumnoModel.imagen).into(binding.ivAlumno)
        itemView.setOnClickListener{ onClickListener(alumnoModel) }
    }
}