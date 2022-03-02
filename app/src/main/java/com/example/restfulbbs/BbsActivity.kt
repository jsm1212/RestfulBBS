package com.example.restfulbbs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BbsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bbs)

        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val bbslist = BbsDao.getInstance().getBbsList()

        val mAdapter = CustomAdapter(this, bbslist)
        recyclerView.adapter = mAdapter

        val layout = LinearLayoutManager(this)
        recyclerView.layoutManager = layout
        recyclerView.setHasFixedSize(true)


        val insertBtn = findViewById<Button>(R.id.insertBtn)
        insertBtn.setOnClickListener {
            val i = Intent(this, BbsWriteActivity::class.java)
            startActivity(i)
        }


    }
}