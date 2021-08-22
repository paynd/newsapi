package name.paynd.android.newsapi.articles

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import name.paynd.android.newsapi.api.NewsService
import name.paynd.android.newsapi.core.di.factories.VMFactory
import javax.inject.Scope


@Scope
internal annotation class ArticlesScope

@[ArticlesScope Component(
    dependencies = [ArticlesDeps::class],
    modules = [ArticlesModule::class]
)]
interface ArticlesComponent {
    fun inject(articlesFragment: ArticlesFragment)

    @Component.Builder
    interface Builder {
        fun deps(articlesDeps: ArticlesDeps): Builder
        fun build(): ArticlesComponent
    }
}

@Module
internal class ArticlesModule

/**
 * ArticlesComponent dependencies holder interface
 */
interface ArticlesDeps {
    val newsService: NewsService
    val vmFactory: VMFactory
}

/**
 * Bridge to fill ArticlesDeps from another component
 */
interface ArticlesDepsProvider {
    val articlesDeps: ArticlesDeps
}

val Context.articlesDepsProvider: ArticlesDepsProvider
    get() = when (this) {
        is ArticlesDepsProvider -> this
        is Application -> error("App must implement ArticlesDepsProvider!")
        else -> applicationContext.articlesDepsProvider
    }

