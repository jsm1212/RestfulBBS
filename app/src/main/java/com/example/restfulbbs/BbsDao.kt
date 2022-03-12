package com.example.restfulbbs

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BbsService {

    @GET("/getBbsList")
    fun getBbsList(): Call<List<BbsDto>>

    @POST("/writeBbs_M")
    fun writeBbs_M(@Body dto: BbsDto): Call<String>

    @GET("/bbsdetail")
    fun bbsdetail(@Query("seq") seq:Int): Call<BbsDto>

    @GET("/getBbs")
    fun getBbs(@Query("seq") seq:Int): Call<BbsDto>

    @POST("/updateBbs")
    fun updateBbs(@Body dto: BbsDto): Call<String>

    @GET("/deleteBbs")
    fun deleteBbs(@Query("seq") seq:Int): Call<String>
}

class BbsDao {

    companion object{
        var bbsDao:BbsDao? = null
        var seq:Int? = null

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

    fun writeBbs_M(dto: BbsDto):String?{

        var response: Response<String>? = null

        try {
            val retrofit = RetrofitClient.getInstance()

            val service = retrofit?.create(BbsService::class.java)

            val call = service?.writeBbs_M(dto)

            response = call?.execute()
        }catch(e:Exception){
            response = null
        }
        if(response == null) return null

        return response?.body() as String
    }


    fun getBbs(seq: Int): BbsDto{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(BbsService::class.java)

        val call = service?.getBbs(seq)

        val response = call?.execute()

        return response?.body() as BbsDto
    }

    fun bbsdetail(seq: Int): BbsDto{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(BbsService::class.java)

        val call = service?.bbsdetail(seq)

        val response = call?.execute()

        return response?.body() as BbsDto
    }

    fun updateBbs(dto: BbsDto): String{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(BbsService::class.java)

        val call = service?.updateBbs(dto)

        val response = call?.execute()

        return response?.body() as String
    }

    fun deleteBbs(seq: Int): String{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(BbsService::class.java)

        val call = service?.deleteBbs(seq)

        val response = call?.execute()

        return response?.body() as String
    }
}