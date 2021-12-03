package com.secondslot.finaltask.di

import android.content.Context
import androidx.room.Room
import com.secondslot.finaltask.data.api.AuthorizationInterceptor
import com.secondslot.finaltask.data.api.ZulipApiService
import com.secondslot.finaltask.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideZulipApiService(authorizationClient: OkHttpClient): ZulipApiService {
        return Retrofit.Builder()
            .baseUrl(ZulipApiService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(authorizationClient)
            .build()
            .create(ZulipApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthorizationClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(AuthorizationInterceptor())
        .build()
}
