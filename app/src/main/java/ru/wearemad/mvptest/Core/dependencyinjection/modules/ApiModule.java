package ru.wearemad.mvptest.Core.dependencyinjection.modules;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Artem on 06.07.2016.
 */
@Module
public class ApiModule {

    String baseUrl;

    public ApiModule (String url) {
        baseUrl = url;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache (Application application) {
        int cacheSize = 10*1024*1024;
        Cache cache = new Cache(application.getCacheDir(),cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson () {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient (Cache cache) {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().cache(cache);
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit (Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
