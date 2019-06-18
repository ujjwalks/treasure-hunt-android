package com.mindtickle.assignment.assignmentapp.dependency.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mindtickle.assignment.assignmentapp.BuildConfig
import com.mindtickle.assignment.assignmentapp.dependency.qualifiers.Auth
import com.mindtickle.assignment.assignmentapp.dependency.qualifiers.Exposed
import com.mindtickle.assignment.assignmentapp.dependency.scope.PerApplication
import com.mindtickle.assignment.assignmentapp.network.NetworkCheckInterceptor
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetModule {
    @Provides
    @PerApplication
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @PerApplication
    @Exposed
    internal fun provideExposedOkHttpClient(networkCheckInterceptor: NetworkCheckInterceptor): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }
        httpClientBuilder.addInterceptor(networkCheckInterceptor)
        return httpClientBuilder.build()
    }

    @Provides
    @PerApplication
    @Exposed
    fun provideExposedRetrofit(gson: Gson, @Exposed okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(okHttpClient)
                .build()
    }

    @Provides
    @PerApplication
    @Auth
    internal fun provideAuthOkHttpClient(networkCheckInterceptor: NetworkCheckInterceptor): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }

        httpClientBuilder.addInterceptor(networkCheckInterceptor)
        return httpClientBuilder.build()
    }

    @Provides
    @PerApplication
    @Auth
    fun provideAuthRetrofit(gson: Gson, @Auth okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(okHttpClient)
                .build()
    }
}