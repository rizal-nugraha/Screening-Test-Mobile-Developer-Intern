package id.kotlin.screeningtest_mobiledeveloperintern

import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
    @get:GET("people")
    val guests: Call<List<Guest?>?>?
}