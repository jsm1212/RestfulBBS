package com.example.restfulbbs

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BbsService {

    @GET("/getBbsList")
    fun getBbsList(): Call<List<BbsDto>>

    @POST("/writeBbs")
    fun writeBbs(@Body dto: BbsDto): Call<String>
}

class BbsDao {

    companion object{
        var bbsDao:BbsDao? = null
        var dto:String? = null

        fun getInstance(): BbsDao{
            if(bbsDao == null){
                bbsDao = BbsDao()
            }
            return bbsDao!!
        }
    }

    fun getBbsList():ArrayList<BbsDto>{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(BbsService::class.java)

        val call = service?.getBbsList()

        val response = call?.execute()

        return response?.body() as ArrayList<BbsDto>
    }

    fun writeBbs(dto: BbsDto):String?{

        var response: Response<String>? = null

        try {
            val retrofit = RetrofitClient.getInstance()

            val service = retrofit?.create(BbsService::class.java)

            val call = service?.writeBbs(dto)

            response = call?.execute()
        }catch(e:Exception){
            response = null
        }
        if(response == null) return null

        return response?.body() as String
    }
}