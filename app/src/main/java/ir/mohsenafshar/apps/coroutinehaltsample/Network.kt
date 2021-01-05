package ir.mohsenafshar.apps.coroutinehaltsample

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

class Network @Inject constructor(){

    companion object {
        const val BASE_URL = "http://bdc151b2e38d.ngrok.io"
    }

    private fun getRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private inline fun <reified T> provideApi(retrofit: Retrofit): T {
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