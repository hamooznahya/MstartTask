package com.example.mstarttask.data.repository

import com.example.mstarttask.data.datasource.OfflineDataSource
import com.example.mstarttask.data.datasource.RemoteDataSource
import com.example.mstarttask.data.dto.DateResponse
import com.example.mstarttask.domain.mapper.DateMapper
import com.example.mstarttask.domain.model.DateModel
import com.example.mstarttask.domain.repository.DateRepositories
import com.example.mstarttask.utils.ResponseState
import com.example.mstarttask.utils.mapToError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.concurrent.thread

class DateRepositoriesImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val offlineDataSource: OfflineDataSource,
    private val dateMapper: DateMapper
) : DateRepositories {

    override fun getAllEvent(): Flow<List<DateModel>> {
        return offlineDataSource.getItems()
            .map {
                it.map {
                    var dateModel = DateModel(
                        it.id,
                        it.eventName,
                        it.eventDescription,
                        it.GregorianDate,
                        it.HijriDate,
                        it.serverDatetime
                    )
                    dateModel.id = it.id
                    dateModel
                }
            }
            .flowOn(Dispatchers.IO)
    }

    override fun saveEvent(item: DateModel) {
        thread {
            offlineDataSource.addItem(dateMapper.mapEntityToDateModel(item))
        }
    }

    override fun deleteEvent(date: DateModel) {
        thread {
            offlineDataSource.deleteItem(dateMapper.mapEntityToDateModel(date))
        }
    }

    override fun deleteById(id: List<Int>) {
        thread {
            offlineDataSource.deleteById(id)
        }
    }

    override fun updateEvent(date: DateModel) {
        thread {
            offlineDataSource.updateItem(
                dateMapper.mapEntityToDateModel(date)
            )
        }
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