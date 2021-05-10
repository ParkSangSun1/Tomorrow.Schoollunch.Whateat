package com.what.tomorrow_school_lunch.Retrofit.SchoolMeals

import com.what.tomorrow_school_lunch.Retrofit.SchoolSelect.SchoolInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolMealAPI {

    @GET("hub/mealServiceDietInfo")
    suspend fun searchSchoolMeal(
        @Query(value = "ATPT_OFCDC_SC_CODE") ATPT_OFCDC_SC_CODE: String,
        @Query(value = "SD_SCHUL_CODE") SD_SCHUL_CODE: String,
        @Query(value = "MLSV_YMD") MLSV_YMD: String,
        @Query(value = "Type") Type: String,
        @Query(value = "plndex") plndex: String,
        @Query(value = "pSize") pSize: String,
        @Query(value = "KEY") KEY: String
    ): Response<SchoolMealInfo>
}