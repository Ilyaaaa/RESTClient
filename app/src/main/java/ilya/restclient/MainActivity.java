package ilya.restclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import ilya.restclient.client.data.User;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenterImpl(this);
    }

    @Override
    public void showUser(User user) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.dispose();
    }
}
