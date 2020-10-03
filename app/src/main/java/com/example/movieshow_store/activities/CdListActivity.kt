package com.example.movieshow_store.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.movieshow_store.R
import com.example.movieshow_store.app.Config
import com.example.movieshow_store.models.Cd
import com.example.movieshow_store.util.Util
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_cd_list.*

class CdListActivity : AppCompatActivity(),View.OnClickListener, AdapterView.OnItemClickListener {

    //obtenemos la instancia por default de realm
    var realm = Realm.getDefaultInstance()
    lateinit var cds : RealmResults<Cd>
    lateinit var adapter:ArrayAdapter<String>
    var arrayOfDisc : MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cd_list)

        cds = realm.where(Cd::class.java).findAll()


        for (cd in cds){
            arrayOfDisc.add(cd.nombre)
        }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOfDisc)
        list_item.adapter = adapter
        adapter.setNotifyOnChange(true)

        btn_new_cd.setOnClickListener(this)
        list_item.setOnItemClickListener(this)

    }

    override fun onClick(opc: View?) {
        if (btn_new_cd.equals(opc)){
            var cd:Cd = Cd(Config.cd.incrementAndGet(),nameCD.text.toString(),(editTextNumberDecimal.text.toString()+"f").toFloat(),
                editTextTextPersonName4.text.toString(),editTextTextPersonName3.text.toString(),"Disponible")

            val util:Util = Util()
            util.createNewCd(cd)

            arrayOfDisc.add(nameCD.text.toString())
            adapter.notifyDataSetChanged()
        }
    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val element:Int =  adapter.getItemId(position).toInt()+1

        intent= Intent(this, CdEditActivity::class.java)
        intent.putExtra("id_cd",element)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }



}