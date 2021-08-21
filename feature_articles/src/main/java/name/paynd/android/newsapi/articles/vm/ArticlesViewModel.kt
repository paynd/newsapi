package name.paynd.android.newsapi.articles.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.*
import name.paynd.android.newsapi.articles.data.ArticlesRepository
import name.paynd.android.newsapi.articles.model.Article
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
    private val articlesRepo: ArticlesRepository
) : ViewModel() {

    private val _source = MutableStateFlow("")

    val articles: StateFlow<PagingData<Article>> = _source.map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(category: String): Pager<Int, Article> {
        return Pager(providePagingConfig()) {
            articlesRepo.getArticlesByCategory(category)
        }
    }

    private fun providePagingConfig() = PagingConfig(
        pageSize = 5,
        enablePlaceholders = false
    )

    fun setCategory(category: String) {
        _source.tryEmit(category)
    }
}