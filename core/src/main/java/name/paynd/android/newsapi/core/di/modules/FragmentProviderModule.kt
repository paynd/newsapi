package name.paynd.android.newsapi.core.di.modules

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.Module
import dagger.Provides
import name.paynd.android.newsapi.core.di.factories.InjectFragmentFactory
import javax.inject.Provider

@Module
object FragmentProviderModule {
    @Provides
    fun provideFragmentFactory(
        fragmentProviders: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
    ): FragmentFactory {
        return InjectFragmentFactory(fragmentProviders)
    }
}