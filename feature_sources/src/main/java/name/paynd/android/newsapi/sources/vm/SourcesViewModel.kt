package name.paynd.android.newsapi.sources.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import name.paynd.android.newsapi.api.NewsService
import javax.inject.Inject
import javax.inject.Provider

class SourcesViewModel(private val service: NewsService) : ViewModel() {
    val sources = flow {
        emit(service.sources().sources)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    class Factory @Inject constructor(
        private val newsService: Provider<NewsService>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == SourcesViewModel::class.java)
            return SourcesViewModel(newsService.get()) as T
        }
    }
}