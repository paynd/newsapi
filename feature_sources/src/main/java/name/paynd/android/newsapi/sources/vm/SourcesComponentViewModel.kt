package name.paynd.android.newsapi.sources.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import name.paynd.android.newsapi.sources.DaggerSourcesComponent
import name.paynd.android.newsapi.sources.SourcesComponent
import name.paynd.android.newsapi.sources.sourcesDepsProvider

// this vm is only for holding this component
class SourcesComponentViewModel(application: Application) : AndroidViewModel(application) {
    val sourcesComponent: SourcesComponent by lazy {
        DaggerSourcesComponent.builder()
            .deps(application.sourcesDepsProvider.sourcesDeps)
            .build()
    }
}