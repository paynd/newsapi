package name.paynd.android.newsapi

import android.app.Application
import name.paynd.android.newsapi.articles.ArticlesDeps
import name.paynd.android.newsapi.articles.ArticlesDepsProvider
import name.paynd.android.newsapi.core.util.SetFragmentFactoryActivityCallback
import name.paynd.android.newsapi.di.AppComponent
import name.paynd.android.newsapi.di.DaggerAppComponent
import name.paynd.android.newsapi.sources.SourcesDeps
import name.paynd.android.newsapi.sources.SourcesDepsProvider

class NewsApiApp : Application(), SourcesDepsProvider, ArticlesDepsProvider {
    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(this)
            .apikey(BuildConfig.NEWS_API_KEY)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(
            SetFragmentFactoryActivityCallback(appComponent.fragmentFactory)
        )
    }

    override val sourcesDeps: SourcesDeps = appComponent
    override val articlesDeps: ArticlesDeps = appComponent
}