package com.example.movieshow_store.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.movieshow_store.R
import com.example.movieshow_store.app.Config
import com.example.movieshow_store.models.Alquiler
import com.example.movieshow_store.models.Cd
import com.example.movieshow_store.models.Cliente
import com.example.movieshow_store.util.Util
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_alquiler_list.*
import kotlinx.android.synthetic.main.activity_cd_list.*
import java.util.*
import kotlin.collections.ArrayList

class AlquilerListActivity : AppCompatActivity(), View.OnClickListener {

    var realm = Realm.getDefaultInstance()
    lateinit var alquileres : RealmResults<Alquiler>
    lateinit var clienteResult : RealmResults<Cliente>
    lateinit var cliente:Cliente
    lateinit var cds:RealmResults<Cd>
    var arrayOfAlquiler :  MutableList<String> = ArrayList()
    var arrayOfAlquilerClient :  MutableList<String> = ArrayList()
    lateinit var adapter:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alquiler_list)

        //obtenemos el id del cliente
        val id:Int = intent.getIntExtra("id",0)

        //obtenemos el cliente
        clienteResult = realm.where(Cliente::class.java).equalTo("id",id).findAll()
        cliente=clienteResult[0]!!

        //pedimos todos los alquileres
        alquileres = realm.where(Alquiler::class.java).findAll()

        //verificamos que alquiler es de este usuario y lo cargamos en la lista
        for (alquiler in alquileres){
            if(alquiler.cliente!!.id.equals(cliente.id)){
                arrayOfAlquilerClient.add(alquiler.cd?.nombre.toString())
            }
        }

        //agregamos al listview los alquileres de este usuario
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOfAlquilerClient)
        list_item_alquiler.adapter = adapter

        //pedimos todos los cd para agregarlos al spinner
        cds = realm.where(Cd::class.java).findAll()

        //creamos el arreglo del spinner
        for (cd in cds){
            arrayOfAlquiler.add(cd.nombre)
        }
        //creamos el spiner y le agregamos las peliculas
        var adapterSpin = ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayOfAlquiler )
        adapterSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapterSpin


        adapter.setNotifyOnChange(true)
        btn_alquilar.setOnClickListener(this)

    }

    override fun onClick(opc: View?) {
        if(btn_alquilar.equals(opc)){
            if(valor_alquiler.text.toString()!=""){
                var position:Int = spinner.getSelectedItemPosition()
                var dateAlquiler:Date=Date()
                var calendar:Calendar = Calendar.getInstance()
                calendar.setTime(dateAlquiler)
                calendar.add(Calendar.DAY_OF_YEAR,editTextNumberSigned.text.toString().toInt())
                val precio_alquiler:Float = (valor_alquiler.text.toString()+"f").toFloat()
                var alquiler:Alquiler= Alquiler(Config.alquiler.incrementAndGet(),cliente,cds[position],
                    dateAlquiler,calendar.getTime(),precio_alquiler)

                val util:Util = Util()
                util.createNewAlquiler(alquiler)

                arrayOfAlquilerClient.add(cds[position]!!.nombre)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this, "es necesario colocar un precio", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}