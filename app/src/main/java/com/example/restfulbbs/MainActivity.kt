package com.example.restfulbbs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editId = findViewById<EditText>(R.id.editId)
        val editPwd = findViewById<EditText>(R.id.editPwd)

        val loginBtn = findViewById<Button>(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val id = editId.text.toString().trim()
            val pwd = editPwd.text.toString().trim()

            val dto = MemberDao.getInstance().login(MemberDto(id,pwd,"","",3))
            if(dto != null){
                MemberDao.user = dto

                Toast.makeText(this, "${dto.name}님 환영합니다", Toast.LENGTH_LONG).show()

                val i = Intent(this, BbsActivity::class.java)
                startActivity(i)
            } else{
                Toast.makeText(this, "ID나 PWD를 확인하세요", Toast.LENGTH_LONG).show()
            }


        }

        val regiBtn = findViewById<Button>(R.id.goRegiBtn)

        regiBtn.setOnClickListener {
            val i = Intent(this, RegiActivity::class.java)
            startActivity(i)
        }
    }
}