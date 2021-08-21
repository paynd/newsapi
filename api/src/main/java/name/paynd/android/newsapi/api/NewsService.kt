package name.paynd.android.newsapi.api

import androidx.annotation.IntRange
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/v2/top-headlines/sources")
    suspend fun sources(): SourcesDto

    /**
     * @param pageSize The number of results to return per page. Default: 100. Maximum: 100.
     * @param page Use this to page through the results. Default: 1
     * @param sources A comma-seperated string of identifiers (maximum 20) for the news sources or blogs you want headlines from.
     *
     * [Rest API Documentation](https://newsapi.org/docs/endpoints/everything)
     */
    @GET("/v2/everything")
    suspend fun everything(
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("pageSize") @IntRange(
            from = 1,
            to = MAX_PAGE_SIZE.toLong()
        ) pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("sources") sources: String? = null
    ): Response<ArticlesResponseDto>

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
        const val MAX_PAGE_SIZE = 20
    }
}

fun NewsService(apiKey: String): NewsService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val authRequest = chain
                .request()
                .newBuilder()
                .addHeader(HEADER_API_KEY, apiKey)
                .build()

            chain.proceed(authRequest)
        }
        .build()

    val json = Json(Json) {
        ignoreUnknownKeys = true
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .build()

    return retrofit.create(NewsService::class.java)
}

private const val HEADER_API_KEY = "X-Api-Key"