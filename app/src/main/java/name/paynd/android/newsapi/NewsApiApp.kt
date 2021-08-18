package name.paynd.android.newsapi

import android.app.Application
import name.paynd.android.newsapi.di.AppComponent
import name.paynd.android.newsapi.di.DaggerAppComponent
import name.paynd.android.newsapi.sources_list.SourcesDeps
import name.paynd.android.newsapi.sources_list.SourcesDepsProvider

class NewsApiApp : Application() , SourcesDepsProvider {
    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(this)
            .apikey(BuildConfig.NEWS_API_KEY)
            .build()
    }

    override val deps: SourcesDeps = appComponent
}