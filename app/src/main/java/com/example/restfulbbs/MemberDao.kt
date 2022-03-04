package com.example.restfulbbs

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface MemberService{

    @POST("/login_M")
    fun login_M(@Body dto:MemberDto): Call<MemberDto>

    @POST("/addMember_M")
    fun addMember_M(@Body dto:MemberDto): Call<String>

    @POST("/getId_M")
    fun getId_M(@Body dto:MemberDto): Call<String>

    @POST("/checkEmail")
    fun checkEmail(@Body dto:MemberDto): Call<String>

}
class MemberDao {

    companion object{
        var memberDao:MemberDao? = null
        var user:MemberDto? = null

        fun getInstance():MemberDao{
            if(memberDao == null){
                memberDao = MemberDao()
            }
            return memberDao!!
        }
    }

    fun login_M(dto: MemberDto): MemberDto? {

        var response: Response<MemberDto>? = null
        try {
            val retrofit = RetrofitClient.getInstance()

            val service = retrofit?.create(MemberService::class.java)

            val call = service?.login_M(dto)

            response = call?.execute()
        }catch (e:Exception){
            response = null
        }
        if(response == null) return null

        return response?.body() as MemberDto
    }

    fun addMember_M(dto: MemberDto): String?{
        var response: Response<String>?= null
        try {
            val retrofit = RetrofitClient.getInstance()

            val service = retrofit?.create(MemberService::class.java)

            val call = service?.addMember_M(dto)

            response = call?.execute()
        }catch(e:Exception){
            response = null
        }
        if(response == null) return null

        return response?.body() as String
    }

    fun getId_M(dto: MemberDto): String?{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(MemberService::class.java)

        val call = service?.getId_M(dto)

        val response = call?.execute()

        return response?.body() as String
    }

    fun checkEmail(dto: MemberDto): String?{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(MemberService::class.java)

        val call = service?.checkEmail(dto)

        val response = call?.execute()

        return response?.body() as String
    }
}