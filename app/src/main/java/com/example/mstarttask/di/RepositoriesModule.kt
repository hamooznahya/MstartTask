package com.example.mstarttask.di

import com.example.mstarttask.data.datasource.OfflineDataSource
import com.example.mstarttask.data.datasource.RemoteDataSource
import com.example.mstarttask.data.repository.CommonRepositoryImpl
import com.example.mstarttask.data.repository.DateRepositoriesImpl
import com.example.mstarttask.domain.mapper.DateMapper
import com.example.mstarttask.domain.repository.CommonRepository
import com.example.mstarttask.domain.repository.DateRepositories
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RepositoriesModule {


    @Provides
    @Singleton
    fun DateRepositories(
        remoteDataSource: RemoteDataSource,
     offlineDataSource: OfflineDataSource
    ): DateRepositories {
        return DateRepositoriesImpl(remoteDataSource,offlineDataSource,DateMapper())
    }

    @Provides
    @Singleton
    fun CommonRepository(
     offlineDataSource: OfflineDataSource
    ): CommonRepository {
        return CommonRepositoryImpl(offlineDataSource)
    }


}