@file:Suppress("Unused")

package name.paynd.android.newsapi.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import name.paynd.android.newsapi.articles.ArticlesFragment
import name.paynd.android.newsapi.core.di.keys.FragmentKey
import name.paynd.android.newsapi.sources.SourcesFragment

/**
 * Dagger module for specify binding between [Fragment] subclasses and [Fragment] to Multibinding map.
 */
@Module
interface FragmentBindsModule {

    @[Binds IntoMap FragmentKey(SourcesFragment::class)]
    fun bindSourcesFragmentToFragmentForMultiBinding(fragment: SourcesFragment): Fragment

    @[Binds IntoMap FragmentKey(ArticlesFragment::class)]
    fun bindArticlesFragmentToFragmentForMultiBinding(fragment: ArticlesFragment): Fragment
}