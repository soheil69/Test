package com.example.template.di.modules

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.network.CacheInterceptor
import com.example.template.TemplateViewModelFactory
import com.example.template.activities.main.MainActivity
import com.example.template.activities.main.MainViewModel
import com.example.template.di.ActivityScope
import com.example.template.di.CacheBase
import com.example.template.di.ViewModelKey
import com.example.template.fragments.detail.DetailViewModel
import com.example.template.fragments.home.HomeViewModel
import com.example.template.network.TemplateApiInfo
import com.example.template.network.TemplateRestApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
internal class TemplateModule


@Module
internal class NetworkModule {

    @Provides
    @Singleton
    fun okHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @CacheBase
    fun cacheBaseOkHttpClient(context: Context, okHttpClient: OkHttpClient): OkHttpClient {
        return okHttpClient.newBuilder()
            .cache(Cache(context.cacheDir, 10L * 1024L * 1024L))
            .addInterceptor(CacheInterceptor(context))
            .build()
    }

    @Provides
    fun gson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun retrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(TemplateApiInfo.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @CacheBase
    fun cacheBaseRetrofit(
        @CacheBase okHttpClient: OkHttpClient,
        retrofit: Retrofit
    ): Retrofit {
        return retrofit.newBuilder()
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun templateRestApi(retrofit: Retrofit): TemplateRestApi {
        return retrofit.create(TemplateRestApi::class.java)
    }

    @Provides
    @CacheBase
    fun cacheBaseTemplateRestApi(@CacheBase retrofit: Retrofit): TemplateRestApi {
        return retrofit.create(TemplateRestApi::class.java)
    }
}

@Module
internal interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun detailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    fun templateViewModelFactory(viewModelFactory: TemplateViewModelFactory):
        ViewModelProvider.Factory
}

@Module
internal interface ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainFragmentBuilder::class])
    @ActivityScope
    fun mainActivity(): MainActivity
}

@Module
internal interface ServiceBuilder
