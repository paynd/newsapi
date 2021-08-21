package name.paynd.android.newsapi.di

import android.app.Application
import androidx.fragment.app.FragmentFactory
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import name.paynd.android.newsapi.api.NewsService
import name.paynd.android.newsapi.articles.ArticlesDeps
import name.paynd.android.newsapi.core.di.modules.FragmentProviderModule
import name.paynd.android.newsapi.core.di.scopes.AppScope
import name.paynd.android.newsapi.sources.SourcesDeps
import javax.inject.Qualifier

@[AppScope Component(
    modules = [
        AppModule::class,
        FragmentProviderModule::class,
        FragmentBindsModule::class,
        VMBindsModule::class
    ]
)]
interface AppComponent : SourcesDeps, ArticlesDeps {

    override val newsService: NewsService
    val fragmentFactory: FragmentFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apikey(@NewsApiQualifier apikey: String): Builder

        fun build(): AppComponent
    }
}

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideNewsService(@NewsApiQualifier apikey: String): NewsService {
        return NewsService(apikey)
    }
}

@Qualifier
annotation class NewsApiQualifier