package com.example.autoshopsplash

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Usuario_Entidad{
    @PrimaryKey
    var email:String
    var nombre:String
    var apellido:String
    var documento:String
    var direccion: String
    var telefono:String
    var password:String

    constructor(
        email: String,
        nombre: String,
        apellido: String,
        documento: String,
        direccion: String,
        telefono: String,
        password: String
    ) {
        this.email = email
        this.nombre = nombre
        this.apellido = apellido
        this.documento = documento
        this.direccion = direccion
        this.telefono = telefono
        this.password = password
    }
}