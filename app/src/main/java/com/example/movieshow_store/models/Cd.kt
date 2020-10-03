package com.example.movieshow_store.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Cd (
    @PrimaryKey
    var id:Int=0,
    var nombre:String="",
    var precio:Float=0.0F,
    var condicion:String="",
    var ubicacion:String="",
    var estado:String=""
):RealmObject()