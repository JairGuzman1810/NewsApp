package com.app.news.di

import android.app.Application
import com.app.news.data.manager.LocalUserManagerImpl
import com.app.news.domain.manager.LocalUserManager
import com.app.news.domain.usecases.AppEntryUseCases
import com.app.news.domain.usecases.ReadAppEntry
import com.app.news.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing application-level dependencies.
 *
 * This module is responsible for providing instances of classes that should
 * live as long as the application does, such as the LocalUserManager and
 * the AppEntryUseCases.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides an instance of LocalUserManager.
     *
     * This method creates and returns a LocalUserManagerImpl instance,
     * which is a singleton and will be the same instance throughout the
     * application's lifecycle.
     *
     * @param application The application instance.
     * @return A LocalUserManager instance.
     */
    @Singleton
    @Provides
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    /**
     * Provides an instance of AppEntryUseCases.
     *
     * This method creates and returns an AppEntryUseCases instance, which
     * contains the ReadAppEntry and SaveAppEntry use cases. It depends on
     * a LocalUserManager instance, which is also provided by this module.
     *
     * @param localUserManager The LocalUserManager instance.
     * @return An AppEntryUseCases instance.
     */
    @Singleton
    @Provides
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}