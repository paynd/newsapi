package name.paynd.android.newsapi.sources.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import name.paynd.android.newsapi.api.NewsService
import javax.inject.Inject

class SourcesViewModel @Inject constructor(
    private val service: NewsService
) : ViewModel() {
    val sources = flow {
        emit(service.sources().sources)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
}