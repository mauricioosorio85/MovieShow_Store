package com.example.movieshow_store.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Alquiler(
    @PrimaryKey
    var id:Int=0,
    var cliente:Cliente? = null,
    var cd:Cd? = null,
    var fecha_prestamo:Date = Date(),
    var fecha_devolucion:Date = Date(),
    var precio_alquiler:Float=0.0f

):RealmObject()