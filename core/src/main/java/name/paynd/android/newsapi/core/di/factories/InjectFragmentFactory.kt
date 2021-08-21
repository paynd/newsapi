package name.paynd.android.newsapi.core.di.factories

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Provider

/**
 * Implementation of [FragmentFactory] that used [Dagger Multibindings](https://dagger.dev/multibindings.html)
 * into Map to get Fragment creators.
 * See more [details](https://proandroiddev.com/inject-into-android-component-constructor-4f5ddd27d06)
 */
class InjectFragmentFactory(
    private val providers: Map<String, Provider<Fragment>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        // Try to find Provider for a Fragment with specified class name
        val provider = providers[className]
        // If provider was not found use super function to create fragment
        // (using Reflection and default constructor)
        if (provider == null) {
            Log.w(TAG, "Can't find Provider for `$className`. Fragment will be created using Reflection API.")
            return super.instantiate(classLoader, className)
        }
        // Create new Fragment instance
        Log.d(TAG, "Create `$className` using Dagger")
        return provider.get()
    }

    companion object {

        private const val TAG = "InjectFragmentFactory"

        @JvmName("newInstance")
        @JvmStatic
        operator fun invoke(providers: Map<Class<out Fragment>, Provider<Fragment>>) : InjectFragmentFactory {
            return InjectFragmentFactory(providers.mapKeys { (fragmentClass, _) -> fragmentClass.name })
        }
    }
}
