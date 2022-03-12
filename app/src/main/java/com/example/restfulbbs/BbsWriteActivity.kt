package com.example.restfulbbs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class BbsWriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bbs_write)


        val writeBtn = findViewById<Button>(R.id.writeBtn)
        val id = findViewById<TextView>(R.id.textId)
        id.text = MemberDao.user?.id

        writeBtn.setOnClickListener {

            val title = findViewById<EditText>(R.id.editTitle)
            val content = findViewById<EditText>(R.id.editContent)

            var dto = BbsDao.getInstance().writeBbs_M(BbsDto(0,MemberDao.user?.id,0,0,0,title.text.toString(),content.text.toString(),"",0,0))
            Log.d("~~~~~게시글 등록", dto!!)
            if(dto != null){

                Toast.makeText(this, "추가되었습니다", Toast.LENGTH_LONG).show()

                val i = Intent(this, BbsActivity::class.java)
                startActivity(i)
            }

        }

    }
}