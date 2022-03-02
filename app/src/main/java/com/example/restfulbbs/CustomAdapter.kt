package com.example.restfulbbs

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val context: Context, val bbsList:ArrayList<BbsDto>) : RecyclerView.Adapter<CustomAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val bbsNum = itemView.findViewById<TextView>(R.id.seqTxt)
        val bbsTitle = itemView.findViewById<TextView>(R.id.titleTxt)
        val bbsId = itemView.findViewById<TextView>(R.id.idTxt)
        val bbsReadcount = itemView.findViewById<TextView>(R.id.readcountTxt)

        fun bind(dataVo:BbsDto, context: Context){
            bbsNum.text = dataVo.seq.toString()
            bbsTitle.text = dataVo.title
            bbsId.text = "작성자: " + dataVo.id
            bbsReadcount.text = "조회수 :" + dataVo.readcount.toString()

            itemView.setOnClickListener{
                BbsDao.seq = bbsNum.text.toString().toInt()
                val getBbs = BbsDao.getInstance().getBbs(dataVo.seq)
                println("~~~dto:${getBbs.title}")
                val bbsdetail = BbsDao.getInstance().bbsdetail(dataVo.seq)
                println("~~~readcount:${bbsdetail.readcount}")

                Intent(context, BbsDetailActivity::class.java).apply {

                    putExtra("data", getBbs)

                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ItemViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.view_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ItemViewHolder, position: Int) {
        holder.bind(bbsList[position], context)
    }

    override fun getItemCount(): Int {
        return bbsList.size
    }

}