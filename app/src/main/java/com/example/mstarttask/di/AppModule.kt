package com.example.mstarttask.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.mstarttask.data.APIEndpoints
import com.example.mstarttask.data.datasource.RemoteDataSource
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.mstarttask.constants.Constants
import com.example.mstarttask.data.database.AppDatabase
import com.example.mstarttask.data.database.DateDao
import com.example.mstarttask.data.datasource.OfflineDataSource
import com.example.mstarttask.domain.datasource.OfflineDataSourceImpl
import com.example.mstarttask.domain.datasource.RemoteDataSourceImpl
import com.example.mstarttask.utils.appPreferencesStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    companion object {
        const val TIMEOUT_SECONDS = 30 * 1000L
    }

    @Provides
    @Singleton
    fun OfflineDataSource(
        @ApplicationContext appContext: Context,
        DateDao: DateDao
    ): OfflineDataSource {
        return OfflineDataSourceImpl(
           DateDao,
            appContext.appPreferencesStore

        )
    }

    @Provides
    fun RemoteDataSource(apiEndpoints: APIEndpoints): RemoteDataSource =
        RemoteDataSourceImpl(apiEndpoints)


    @Provides
    @Singleton
    fun getRetrofit(): APIEndpoints {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor(interceptor)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .baseUrl(Constants.BASE_URL)
            .client(builder.build())
            .build()
        return retrofit.create(APIEndpoints::class.java)
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        gsonBuilder.setLenient()
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideItemDetailsDao(db: AppDatabase) = db.itemDetailsDao()


}