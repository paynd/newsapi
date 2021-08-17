package name.paynd.android.newsapi.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import name.paynd.android.newsapi.api.NewsService
import javax.inject.Qualifier
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apikey(apikey: String): Builder
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

@Scope
annotation class AppScope
