package name.paynd.android.newsapi.sources_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import name.paynd.android.newsapi.api.NewsService
import name.paynd.android.newsapi.api.Source
import javax.inject.Inject

class SourcesViewModel() : ViewModel() {
    @Inject
    lateinit var service: NewsService

    val sources = flow<List<Source>> {
        val list = service.sources().sourcesList
        Log.d("####", "$list")
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
}