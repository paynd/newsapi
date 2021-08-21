package name.paynd.android.newsapi.core.di.keys

import dagger.MapKey
import kotlin.reflect.KClass
import androidx.lifecycle.ViewModel

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
