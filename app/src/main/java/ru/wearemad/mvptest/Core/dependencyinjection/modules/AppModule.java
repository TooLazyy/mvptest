package ru.wearemad.mvptest.Core.dependencyinjection.modules;

import android.app.Application;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 06.07.2016.
 */
@Module
public class AppModule {

    Application application;

    public AppModule (Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication () {
        return this.application;
    }
}

