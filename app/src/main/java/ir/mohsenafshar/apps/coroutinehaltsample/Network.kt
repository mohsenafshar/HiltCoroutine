package ir.mohsenafshar.apps.coroutinehaltsample

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object Network {

    const val BASE_URL = "http://0f3a368e305b.ngrok.io"

    private fun getRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    inline fun <reified T> provideApi(retrofit: Retrofit): T {
        return retrofit.create(T::class.java)
    }

    fun getApi(): Api {
        return provideApi(getRetrofit(Gson()))
    }
}

interface Api {

    @GET("/")
    suspend fun getList(): List<User>

}