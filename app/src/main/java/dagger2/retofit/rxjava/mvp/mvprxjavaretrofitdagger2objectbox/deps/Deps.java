package dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.deps;


import javax.inject.Singleton;

import dagger.Component;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.ExoPlayer.ExoPlayer;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.home.HomeActivity;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.networking.NetworkModule;

/**
 * Created by ennur on 6/28/16.
 */
@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity homeActivity);
    void inject(ExoPlayer exoPlayer);
}

