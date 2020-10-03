package com.example.movieshow_store.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Sancion (
    @PrimaryKey
    var id:Int=0,
    var cliente:Cliente?=null,
    var alquiler:Alquiler?=null,
    var cargo:Float=0.0F
):RealmObject()