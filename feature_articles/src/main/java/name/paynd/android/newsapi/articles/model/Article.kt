package name.paynd.android.newsapi.articles.model

import name.paynd.android.newsapi.api.ArticleDto
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*

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
        publishedAt = publishedAt?.parseDate()?.getOrNull(), //ISO 8601 yyyy-MM-dd'T'HH:mm:ss
        content = content
    )
}

fun String.parseDate() = runCatching {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    PrettyTime().format(sdf.parse(this))
}