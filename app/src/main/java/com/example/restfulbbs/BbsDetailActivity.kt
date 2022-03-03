package com.example.restfulbbs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        var recyclerView = findViewById<RecyclerView>(R.id.reply_recyclerView)

        val commentlist = CommentDao.getInstance().getCommentList(BbsDao.seq!!)

        val mAdapter2 = CustomAdapter2(this, commentlist)
        recyclerView.adapter = mAdapter2

        val layout = LinearLayoutManager(this)
        recyclerView.layoutManager = layout
        recyclerView.setHasFixedSize(true)

        val replyBtn = findViewById<Button>(R.id.replyBtn)

        replyBtn.setOnClickListener {
            val i = Intent(this, CommentActivity::class.java)
            startActivity(i)
        }
    }
}