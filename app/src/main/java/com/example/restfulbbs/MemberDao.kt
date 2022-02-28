package com.example.restfulbbs

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface MemberService{

    @POST("/login")
    fun login(@Body dto:MemberDto): Call<MemberDto>

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

    fun login(dto: MemberDto): MemberDto? {

        var response: Response<MemberDto>? = null
        try {
            val retrofit = RetrofitClient.getInstance()

            val service = retrofit?.create(MemberService::class.java)

            val call = service?.login(dto)

            response = call?.execute()
        }catch (e:Exception){
            response = null
        }
        if(response == null) return null

        return response?.body() as MemberDto
    }
}