package ru.wearemad.mvptest.Core.dependencyinjection.components;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import ru.wearemad.mvptest.Core.dependencyinjection.modules.ApiModule;
import ru.wearemad.mvptest.Core.dependencyinjection.modules.AppModule;
import ru.wearemad.mvptest.MainActivity;

/**
 * Created by Artem on 06.07.2016.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {

    Retrofit retrofit();
    OkHttpClient okHttpClient();
}
