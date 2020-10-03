package com.example.movieshow_store.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*
import javax.annotation.Nullable

open class Cliente (
    @PrimaryKey
    var id:Int=0,
    var name: String="",
    var direccion:String="",
    var telefono:String="",
    var email:String="",
    var dni:String="",
    var fecha_nacimiento:Date= Date(),
    var fecha_inscripcion:Date=Date(),
    var estado:String="",
    var usuario:String="",
    var contrasenna:String=""
): RealmObject()