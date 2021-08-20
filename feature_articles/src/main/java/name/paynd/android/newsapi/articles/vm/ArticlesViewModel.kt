package name.paynd.android.newsapi.articles.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.*
import name.paynd.android.newsapi.articles.data.ArticlesRepository
import name.paynd.android.newsapi.articles.model.Article
import javax.inject.Inject
import javax.inject.Provider

class ArticlesViewModel(private val articlesRepo: ArticlesRepository) : ViewModel() {

    private val _category = MutableStateFlow("")
    private val category: StateFlow<String> = _category.asStateFlow()

    val articles: StateFlow<PagingData<Article>> = category.map(::newPager)
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
        _category.tryEmit(category)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val articlesRepo: Provider<ArticlesRepository>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ArticlesViewModel::class.java)
            return ArticlesViewModel(articlesRepo.get()) as T
        }
    }
}