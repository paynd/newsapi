package name.paynd.android.newsapi.articles

import name.paynd.android.newsapi.api.ArticleDto
import name.paynd.android.newsapi.articles.model.Article

internal fun ArticleDto.toArticle(): Article {
    return Article(
        title = title,
        url = url,
        description = description,
        author = author,
        urlToImage = urlToImage
//        publishedAt = publishedAt,
//        content = content
    )
}