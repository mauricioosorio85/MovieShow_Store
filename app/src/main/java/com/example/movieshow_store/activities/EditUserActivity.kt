package com.example.movieshow_store.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.movieshow_store.R
import com.example.movieshow_store.models.Cliente
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_edit_user.*
import java.util.*

class EditUserActivity : AppCompatActivity(), View.OnClickListener {
    var realm = Realm.getDefaultInstance()
    lateinit var cliente : RealmResults<Cliente>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        btn_guardar_cambios.setOnClickListener(this)
        //obtenemos el id del usuario que vamos a modificar
        val id:Int = intent.getIntExtra("id",0)

        //buscamos el cliente en la base de datos por su id
        cliente = realm.where(Cliente::class.java).equalTo("id",id).findAll()

        //hacemos match con cada uno de los datos
        edt_name.setText(cliente[0]?.name)
        edt_phone.setText(cliente[0]?.telefono)
        editTextTextMultiLine2.setText(cliente[0]?.direccion)
        editTextTextPersonName2.setText(cliente[0]?.usuario)
        editTextTextPassword.setText(cliente[0]?.contrasenna)
        editTextTextPassword2.setText(cliente[0]?.contrasenna)

    }

    override fun onClick(opc: View?) {
        if (btn_guardar_cambios.equals(opc)){
            val correo:String = cliente[0]?.email.toString()
            val dni:String = cliente[0]?.dni.toString()
            val date_nacimiento: Date? = cliente[0]?.fecha_nacimiento
            val date_inscripcion: Date? = cliente[0]?.fecha_inscripcion
            val estado = cliente[0]?.estado.toString()
            val id:Int? = cliente[0]?.id


            if(editTextTextPassword2.text.toString().equals(editTextTextPassword.text.toString())) {
                var cliente: Cliente = Cliente(
                    id!!, //indica que el id existe
                    edt_name.text.toString(),
                    editTextTextMultiLine2.text.toString(),
                    edt_phone.text.toString(),
                    correo,
                    dni,
                    date_nacimiento!!,
                    date_inscripcion!!,
                    estado,
                    editTextTextPersonName2.text.toString(),
                    editTextTextPassword.text.toString()
                )

                realm.executeTransaction { realm ->
                    realm.copyToRealmOrUpdate(cliente)
                }
                startActivity(Intent(this, MenuActivity::class.java))
            }else{
                Toast.makeText(this, "las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }
}