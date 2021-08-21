package name.paynd.android.newsapi.sources

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import name.paynd.android.newsapi.api.NewsService
import javax.inject.Scope

@Scope
internal annotation class SourcesScope

@[SourcesScope Component(
    dependencies = [SourcesDeps::class],
    modules = [SourcesModule::class]
)]
interface SourcesComponent {
    fun inject(fragment: SourcesFragment)

    @Component.Builder
    interface Builder {
        fun deps(sourcesDeps: SourcesDeps): Builder
        fun build(): SourcesComponent
    }
}

@Module
internal abstract class SourcesModule

/**
 * SourcesComponent dependencies holder interface
 */
interface SourcesDeps {
    val newsService: NewsService
}

/**
 * Bridge to fill SourcesDeps from another component
 */
interface SourcesDepsProvider {
    val sourcesDeps: SourcesDeps
}

val Context.sourcesDepsProvider: SourcesDepsProvider
    get() = when (this) {
        is SourcesDepsProvider -> this
        is Application -> error("App must implement SourcesDepsProvider!")
        else -> applicationContext.sourcesDepsProvider
    }


