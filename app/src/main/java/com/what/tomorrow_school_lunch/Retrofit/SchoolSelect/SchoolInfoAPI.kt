package com.what.tomorrow_school_lunch.Retrofit.SchoolSelect

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolInfoAPI {
//    @FormUrlEncoded
//@Headers("Content-Type: application/json; charset=utf-8")

//    SCHUL_NM={SCHUL_NM}&{Type}&{plndex}&{pSize}&KEY={KEY}

@GET("hub/schoolInfo")
    suspend fun searchSchoolInfo(
    @Query(value = "SCHUL_NM") SCHUL_NM:String,
    @Query(value = "Type") Type:String,
    @Query(value = "plndex") plndex: String,
    @Query(value = "pSize") pSize: String,
    @Query(value = "KEY") KEY:String
    ): Response<SchoolInfo>


//    @GET("hub/schoolInfo/{SCHUL_NM}/{Type}/{plndex}/{pSize}/{KEY}")
//    suspend fun searchSchoolInfo(
//        @Query("SCHUL_NM") SCHUL_NM:String,
//        @Query("Type") Type:String,
//        @Query("plndex") plndex: String,
//        @Query("pSize") pSize: String,
//        @Query("KEY") KEY:String
//    ): Response<SchoolInfoDTO>
}