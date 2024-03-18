package edu.oregonstate.cs492.githubsearchwithsettings.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ExerciseService {
    @GET("v1/exercises")
    suspend fun searchExercises(
        @Query("name") name: String
    ) : Response<List<ExerciseSearchResults>>

    companion object {
        private const val BASE_URL = "https://api.api-ninjas.com/"
        private const val API_KEY = "rschPA7svf/iNip2dwbsFg==lFznmofg6i62Swdr"

        private val client = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("X-Api-Key", API_KEY)
                    .build()
                chain.proceed(request)
            })
            .build()

        fun create(): ExerciseService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ExerciseService::class.java)
        }
    }
}

// api key: rschPA7svf/iNip2dwbsFg==lFznmofg6i62Swdr