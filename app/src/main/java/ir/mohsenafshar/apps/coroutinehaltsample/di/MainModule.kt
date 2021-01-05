package ir.mohsenafshar.apps.coroutinehaltsample.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ir.mohsenafshar.apps.coroutinehaltsample.data.RemoteRepository
import ir.mohsenafshar.apps.coroutinehaltsample.data.Repository
import javax.inject.Qualifier

@Qualifier
annotation class RemoteRepositoryQualifier
@Qualifier
annotation class LocalRepositoryQualifier

@Module
@InstallIn(ActivityComponent::class)
abstract class MainModule {

    @Binds
    @RemoteRepositoryQualifier
    abstract fun bindRemoteRepository(remoteRepository: RemoteRepository): Repository

}