package com.example.movieshow_store.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.movieshow_store.R
import com.example.movieshow_store.models.Cliente
import com.example.movieshow_store.util.Util
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
/*
* implementas la interfaz View.OnClickListener
* se manejan los clicks con condicionales if fuera del onCreate para tener un código mas limpio
*
* */
class MainActivity : AppCompatActivity(),View.OnClickListener{
//obtenemos la instancia por defoult de realm
    var realm = Realm.getDefaultInstance()
    lateinit var clientes : RealmResults<Cliente>
    //aqui se crea la estancia para crear las funciones creadas en la clase util
    var util: Util = Util()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //nos traemos todos los clientes de la base de datos
        clientes = realm.where(Cliente::class.java).findAll()

       //consultamos si se trajo clientes si no trajo utilizamos los datos precargados
        if(clientes.isEmpty()){
            util.CargarDatos()
        }

        clientes = realm.where(Cliente::class.java).findAll()

        btn_login.setOnClickListener(this)
    }


    override fun onClick(opc: View?) {
        if(btn_login.equals(opc)){
            var bandera:Int = 0
            for (i in clientes){
                if(i.usuario.equals(edt_user.text.toString())&&(i.contrasenna.equals(edt_pass.text.toString()))){
                    intent=Intent(this, MenuActivity::class.java)
                    intent.putExtra("id",i.id )
                    startActivity(intent)
                    bandera=1
                }
            }
            if (bandera==0){
                Toast.makeText(this, "usuario o contraseña invalidos", Toast.LENGTH_SHORT).show()
            }

            //forma de usar el toast creado en el util
            //util.setToast("prueba",this)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


}