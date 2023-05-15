package com.example.mstarttask.domain.datasource


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.mstarttask.data.database.DateDao
import com.example.mstarttask.data.datasource.OfflineDataSource
import com.example.mstarttask.data.entity.DateEntity
import com.example.mstarttask.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.concurrent.thread


class OfflineDataSourceImpl(
    private val dateDao: DateDao,
    private val appPreferencesStore: DataStore<Preferences>
) : OfflineDataSource {

    companion object {
        val SETTINGS_LANGUAGE = stringPreferencesKey("language")
    }

    override fun getItems(): Flow<List<DateEntity>> {
        return dateDao.getAll()
    }

    override fun addItem(item: DateEntity) {
        dateDao.insertAll(item)
    }

    override fun deleteItem(item: DateEntity) {
        dateDao.delete(item)
    }

    override fun deleteById(id: List<Int>) {
        dateDao.deleteById(id)
    }

    override fun updateItem(item: DateEntity) {
        dateDao.update(item)
    }


    override suspend fun saveAppLanguage(appSettings: AppSettings) {
        appPreferencesStore.edit { preferences ->
            preferences[SETTINGS_LANGUAGE] = appSettings.language
        }
    }

    override fun getAppLanguage(): Flow<AppSettings> {
        return appPreferencesStore.data.map { preferences ->
            AppSettings(
                language = preferences[SETTINGS_LANGUAGE] ?: "En",
            )
        }
    }
}