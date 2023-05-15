package com.example.mstarttask.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.util.*


object ContextUtils {
    fun updateLocale(context: Context, localeToSwitchTo: Locale): ContextWrapper {

        var mContext = context

        val resources: Resources = context.resources

        val configuration: Configuration = resources.configuration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(localeToSwitchTo)
            LocaleList.setDefault(localeList)
            configuration.setLocales(localeList)
        } else {
            configuration.locale = localeToSwitchTo
        }

        configuration.setLayoutDirection(localeToSwitchTo)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            mContext = context.createConfigurationContext(configuration)
        } else {
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }

        return ContextWrapper(mContext)
    }
}