package name.paynd.android.newsapi.api

import androidx.annotation.IntRange
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourcesDto(
    val sources: List<SourceDto>,
    val status: String
)

@Serializable
data class SourceDto(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String,
)

@Serializable
data class ArticlesResponseDto(
    @SerialName("status") val status: String,
    @SerialName("totalResults") @IntRange(from = 1) val totalResults: Int,
    @SerialName("message") val message: String? = null,
    @SerialName("articles") val articles: List<ArticleDto>,
)

@Serializable
data class ArticleDto(
    @SerialName("title") val title: String = "",
    @SerialName("url") val url: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("urlToImage") val urlToImage: String? = null,
    @SerialName("publishedAt") val publishedAt: String? = null,
    @SerialName("content") val content: String? = null,
)