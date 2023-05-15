package com.example.mstarttask.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore


private const val DATA_STORE_APP_SETTINGS = "app_language"


val Context.appPreferencesStore: DataStore<Preferences> by preferencesDataStore(
    name = DATA_STORE_APP_SETTINGS
)
