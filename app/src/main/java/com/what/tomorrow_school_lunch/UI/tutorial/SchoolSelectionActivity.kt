package com.what.tomorrow_school_lunch.UI.tutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.what.tomorrow_school_lunch.DataClass.UserSchoolInfo
import com.what.tomorrow_school_lunch.DataClass.UserSchoolInfo.School_Name
import com.what.tomorrow_school_lunch.Retrofit.SchoolSelect.*
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.Retrofit.SchoolInfoClient
import com.what.tomorrow_school_lunch.UI.SchoolPerformMain.SchoolPerformMainActivity
import com.what.tomorrow_school_lunch.UI.SplashActivity.Companion.prefs
import com.what.tomorrow_school_lunch.UI.newMain.fragment.BottomNavigationActivity
import com.what.tomorrow_school_lunch.databinding.ActivitySchoolSelectionBinding
import retrofit2.Response

class SchoolSelectionActivity : AppCompatActivity() {
    lateinit var binding :ActivitySchoolSelectionBinding
    private lateinit var retService : SchoolInfoAPI


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_school_selection)
        retService = SchoolInfoClient().getService().create(SchoolInfoAPI::class.java)



        binding.goStart.setOnClickListener {
            School_Name = prefs.getString("SCHUL_NM","")
            UserSchoolInfo.School_Code = prefs.getString("SD_SCHUL_CODE","")
            UserSchoolInfo.Atpt_Ofcdc_Code = prefs.getString("ATPT_OFCDC_SC_CODE","")
            prefs.setString("FIRST_START","true")

            val intent = Intent(this, SchoolPerformMainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.findSchoolBtn.setOnClickListener {


//            val service : com.what.tomorrow_school_lunch.Retrofit.newretrofit.SchoolInfoAPI = SchoolInfoClient().getService().create(com.what.tomorrow_school_lunch.Retrofit.newretrofit.SchoolInfoAPI::class.java)
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



            val schoolInfoSearchResponseLiveData : LiveData<Response<SchoolInfo>> = liveData {
                val response = retService.searchSchoolInfo(binding.schoolName.text.toString(), SchoolInfoAPI2.TYPE, SchoolInfoAPI2.PLNDEX,SchoolInfoAPI2.SIZE,SchoolInfoAPI2.KEY)
                emit(response)
            }

            schoolInfoSearchResponseLiveData.observe(this, Observer {
                val SchoolName = it.body()?.schoolInfo?.get(1)?.row?.get(0)?.SCHUL_NM
                val SchoolCode = it.body()?.schoolInfo?.get(1)?.row?.get(0)?.SD_SCHUL_CODE
                val AtptOfcdcCode = it.body()?.schoolInfo?.get(1)?.row?.get(0)?.ATPT_OFCDC_SC_CODE
//                for (a in it.body()?.schoolInfo?.get(1)?.row.size)
                binding.testTxt.text = SchoolName

                if (SchoolName != null && SchoolCode !=null && AtptOfcdcCode != null) {
                    prefs.setString("SCHUL_NM",SchoolName)
                    prefs.setString("SD_SCHUL_CODE",SchoolCode)
                    prefs.setString("ATPT_OFCDC_SC_CODE",AtptOfcdcCode)

                }else{
                    Toast.makeText(this,"????????? ???????????? ???????????????",Toast.LENGTH_SHORT).show()
                }
            })



//
//            SchoolInfoAPI.SCHUL_NM = binding.schoolName.text.toString()
//            Log.d(Contants.TAG,"?????? ?????? ???????????? : ${binding.schoolName.text}")
//            Log.d(Contants.TAG,"??? : ${SchoolInfoAPI.KEY}")
//
//            //?????? api ??????
//            SchoolInfoRetrofitManager.instance.searchSchool(searchTerm = "&SCHUL_NM=${binding.schoolName.text}", completion ={
//                    responseState, responseBody ->
//
//                when(responseState){















//                    RESPONSE_STATE.OKAY->{
//                        Log.i("??????","api ???????????? : $responseBody.")
//                    }
//                    RESPONSE_STATE.FAIL->{
//                        Toast.makeText(this,"api?????? ??????", Toast.LENGTH_SHORT).show()
//                        Log.i("??????","api ???????????? : $responseBody")
//                    }
//                }
//            } )
        }
    }

}