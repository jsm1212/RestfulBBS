package com.example.restfulbbs


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentService {
    @GET("/getCommentList")
    fun getCommentList(@Query("seq") seq:Int): Call<List<CommentDto>>
}

class CommentDao {

    companion object{
        var commentDao:CommentDao? = null

        fun getInstance(): CommentDao{
            if(commentDao == null){
                commentDao = CommentDao()
            }
            return commentDao!!
        }
    }

    fun getCommentList(seq: Int):ArrayList<CommentDto>{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(CommentService::class.java)

        val call = service?.getCommentList(seq)

        val response = call?.execute()

        return response?.body() as ArrayList<CommentDto>
    }
}