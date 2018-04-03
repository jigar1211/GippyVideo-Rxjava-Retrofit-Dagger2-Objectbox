package dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.networking;


import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.BuildConfig;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.models.CityListResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ennur on 6/25/16.
 */
public class Service {
    private NetworkService networkService = null;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public DisposableObserver getCityList(String query, final GetCityListCallback callback) {
        return networkService.getCityList(query, BuildConfig.APIKEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    return Observable.error(throwable);
                }).subscribeWith(new DisposableObserver<CityListResponse>() {
                    @Override
                    public void onNext(CityListResponse cityListResponse) {
                        callback.onSuccess(cityListResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    public interface GetCityListCallback{
        void onSuccess(CityListResponse cityListResponse);

        void onError(NetworkError networkError);
    }


}

