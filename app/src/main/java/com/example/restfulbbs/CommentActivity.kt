package com.example.restfulbbs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CommentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        var recyclerView = findViewById<RecyclerView>(R.id.comment_recyclerView)

        val commentlist = CommentDao.getInstance().getCommentList(BbsDao.seq!!)

        val mAdapter2 = CustomAdapter2(this, commentlist)
        recyclerView.adapter = mAdapter2

        val layout = LinearLayoutManager(this)
        recyclerView.layoutManager = layout
        recyclerView.setHasFixedSize(true)

        val id = findViewById<TextView>(R.id.commentId)
        id.text = MemberDao.user?.id


        val commentBtn = findViewById<Button>(R.id.commentBtn)
        commentBtn.setOnClickListener {
                val content = findViewById<EditText>(R.id.editCommentContent)

                var dto = CommentDao.getInstance()
                    .insertComment_M(CommentDto(BbsDao.seq!!, MemberDao.user?.id, content.text.toString(), ""))
                Log.d("~~~~~댓글 작성내용", dto!!)
                if (dto != null) {

                    Toast.makeText(this, "댓글이 추가되었습니다", Toast.LENGTH_LONG).show()
                    val i = Intent(this, BbsDetailActivity::class.java)
                    startActivity(i)

                }

        }
    }
}