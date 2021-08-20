package name.paynd.android.newsapi.articles.data

import androidx.paging.PagingSource
import name.paynd.android.newsapi.articles.model.Article
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val articlesPagingSourceFactory: ArticlesPagingSource.Factory
) {
    fun getArticlesByCategory(category: String): PagingSource<Int, Article> {
        return articlesPagingSourceFactory.create(category)
    }
}