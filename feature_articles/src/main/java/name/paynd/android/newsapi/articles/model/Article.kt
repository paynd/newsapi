package name.paynd.android.newsapi.articles.model

import name.paynd.android.newsapi.api.ArticleDto

data class Article(
    val title: String,
    val url: String?,
    val description: String?,
    val author: String?,
    val urlToImage: String?,
    val publishedAt: String? = null,
    val content: String? = null,
)

internal fun ArticleDto.toArticle(): Article {
    return Article(
        title = title,
        url = url,
        description = description,
        author = author,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}