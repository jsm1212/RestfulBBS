package com.example.restfulbbs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class BbsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bbs_detail)

        val data = intent.getParcelableExtra<BbsDto>("data")

        val id = findViewById<EditText>(R.id.DetailEditName)
        val title = findViewById<EditText>(R.id.DetailEditTitle)
        val content = findViewById<EditText>(R.id.DetailEditContent)

        id.setText(data?.id)
        title.setText(data?.title)
        content.setText(data?.content)



    }
}