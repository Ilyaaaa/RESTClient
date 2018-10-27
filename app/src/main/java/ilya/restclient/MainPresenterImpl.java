package ilya.restclient;

import ilya.restclient.client.Api;
import ilya.restclient.client.Client;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class MainPresenterImpl implements MainPresenter {
    private CompositeDisposable compositeDisposable;
    private MainView view;
    private Api api;

    public MainPresenterImpl(MainView view) {
        this.view = view;

        api = Client.getInstance().getApi();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getUser(long id) {
        compositeDisposable.add(
                api.getUser(id)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(user -> view.showUser(user), Throwable::printStackTrace
                        )
        );
    }

    @Override
    public void dispose() {
        compositeDisposable.dispose();
    }
}
