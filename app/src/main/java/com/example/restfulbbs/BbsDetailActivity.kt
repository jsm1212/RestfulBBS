package com.example.restfulbbs

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        val updateBtn = findViewById<Button>(R.id.bbsUpdateBtn)
        val deleteBtn = findViewById<Button>(R.id.bbsDeleteBtn)

        if( id.text.toString() != MemberDao.user?.id ){

            updateBtn.visibility = View.INVISIBLE
            deleteBtn.visibility = View.INVISIBLE

        }

        updateBtn.setOnClickListener {

            val i = Intent(this,BbsUpdateActivity::class.java)
            i.putExtra("data", data)
            startActivity(i)
        }

        deleteBtn.setOnClickListener {

            AlertDialog.Builder(this@BbsDetailActivity)
                .setTitle("게시글 삭제")
                .setMessage("정말 삭제하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("예", DialogInterface.OnClickListener{dialog, which ->
                    var del = BbsDao.getInstance().deleteBbs(BbsDao.seq!!)
                    Log.d("deleteBbs_MSG",del)

                    Toast.makeText(this, "삭제되었습니다", Toast.LENGTH_LONG).show()
                    val i = Intent(this,BbsActivity::class.java)
                    startActivity(i)
                })
                .setNegativeButton("아니오", DialogInterface.OnClickListener{dialog,which -> })
                .show()
        }


    }
}