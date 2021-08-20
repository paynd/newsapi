package name.paynd.android.newsapi.articles.model

data class Article(
    val title: String,
    val url: String?,
    val description: String?,
    val author: String?,
    val urlToImage: String?,
    val publishedAt: String? = null,
    val content: String? = null,
)