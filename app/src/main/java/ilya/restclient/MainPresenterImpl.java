package ilya.restclient;

import android.util.Log;

import ilya.restclient.client.Api;
import ilya.restclient.client.Client;
import ilya.restclient.client.data.Meta;
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
                        .subscribe(user -> {
                            if (user.getMeta().isSuccess())
                                view.showUser(user.getResult());
                            else showError(user.getMeta());
                            }, Throwable::printStackTrace
                        )
        );
    }

    @Override
    public void getUsers() {
        compositeDisposable.add(
                api.getUsers()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(users -> {
                            if (users.getMeta().isSuccess())
                                view.updateUserList(users.getResult());
                            else showError(users.getMeta());
                            }, Throwable::printStackTrace
                        )
        );
    }

    private void showError(Meta meta) {
        if (meta.isSuccess()) return;

        view.showError(meta.getCode(), meta.getMessage());
    }

    @Override
    public void dispose() {
        compositeDisposable.dispose();
    }
}
