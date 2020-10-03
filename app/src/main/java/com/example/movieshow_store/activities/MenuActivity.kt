package com.example.movieshow_store.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.movieshow_store.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        img_exit.setOnClickListener(this)

        btn_infalqui.setOnClickListener(this)
        btn_inflist.setOnClickListener(this)
        btn_infperso.setOnClickListener(this)
        btn_proxvencer.setOnClickListener(this)


    }

    override fun onClick(opc: View?) {
        if(img_exit.equals(opc)){
            startActivity(Intent(this, MainActivity::class.java))
        }

        if(btn_infperso.equals(opc)){
            val id:Int = intent.getIntExtra("id",0)

            intent=Intent(this, EditUserActivity::class.java)
            intent.putExtra("id",id )
            startActivity(intent)

        }

        if(btn_inflist.equals(opc)){
            startActivity(Intent(this, CdListActivity::class.java))
        }

        if(btn_infalqui.equals(opc)){
            val id:Int = intent.getIntExtra("id",0)
            intent=Intent(this, AlquilerListActivity::class.java)
            intent.putExtra("id",id )
            startActivity(intent)
        }

        if(btn_proxvencer.equals(opc)){
            val id:Int = intent.getIntExtra("id",0)
            intent=Intent(this, AlquilerVencerActivity::class.java)
            intent.putExtra("id",id )
            startActivity(intent)
        }
    }
}