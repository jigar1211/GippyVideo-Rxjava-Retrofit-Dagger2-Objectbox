package dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.deps.DaggerDeps;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.deps.Deps;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.networking.NetworkModule;

/**
 * Created by ennur on 6/28/16.
 */
public class BaseApp  extends AppCompatActivity{
    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public Deps getDeps() {
        return deps;
    }
}
