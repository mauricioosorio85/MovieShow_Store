package com.example.movieshow_store.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.movieshow_store.R
import com.example.movieshow_store.models.Cd
import com.example.movieshow_store.models.Cliente
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_cd_edit.*

class CdEditActivity : AppCompatActivity(), View.OnClickListener {

    var realm = Realm.getDefaultInstance()
    lateinit var cd : RealmResults<Cd>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cd_edit)

        val id:Int = intent.getIntExtra("id_cd",1)
        cd = realm.where(Cd::class.java).equalTo("id",id).findAll()

        edt_cd_name.setText(cd[0]?.nombre.toString())
        edt_cd_condicion.setText(cd[0]?.condicion.toString())
        edt_cd_estado.setText(cd[0]?.estado.toString())
        edt_cd_ubicacion.setText(cd[0]?.ubicacion.toString())
        edt_cd_precio.setText(cd[0]?.precio.toString())

        btn_guardar_cd.setOnClickListener(this)
    }

    override fun onClick(btn: View?) {
        if(btn_guardar_cd.equals(btn)){
            val id = cd[0]?.id
            val precio:Float = (edt_cd_precio.text.toString()+"f").toFloat()

            val nuevoDisc:Cd = Cd(id!!,edt_cd_name.text.toString(),precio,edt_cd_condicion.text.toString(),
                edt_cd_ubicacion.text.toString(),edt_cd_estado.text.toString())

            realm.executeTransaction { realm ->
                realm.copyToRealmOrUpdate(nuevoDisc)
            }
            startActivity(Intent(this, MenuActivity::class.java))

        }
    }
}