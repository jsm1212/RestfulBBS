package com.example.restfulbbs

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter2(val context: Context, val commentList:ArrayList<CommentDto>) : RecyclerView.Adapter<CustomAdapter2.ItemViewHolder>(){
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val commentId = itemView.findViewById<TextView>(R.id.commentIdText)
        val commentContent = itemView.findViewById<TextView>(R.id.commentContentText)
        val commentWdate = itemView.findViewById<TextView>(R.id.commentWdateText)

        fun bind(dataVo:CommentDto, context: Context){
            commentId.text = dataVo.id
            commentContent.text = dataVo.content
            commentWdate.text = dataVo.wdate

            itemView.setOnClickListener{

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter2.ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_item_layout2, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomAdapter2.ItemViewHolder, position: Int) {
        holder.bind(commentList[position], context)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }
}