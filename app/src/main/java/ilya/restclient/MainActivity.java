package ilya.restclient;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ilya.restclient.client.adapter.UsersAdapter;
import ilya.restclient.client.data.User;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter presenter;

    @BindView(R.id.main_root_lay) ConstraintLayout rootLayout;
    @BindView(R.id.users_list) ListView usersList;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenterImpl(this);

        adapter = new UsersAdapter(this, new ArrayList<>(), R.layout.users_list_item);
        usersList.setAdapter(adapter);
        presenter.getUsers();
    }

    @Override
    public void showUser(User user) {
    }

    @Override
    public void updateUserList(ArrayList<User> users) {
        adapter.addAll(users);
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void showError(int code, String msg) {
        Snackbar.make(rootLayout, code + ": " + msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.dispose();
    }
}
