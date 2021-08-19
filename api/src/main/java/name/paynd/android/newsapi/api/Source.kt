package name.paynd.android.newsapi.api

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
data class Articles(
    val articles: List<Article>
)

@Serializable
data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    @Serializable(with = DateSerializer::class) val publishedAt: Date,
)