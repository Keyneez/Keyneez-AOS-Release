package com.release.keyneez.di

import com.release.keyneez.data.repository.ContentRepository
import com.release.keyneez.data.repository.ContentRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun providesContentRepository(
        contentRepositoryImpl: ContentRepositoryImpl
    ): ContentRepository
}
