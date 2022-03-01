package com.example.restfulbbs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regi)

        val editId = findViewById<EditText>(R.id.regiEditId)
        val editPwd = findViewById<EditText>(R.id.regiEditPwd)
        val editPwd2 = findViewById<EditText>(R.id.regiEditPwd2)
        val editName = findViewById<EditText>(R.id.regiEditName)
        val editEmail = findViewById<EditText>(R.id.regiEditEmail)

        val duplText = findViewById<TextView>(R.id.duplText)
        val duplBtn = findViewById<Button>(R.id.duplBtn)

        duplBtn.setOnClickListener {
            val id = editId.text.toString()

            val msg = MemberDao.getInstance().getId(MemberDto(id,"","","",3))
            if(msg == "NO"){
                duplText.text= "이미 사용중인 아이디입니다."
                editId.setText("")
            }else{
                duplText.text= "사용 가능한 아이디입니다."
            }
        }

        val regiBtn = findViewById<Button>(R.id.regiBtn)

        regiBtn.setOnClickListener {
            val id = editId.text.toString()
            val pwd = editPwd.text.toString()
            val pwd2 = editPwd2.text.toString()
            val name = editName.text.toString()
            val email = editEmail.text.toString()

            val msg = MemberDao.getInstance().addMember(MemberDto(id,pwd,name,email,3))
            if(msg == "YES" && pwd == pwd2){
                Toast.makeText(this, "가입이 완료되었습니다.", Toast.LENGTH_LONG).show()

                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }else{
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
            }
        }


    }
}