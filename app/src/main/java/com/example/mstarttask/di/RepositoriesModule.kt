package com.example.mstarttask.di

import com.example.mstarttask.data.datasource.OfflineDataSource
import com.example.mstarttask.data.datasource.RemoteDataSource
import com.example.mstarttask.data.repository.CommonRepositoryImpl
import com.example.mstarttask.data.repository.DateRepositoryImpl
import com.example.mstarttask.domain.mapper.DateMapper
import com.example.mstarttask.domain.repository.CommonRepository
import com.example.mstarttask.domain.repository.DateRepository
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
    ): DateRepository {
        return DateRepositoryImpl(remoteDataSource,offlineDataSource,DateMapper())
    }

    @Provides
    @Singleton
    fun CommonRepository(
     offlineDataSource: OfflineDataSource
    ): CommonRepository {
        return CommonRepositoryImpl(offlineDataSource)
    }


}