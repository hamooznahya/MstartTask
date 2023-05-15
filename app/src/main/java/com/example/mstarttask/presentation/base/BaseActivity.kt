package com.example.mstarttask.presentation.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mstarttask.domain.datasource.OfflineDataSourceImpl
import com.example.mstarttask.utils.ContextUtils
import com.example.mstarttask.utils.appPreferencesStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.util.*

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {
    companion object {
        const val LOCATION_TAG = "LOCATION_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        window.setBackgroundDrawable(null)
    }




    override fun attachBaseContext(base: Context?) {
        base?.let {
            val language = getLanguage(base)
            val updateLocale = ContextUtils.updateLocale(base, Locale(language))
            super.attachBaseContext(updateLocale)

        }
    }


    private fun getLanguage(context: Context): String? {
        return runBlocking {
            context.appPreferencesStore.data.map {
                it[OfflineDataSourceImpl.Companion.SETTINGS_LANGUAGE] ?: "En"
            }.first()
        }
    }


}