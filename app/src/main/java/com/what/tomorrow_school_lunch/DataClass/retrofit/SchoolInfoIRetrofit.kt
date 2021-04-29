package com.what.tomorrow_school_lunch.DataClass.retrofit

import com.google.gson.JsonElement
import com.what.tomorrow_school_lunch.DataClass.SchoolInfoAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolInfoIRetrofit {

    @GET(SchoolInfoAPI.SEARCH_SCHOOLINFO)
    fun searchSchoolInfo(@Query("SCHUL_NM") searchTerm: String) : Call<JsonElement>

}