package com.example.restfulbbs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class BbsWriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bbs_write)


        val writeBtn = findViewById<Button>(R.id.writeBtn)

        writeBtn.setOnClickListener {
            val id = findViewById<TextView>(R.id.textId)
            id.text = MemberDao.user?.id

            val title = findViewById<EditText>(R.id.editTitle)
            val content = findViewById<EditText>(R.id.editContent)

            var dto = BbsDao.getInstance().writeBbs(BbsDto(0,MemberDao.user?.id,0,0,0,title.text.toString(),content.text.toString(),"",0,0))
            if(dto != null){
                BbsDao.dto = dto

                Toast.makeText(this, "추가되었습니다", Toast.LENGTH_LONG).show()

                val i = Intent(this, BbsActivity::class.java)
                startActivity(i)
            }

        }

    }
}