package org.d3if3084.nebula.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3084.nebula.model.Zodiak
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ZodiakApi {
    @GET("zodiak.json")
    suspend fun getSuku(): List<Zodiak>
}


object ServiceAPI {
    private const val BASE_URL_ZODIAK = "https://raw.githubusercontent.com/" +
            "rifanmuhammadhidayat/Nebula/dataAPi/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofitZodiak = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL_ZODIAK)
        .build()

    //Retrofit Create


    val zodiakService: ZodiakApi by lazy {
        retrofitZodiak.create(ZodiakApi::class.java)
    }

    //Function


    fun getZodiakUrl(imageId: String): String {
        return "$BASE_URL_ZODIAK$imageId.jpg"
    }
}


enum class ApiStatus { LOADING, SUCCES,FAILED}