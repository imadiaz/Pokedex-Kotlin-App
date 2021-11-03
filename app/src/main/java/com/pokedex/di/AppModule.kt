package com.pokedex.di

import com.google.gson.GsonBuilder
import com.pokedex.data.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Singleton
//    @Provides
//    fun provideRetrofitInstance(
//    ) = Retrofit.Builder()
//        .baseUrl("https://pokeapi.co/api/v2/")
//        .addConverterFactory(
//            GsonConverterFactory.create(
//                GsonBuilder().create()
//            )
//        ).build()


    @Singleton
    @Provides
    fun provideRetrofitInstance(
    ) = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addCallAdapterFactory(
            RxJava2CallAdapterFactory.create()
        )
        .addConverterFactory(
            GsonConverterFactory.create(GsonBuilder().create())
        )
        .build()

    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ) = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    @IOThread
    fun provideScheduler(): Scheduler =  Schedulers.io()

    @Provides
    @Singleton
    @MainThread
    fun provideShcedulerMainThread(): Scheduler = AndroidSchedulers.mainThread()



}

@Qualifier
annotation class MainThread
@Qualifier
annotation class IOThread

