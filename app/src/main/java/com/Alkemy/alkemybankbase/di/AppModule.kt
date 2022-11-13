package com.Alkemy.alkemybankbase.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.Alkemy.alkemybankbase.data.datasource.ExpenseRemoteDataSource
import com.Alkemy.alkemybankbase.data.datasource.ExpenseServerDataSource
import com.Alkemy.alkemybankbase.data.server.RemoteService
import com.Alkemy.alkemybankbase.data.datasource.UserRemoteDatasource
import com.Alkemy.alkemybankbase.data.datasource.UserServerDataSource
import com.Alkemy.alkemybankbase.utils.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl():String = "http://wallet-main.eba-ccwdurgr.us-east-1.elasticbeanstalk.com"

    @Singleton
    @Provides
    fun provideRetrofit(@ApiUrl apiUrl: String): RemoteService {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context : Context) : SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            Constants.PREFERENCES_TOKEN,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule{

    @Binds
    abstract fun bindUserRemoteDataSource(userServerDataSource: UserServerDataSource) : UserRemoteDatasource

    @Binds
    abstract fun bindExpenseRemoteDataSource(expenseServerDataSource: ExpenseServerDataSource) : ExpenseRemoteDataSource

}