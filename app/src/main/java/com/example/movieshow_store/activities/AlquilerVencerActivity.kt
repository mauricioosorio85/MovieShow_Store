package com.example.movieshow_store.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.movieshow_store.R
import com.example.movieshow_store.models.Alquiler
import com.example.movieshow_store.models.Cliente

import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_alquiler_vencer.*
import java.time.Year
import java.util.*
import kotlin.collections.ArrayList

class AlquilerVencerActivity : AppCompatActivity() {
    var realm = Realm.getDefaultInstance()
    lateinit var clienteResult : RealmResults<Cliente>
    lateinit var cliente:Cliente
    lateinit var alquileres : RealmResults<Alquiler>
    var arrayOfPorVencer :  MutableList<String> = ArrayList()
    lateinit var adapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alquiler_vencer)

        //obtenemos el id del cliente
        val id:Int = intent.getIntExtra("id",0)

        //obtenemos el cliente
        clienteResult = realm.where(Cliente::class.java).equalTo("id",id).findAll()
        cliente=clienteResult[0]!!

        alquileres = realm.where(Alquiler::class.java).findAll()

        for(alquiler in alquileres){
            if (alquiler.cliente!!.id.equals(id)){
                    //date trae la fecha actual
                var dateAlquiler: Date = Date()
                //Se crea una estancia de calendar
                var calendar: Calendar = Calendar.getInstance()
                //se asignan los valores de dateAlquiler a la variable calendar
                calendar.setTime(dateAlquiler)
                //se divide el año en dias y guarda el día del año
                val diaActual = calendar.get(Calendar.DAY_OF_YEAR)
                //guarda el año actual en la variable anno
                val anno = calendar.get(Calendar.YEAR)
                Log.i("DiaActualTAG", ""+diaActual)

                var calendarAlqui:Calendar = Calendar.getInstance()
                calendarAlqui.setTime(alquiler.fecha_devolucion)
                val dia_devolucion= calendarAlqui.get(Calendar.DAY_OF_YEAR)
                val anno_devolucion = calendarAlqui.get(Calendar.YEAR)
                Log.i("DiaDevolucionTAG", ""+dia_devolucion)


                if(anno_devolucion == anno){
                    if(diaActual<=dia_devolucion){
                        val result = dia_devolucion-diaActual
                        if (result<3){
                            arrayOfPorVencer.add(alquiler.cd!!.nombre+" vence en "+ result+" dias")
                        }
                    }
                }

            }
        }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOfPorVencer)
        list_por_vencer.adapter = adapter

    }
}