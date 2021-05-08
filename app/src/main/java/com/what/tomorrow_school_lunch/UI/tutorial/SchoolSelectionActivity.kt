package com.what.tomorrow_school_lunch.UI.tutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.what.tomorrow_school_lunch.DataClass.newretrofit.*
import com.what.tomorrow_school_lunch.UI.main.MainActivity
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.databinding.ActivitySchoolSelectionBinding
import retrofit2.Response
import retrofit2.create
import java.net.URLDecoder

class SchoolSelectionActivity : AppCompatActivity() {
    lateinit var binding :ActivitySchoolSelectionBinding
    private lateinit var retService : SchoolInfoAPI


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_school_selection)
        retService = SchoolInfoClient().getService().create(SchoolInfoAPI::class.java)


        binding.goStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.findSchoolBtn.setOnClickListener {

//            val service : com.what.tomorrow_school_lunch.DataClass.newretrofit.SchoolInfoAPI = SchoolInfoClient().getService().create(com.what.tomorrow_school_lunch.DataClass.newretrofit.SchoolInfoAPI::class.java)
//
//            service.searchSchoolInfo("?${SchoolInfoAPI2.SCHUL_NM}","&${SchoolInfoAPI2.TYPE}", "&${SchoolInfoAPI2.PLNDEX}", "&${SchoolInfoAPI2.SIZE}", "&${SchoolInfoAPI2.KEY}" )
//                .enqueue(object : Callback<SchoolInfoDTO> {
//                    override fun onResponse(
//                        call: Call<SchoolInfoDTO>,
//                        response: Response<SchoolInfoDTO>
//                    ) {
//                        if (response.body() != null){
//                            binding.testTxt.text = response.body()!!.SCHUL_NM
//                        }
//                    }
//
//                    override fun onFailure(call: Call<SchoolInfoDTO>, t: Throwable) {
//
//                    }
//
//                })



            Log.d("확인",binding.schoolName.text.toString())
            val schoolInfoSearchResponseLiveData : LiveData<Response<SchoolInfo>> = liveData {
                val response = retService.searchSchoolInfo(binding.schoolName.text.toString(), SchoolInfoAPI2.TYPE, SchoolInfoAPI2.PLNDEX,SchoolInfoAPI2.SIZE,SchoolInfoAPI2.KEY)
                emit(response)
            }

            schoolInfoSearchResponseLiveData.observe(this, Observer {
                val SchoolName = it.body()?.schoolInfo?.get(1)?.row?.get(0)?.SCHUL_NM
//                for (a in it.body()?.schoolInfo?.get(1)?.row.size)
                binding.testTxt.text = SchoolName
            })



//
//            SchoolInfoAPI.SCHUL_NM = binding.schoolName.text.toString()
//            Log.d(Contants.TAG,"학교 이름 액티비티 : ${binding.schoolName.text}")
//            Log.d(Contants.TAG,"키 : ${SchoolInfoAPI.KEY}")
//
//            //검색 api 호출
//            SchoolInfoRetrofitManager.instance.searchSchool(searchTerm = "&SCHUL_NM=${binding.schoolName.text}", completion ={
//                    responseState, responseBody ->
//
//                when(responseState){















//                    RESPONSE_STATE.OKAY->{
//                        Log.i("로그","api 호출성공 : $responseBody.")
//                    }
//                    RESPONSE_STATE.FAIL->{
//                        Toast.makeText(this,"api호출 에러", Toast.LENGTH_SHORT).show()
//                        Log.i("로그","api 호출실패 : $responseBody")
//                    }
//                }
//            } )
        }
    }

}