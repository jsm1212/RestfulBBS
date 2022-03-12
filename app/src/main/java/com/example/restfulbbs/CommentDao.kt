package com.example.restfulbbs


import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CommentService {
    @GET("/getCommentList")
    fun getCommentList(@Query("seq") seq:Int): Call<List<CommentDto>>

    @POST("/insertComment_M")
    fun insertComment_M(@Body dto: CommentDto) : Call<String>
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

    fun insertComment_M(dto: CommentDto): String?{
        var response: Response<String>? = null

        try {
            val retrofit = RetrofitClient.getInstance()

            val service = retrofit?.create(CommentService::class.java)

            val call = service?.insertComment_M(dto)

            response = call?.execute()
        }catch(e:Exception){
            response = null
        }
        if(response == null) return null

        return response?.body() as String
    }
}