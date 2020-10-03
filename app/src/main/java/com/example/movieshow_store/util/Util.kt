package com.example.movieshow_store.util

import android.content.Context
import android.widget.Toast
import com.example.movieshow_store.app.Config
import com.example.movieshow_store.models.Alquiler
import com.example.movieshow_store.models.Cd
import com.example.movieshow_store.models.Cliente
import io.realm.Realm
import java.util.*

open class Util{
     var realm: Realm = Realm.getDefaultInstance()
    lateinit var alquiler: Alquiler



  fun CargarDatos(){

        var cliente1: Cliente= Cliente(Config.cliente.incrementAndGet(),"Mauricio Osorio","colombia","+578963325542",
        "mau1524@gmail.com","8658565",Date(1994,5,18), Date(2020,8,25),"Medellin","Mau1524","123456")
        createNewCliente(cliente1)
        var cliente2: Cliente= Cliente(Config.cliente.incrementAndGet(),"Diana","colombia","+578963555882",
            "dia2525@gmail.com","86258965", Date(1992,8,25), Date(2020,3,15),"Bogota","dianaM15","123456")
        createNewCliente(cliente2)
        var cliente3: Cliente= Cliente(Config.cliente.incrementAndGet(),"Andres","colombia","+5781258878858",
            "andresL4@gmail.com","864558865", Date(1900,2,28), Date(2020,8,25),"Bogota","dianaM25","123456")
        createNewCliente(cliente3)
        var cliente4: Cliente= Cliente(Config.cliente.incrementAndGet(),"Carlos","colombia","+5781258878858",
          "carlos@gmail.com","864558865", Date(1900,2,28), Date(2020,8,25),"Bogota","carlos7","1234567")
        createNewCliente(cliente4)

        var cd:  Cd =  Cd(Config.cd.incrementAndGet(),"el señor de los anillos",250.3F,"Nuevo","Bodega 1","Alquilado")
          createNewCd(cd)
          var cd1: Cd = Cd(Config.cd.incrementAndGet(),"Spiderman",120.0F,"Usado","Bodega 2","Disponible")
          createNewCd(cd1)
          var cd2: Cd = Cd(Config.cd.incrementAndGet(),"Harry Potter",250.3F,"Nuevo","Bodega 1","Disponible")
          createNewCd(cd2)
          var cd3: Cd = Cd(Config.cd.incrementAndGet(),"Batman",120.0F,"Usado","Bodega 1","Disponible")
          createNewCd(cd3)
          var cd4: Cd = Cd(Config.cd.incrementAndGet(),"Avengers",250.3F,"Nuevo","Bodega 1","Disponible")
          createNewCd(cd4)
          var cd5: Cd = Cd(Config.cd.incrementAndGet(),"Rapido y Furioso",250.3F,"Nuevo","Bodega 2","Disponible")
          createNewCd(cd5)

  }
    //aquí se llevan los datos a la base de datos
    private fun createNewCliente(cliente: Cliente) {
        realm.executeTransaction { realm ->
            realm.copyToRealm(cliente)
        }
    }

     fun createNewCd(cd: Cd) {
            realm.executeTransaction { realm ->
                realm.copyToRealm(cd)
            }
    }

     fun createNewAlquiler(alquiler: Alquiler) {
        realm.executeTransaction { realm ->
            realm.copyToRealm(alquiler)
        }
    }


    //funcion toast para cualquier actividad
    fun setToast(string:String,context:Context){
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }


}