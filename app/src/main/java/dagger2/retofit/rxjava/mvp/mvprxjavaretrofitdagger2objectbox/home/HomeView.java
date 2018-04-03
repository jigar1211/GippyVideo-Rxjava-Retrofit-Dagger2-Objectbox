package dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.home;


import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.models.CityListResponse;

/**
 * Created by ennur on 6/25/16.
 */
public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCityListSuccess(CityListResponse cityListResponse);

}
