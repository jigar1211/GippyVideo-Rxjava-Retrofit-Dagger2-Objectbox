package dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.BaseApp;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.ExoPlayer.ExoPlayer;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.R;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.models.CityListResponse;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.networking.Service;

public class HomeActivity extends BaseApp implements HomeView {

    @Inject
    public Service service;
    @BindView(R.id.progress) ProgressBar progressBar;
    HomePresenter presenter;
    @BindView(R.id.etSearch)  EditText etSearch;
    @BindView(R.id.list) RecyclerView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();

        presenter = new HomePresenter(service, this);
        presenter.getCityList(String.valueOf("jigar"));
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                presenter.getCityList(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void renderView() {
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    public void init() {

        list.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getCityListSuccess(CityListResponse cityListResponse) {

        HomeAdapter adapter = new HomeAdapter(getApplicationContext(), cityListResponse.getData(),
                Item -> {

                    Intent _intentExoPlayer = new Intent(HomeActivity.this, ExoPlayer.class);
                    _intentExoPlayer.putExtra(getResources().getString(R.string.video_url), Item.getImages().getPreview().getMp4());
                    startActivity(_intentExoPlayer);
                });

        list.setAdapter(adapter);

    }

}
