package name.paynd.android.newsapi.articles.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import name.paynd.android.newsapi.api.NewsService
import name.paynd.android.newsapi.articles.model.Article
import name.paynd.android.newsapi.articles.toArticle
import retrofit2.HttpException
import javax.inject.Inject

class ArticlesPagingSource @Inject constructor(
    private val newsService: NewsService,
    private val sources: String
) : PagingSource<Int, Article>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        if (sources.isBlank()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize.coerceAtMost(NewsService.MAX_PAGE_SIZE)
            val response = newsService.everything(
                sources = sources,
                page = pageNumber,
                pageSize = pageSize
            )

            return if (response.isSuccessful) {
                val articles = response.body()?.articles?.map { it.toArticle() } ?: emptyList()
                val nextPageNumber = if (articles.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
                LoadResult.Page(articles, prevPageNumber, nextPageNumber)
            } else {
                LoadResult.Error(HttpException(response))
            }

        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    //todo: check details in doc
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("sources") sources: String): ArticlesPagingSource
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }

}