package dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.home;

import android.support.v7.widget.SearchView;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


public class RxSearchObservable {
    public static Observable<String> fromView(SearchView searchView) {

        final PublishSubject<String> subject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                subject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                subject.onNext(text);
                return true;
            }
        });
        return subject;
    }
}
