package ir.mohsenafshar.apps.coroutinehaltsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass


@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
interface MainModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(myViewModelFactory: MyViewModelFactory): ViewModelProvider.Factory
}

@Component(modules = [MainModule::class])
interface PresenterFactory {
    fun inject(activity: MainActivity)
}

class MyViewModelFactory @Inject constructor(private val providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var viewModelProvider: Provider<ViewModel>? = null
        for ((klass, provider) in providers) {
            if (modelClass.isAssignableFrom(klass)) {
                viewModelProvider = provider
                break
            }
        }

        if (viewModelProvider == null) throw Exception("cannot be null")

        try {
            @Suppress("UNCHECKED_CAST")
            return viewModelProvider.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}