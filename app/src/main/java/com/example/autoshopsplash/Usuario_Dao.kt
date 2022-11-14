package com.example.autoshopsplash

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Usuario_Dao {
    @Query("select * from Usuario_Entidad")
    suspend fun consultarUsuarios():List<Usuario_Entidad>

    @Query("select * from Usuario_Entidad where email=:emailUsu")
   suspend fun buscarUsuario(emailUsu: String): Usuario_Entidad

    @Insert
    suspend fun agregarUsuario(usuario:Usuario_Entidad)

    @Query("update Usuario_Entidad set telefono=:tel, direccion=:dir, password=:pass where email=:emailUsu")
    suspend fun actualizarUsuario(tel:String,dir:String, pass:String, emailUsu: String)

    @Query("delete from Usuario_Entidad where email=:emailUsu")
    suspend fun eliminarUsuario(emailUsu: String)
}