package name.paynd.android.newsapi.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import name.paynd.android.newsapi.articles.vm.ArticlesViewModel
import name.paynd.android.newsapi.core.di.factories.VMFactory
import name.paynd.android.newsapi.core.di.keys.ViewModelKey
import name.paynd.android.newsapi.sources.vm.SourcesViewModel

@Module
interface VMBindsModule {
    @Binds
    abstract fun bindViewModelFactory(factory: VMFactory): ViewModelProvider.Factory

    @[Binds IntoMap ViewModelKey(SourcesViewModel::class)]
    fun bindSourcesViewModel(sourcesViewModel: SourcesViewModel): ViewModel

    @[Binds IntoMap ViewModelKey(ArticlesViewModel::class)]
    fun bindArticlesViewModel(sourcesViewModel: ArticlesViewModel): ViewModel
}