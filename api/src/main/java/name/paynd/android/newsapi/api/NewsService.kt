package name.paynd.android.newsapi.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET

interface NewsService {

    @GET("/v2/top-headlines/sources")
    fun sources(): SourcesList
}

@ExperimentalSerializationApi
fun NewsService(apiKey: String): NewsService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val authRequest = chain
                .request()
                .newBuilder()
                .addHeader("apiKey", apiKey)
                .build()

            chain.proceed(authRequest)
        }
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()

    return retrofit.create(NewsService::class.java)
}

private const val HEADER_API_KEY = "X-Api-Key"