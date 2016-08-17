package ru.wearemad.mvptest.Core.dependencyinjection.modules;

import android.app.Application;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 09.07.2016.
 */
@Module
public class PicassoModule {

    @Provides
    @Singleton
    Picasso providePicasso (Application application) {
        return Picasso.with(application);
    }
}
