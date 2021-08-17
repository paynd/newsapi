package name.paynd.android.newsapi

import android.app.Application
import name.paynd.android.newsapi.di.AppComponent
import name.paynd.android.newsapi.di.DaggerAppComponent

class NewsApiApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(this)
            .apikey(BuildConfig.NEWS_API_KEY)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

    }
}