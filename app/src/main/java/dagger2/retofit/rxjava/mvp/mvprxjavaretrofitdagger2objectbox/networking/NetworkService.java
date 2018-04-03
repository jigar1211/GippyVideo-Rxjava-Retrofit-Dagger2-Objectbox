package dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.networking;


import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.models.CityListResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ennur on 6/25/16.
 */
public interface NetworkService {

    @GET("/v1/gifs/search")
    Observable<CityListResponse> getCityList(@Query("q") String query, @Query("api_key") String apiKey);

}
