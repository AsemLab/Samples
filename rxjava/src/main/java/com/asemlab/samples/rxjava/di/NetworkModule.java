package com.asemlab.samples.rxjava.di;

import com.asemlab.samples.rxjava.remote.CountryService;
import com.asemlab.samples.rxjava.remote.HttpRoutes;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@InstallIn(SingletonComponent.class)
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideInterceptor() {
        return new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                .setLevel(HttpLoggingInterceptor.Level.HEADERS);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .build();

        return client;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okhttp) {
        return new Retrofit.Builder().baseUrl(HttpRoutes.BASE_URL)
                // TODO Adding a call adapter which for creating observables
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttp)
                .build();
    }

    @Provides
    @Singleton
    public CountryService provideCountryService(Retrofit retrofit) {
        return retrofit.create(CountryService.class);
    }

}
