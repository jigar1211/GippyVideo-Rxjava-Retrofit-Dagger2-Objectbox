package dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox;

import android.app.Application;
import android.util.Log;

import io.objectbox.BoxStore;

/**
 * Created by T183 on 03-Apr-18.
 */

public class MyApplication  extends Application{
private static final String TAG = MyApplication.class.getSimpleName();
    private static BoxStore boxStore;
    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(MyApplication.this).build();
        Log.d(TAG,"hello");
    }

    public static BoxStore getBoxStore() {
        Log.d(TAG,"gettin data");
        return boxStore;
    }
}
