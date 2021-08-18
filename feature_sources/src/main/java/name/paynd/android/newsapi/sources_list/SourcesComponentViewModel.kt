package name.paynd.android.newsapi.sources_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

// this vm is only for holding this component
class SourcesComponentViewModel(application: Application): AndroidViewModel(application) {
    val sourcesComponent: SourcesComponent by lazy {
        DaggerSourcesComponent
            .builder()
            .deps(application.sourcesDepsProvider.deps)
            .build()
    }
}