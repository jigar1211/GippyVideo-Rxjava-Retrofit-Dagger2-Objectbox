package dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.home;


import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.models.CityListResponse;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.networking.NetworkError;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.networking.Service;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by ennur on 6/25/16.
 */
public class HomePresenter {
    private final Service service;
    private final HomeView view;
    private CompositeDisposable subscriptions;
    public HomePresenter(Service service, HomeView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeDisposable();
    }

    public void getCityList(String query) {
        view.showWait();

        DisposableObserver subscription = service.getCityList(query,new Service.GetCityListCallback() {
            @Override
            public void onSuccess(CityListResponse cityListResponse) {
                view.removeWait();
                view.getCityListSuccess(cityListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.dispose();
    }


}
