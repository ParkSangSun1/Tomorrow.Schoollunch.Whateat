package com.what.tomorrow_school_lunch.DataClass

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object Contants{
    const val TAG : String = "로그"
    var SCHOOLNAME=""
    private val auth: FirebaseAuth =FirebaseAuth.getInstance()

}

enum class RESPONSE_STATE{
    OKAY,
    FAIL
}

object SchoolInfoAPI{
    const val BASE_URL : String = "https://open.neis.go.kr/"
    const val KEY : String ="70e25a4d096f493f8e5e9a35366b4f60&Type=json&plndex=1&pSize=10"
    const val SEARCH_SCHOOLINFO : String = "hub/schoolInfo?"
//    var SCHUL_NM = ""
}