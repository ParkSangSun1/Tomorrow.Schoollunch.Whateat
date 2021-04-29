package com.what.tomorrow_school_lunch.DataClass.retrofit

import android.util.Log
import com.google.gson.JsonElement
import com.what.tomorrow_school_lunch.DataClass.Contants.TAG
import com.what.tomorrow_school_lunch.DataClass.RESPONSE_STATE
import com.what.tomorrow_school_lunch.DataClass.SchoolInfoAPI.BASE_URL
import retrofit2.Call
import retrofit2.Response

class SchoolInfoRetrofitManager {
    companion object {
        val instance = SchoolInfoRetrofitManager()
    }

    private val iRetrofit: SchoolInfoIRetrofit? =
        SchoolInfoRetrofitClient.getClient(BASE_URL)?.create(SchoolInfoIRetrofit::class.java)


    //학교 찾기 api 호출
    fun searchSchool(searchTerm: String?, completion: (RESPONSE_STATE, String) -> Unit) {

        val term = searchTerm.let {
            it
        } ?: ""

        val call = iRetrofit?.searchSchoolInfo(searchTerm = term).let {
            it
        } ?: return

        //call : Call<jsonElement> 이기 때문에 enqueue가 가능
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.i(TAG,"retrofitmanager - onresponse called / response: ${response.raw()}")
                completion(RESPONSE_STATE.OKAY, response.body().toString())

            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.i(TAG,"retrofitmanager - onfaiure called / t: $t")
                completion(RESPONSE_STATE.FAIL,t.toString())
            }

        })
    }
}