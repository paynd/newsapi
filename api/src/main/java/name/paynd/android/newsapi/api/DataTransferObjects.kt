package name.paynd.android.newsapi.api

import androidx.annotation.IntRange
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Sources(
    val sources: List<Source>,
    val status: String
)

@Serializable
data class Source(
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
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    @Serializable(with = DateSerializer::class) val publishedAt: Date,
)