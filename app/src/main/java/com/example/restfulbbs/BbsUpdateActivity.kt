package com.example.restfulbbs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class BbsUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bbs_update)

        val data = intent.getParcelableExtra<BbsDto>("data")

        val id = findViewById<EditText>(R.id.UpdateEditName)
        val title = findViewById<EditText>(R.id.UpdateEditTitle)
        val content = findViewById<EditText>(R.id.UpdateEditContent)

        id.setText(data?.id)
        title.setText(data?.title)
        content.setText(data?.content)

        val updateBtn = findViewById<Button>(R.id.bbsUpdateCompleteBtn)

        updateBtn.setOnClickListener {

            var dto = BbsDao.getInstance().updateBbs_M(BbsDto(BbsDao.seq!!,"",0,0,0,title.text.toString(),content.text.toString(),"",0,0))
            Log.d("~~~~~updateBbs_M_MSG",dto)

            Toast.makeText(this, "수정되었습니다", Toast.LENGTH_LONG).show()
            val i = Intent(this,BbsActivity::class.java)
            startActivity(i)

        }
    }
}