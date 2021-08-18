package name.paynd.android.newsapi.sources_list

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import name.paynd.android.newsapi.api.NewsService
import javax.inject.Scope

@Scope
internal annotation class SourcesScope

@[SourcesScope Component(
    dependencies = [SourcesDeps::class],
    modules = [SourcesModule::class]
)]
interface SourcesComponent {
    abstract fun inject(fragment: SourcesFragment)

    @Component.Builder
    interface Builder {
        fun deps(sourcesDeps: SourcesDeps): Builder
        fun build(): SourcesComponent
    }
}

@Module
internal class SourcesModule

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
    val deps: SourcesDeps
}

val Context.sourcesDepsProvider: SourcesDepsProvider
    get() = when (this) {
        is SourcesDepsProvider -> this
        is Application -> error("App must implement SourcesDepsProvider!")
        else -> applicationContext.sourcesDepsProvider
    }


