package com.what.tomorrow_school_lunch.DataClass.retrofit

import android.util.Log
import com.what.tomorrow_school_lunch.DataClass.Contants.TAG
import com.what.tomorrow_school_lunch.DataClass.SchoolInfoAPI.KEY
import com.what.tomorrow_school_lunch.DataClass.isJsonArray
import com.what.tomorrow_school_lunch.DataClass.isJsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

object SchoolInfoRetrofitClient {
    private var retrofitClient: Retrofit? = null

    fun getClient(baseUrl:String) : Retrofit?{

        //로깅 인터셉터 추가

        //okhttp 인스턴트 생성
        val client = OkHttpClient.Builder()
        //로그를 찍기 위해 로깅 인터셉터 추가
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
//                Log.d(Constants.TAG, "Retrofitclient - log() called / message: $message")

                when{
                    message.isJsonObject()->
                        //                                     4줄 띄우기 들여쓰기
                        Log.d(TAG, JSONObject(message).toString(4))
                    message.isJsonArray()->
                        //                                     4줄 띄우기 들여쓰기
                        Log.d(TAG, JSONObject(message).toString(4))
                    else ->{
                        try {
                            Log.d(TAG, JSONObject(message).toString(4))

                        }catch (e: Exception){
                            Log.d(TAG,message)

                        }
                    }

                }
            }

        })
        //레벨 설정
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        //위에서 설정한 로깅 인터셉터를 okhttp클라이언트에 추가한다
        client.addInterceptor(loggingInterceptor)


        //기본 파라매터 인터셉터 설정
        val baseParameterInterceptor : Interceptor = (object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                //오리지날 리퀘스트
                val originalRequest = chain.request()

                //쿼리 파라매터
                //예 )  https://open.neis.go.kr/hub/schoolInfo?Type=json&pIndex=1&pSize=100&SCHUL_NM=%EC%84%9C%EC%84%9D%EC%A4%91
                val addedUrl = originalRequest.url.newBuilder().addQueryParameter("KEY", KEY).build()

                val finalRequest = originalRequest.newBuilder().url(addedUrl)
                    .method(originalRequest.method, originalRequest.body)
                    .build()
                return chain.proceed(finalRequest)
            }
        })

        //위에서 설정한 기본파라메터 인터셉터를 okhttp클라이언트에 추가한다
        client.addInterceptor(baseParameterInterceptor)

        //커넥션 타임아웃
        client.connectTimeout(10, TimeUnit.SECONDS) //10초 동안 반응이 없으면
        client.readTimeout(10, TimeUnit.SECONDS)
        client.writeTimeout(10, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)//실패시 다시 시도할건지

        if(retrofitClient == null){

            //레트로핏 빌더를 통해 인스터스 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())

                //위에서 설정한 클라이언트로 레트로핏 클라이언트를 실행한다
                .client(client.build())

                .build()
        }
        return retrofitClient
    }
}