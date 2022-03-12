package com.example.restfulbbs

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import kotlinx.android.synthetic.main.activity_main.*
import com.kakao.sdk.common.model.AuthErrorCause.*
import com.kakao.sdk.user.UserApiClient
import com.kakao.util.helper.log.Tag

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 디버그 키 : 5e+LL1wohWfCddne5nra5dCl8S4=
        // val KeyHash = Utility.getKeyHash(this)
        // Log.d("~~~~~Hash", KeyHash)

        val editId = findViewById<EditText>(R.id.editId)
        val editPwd = findViewById<EditText>(R.id.editPwd)

        val loginBtn = findViewById<Button>(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val id = editId.text.toString().trim()
            val pwd = editPwd.text.toString().trim()

            val dto = MemberDao.getInstance().login_M(MemberDto(id,pwd,"","",3))
            if(dto != null){
                MemberDao.user = dto

                Toast.makeText(this, "${dto.name}님 환영합니다", Toast.LENGTH_LONG).show()

                val i = Intent(this, BbsActivity::class.java)
                startActivity(i)
            } else{
                Toast.makeText(this, "ID나 PWD를 확인하세요", Toast.LENGTH_LONG).show()
            }


        }

        val regiBtn = findViewById<Button>(R.id.goRegiBtn)

        regiBtn.setOnClickListener {
            val i = Intent(this, RegiActivity::class.java)
            startActivity(i)
        }


        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                        Log.d("~~~~error", error.toString())
                    }
                }
            }
            else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                UserApiClient.instance.me { user, error ->
                    if(error!=null){
                        Log.e(TAG,"사용자 정보 요청 실패", error)
                    }
                    else if(user != null) {
                        Log.i(TAG, "사용자 정보 요청 성공+${user.id}")
                        Log.i(TAG, "사용자 정보 요청 성공+${user.kakaoAccount!!.profile!!.nickname}")

                        val idmsg = MemberDao.getInstance().getId_M(MemberDto(user.kakaoAccount!!.profile!!.nickname,"","","",3))

                        if(idmsg == "NO"){

                            val dto = MemberDao.getInstance().login_M(MemberDto(user.kakaoAccount!!.profile!!.nickname,user.id.toString(),user.kakaoAccount!!.profile!!.nickname,"",3))
                            if(dto != null) {
                                MemberDao.user = dto
                            }

                        }else{

                            val msg = MemberDao.getInstance().addMember_M(MemberDto(user.kakaoAccount!!.profile!!.nickname,user.id.toString(),user.kakaoAccount!!.profile!!.nickname,"",3))
                            System.out.println(msg)

                        }


                        val i = Intent(this,BbsActivity::class.java)
                        startActivity(i)
                    }
                }
            }
        }
        kakao_login_button.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

}