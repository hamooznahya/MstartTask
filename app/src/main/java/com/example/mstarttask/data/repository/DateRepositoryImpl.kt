package com.example.mstarttask.data.repository

import com.example.mstarttask.data.datasource.OfflineDataSource
import com.example.mstarttask.data.datasource.RemoteDataSource
import com.example.mstarttask.data.dto.DateResponse
import com.example.mstarttask.domain.mapper.DateMapper
import com.example.mstarttask.domain.model.DateModel
import com.example.mstarttask.domain.repository.DateRepository
import com.example.mstarttask.utils.ResponseState
import com.example.mstarttask.utils.mapToError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.concurrent.thread

class DateRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val offlineDataSource: OfflineDataSource,
    private val dateMapper: DateMapper
) : DateRepository {

    override fun getAllEvent(): Flow<List<DateModel>> {

        return offlineDataSource.getItems()
            .map {
                it.map {
                    dateMapper.mapDateModelToEntity(it)
                }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun saveEvent(item: DateModel) {
//        thread {
            offlineDataSource.addItem(dateMapper.mapEntityToDateModel(item))
        //}
    }

    override suspend fun deleteEvent(date: DateModel) {
            offlineDataSource.deleteItem(dateMapper.mapEntityToDateModel(date))

    }

    override suspend fun deleteById(id: List<Int>) {
            offlineDataSource.deleteById(id)
    }

    override suspend fun updateEvent(date: DateModel) {
            offlineDataSource.updateItem(
                dateMapper.mapEntityToDateModel(date)
            )
    }

    override fun convertDate(date: String): Flow<ResponseState<DateResponse>> {
        return flow {
            emit(ResponseState.Loading)
            remoteDataSource.convertDate(date).onSuccess {
                emit(ResponseState.Success(it.data!!))
            }.onFailure {
                emit(ResponseState.Failure(it.mapToError()))
            }
        }.flowOn(Dispatchers.IO)
    }
}