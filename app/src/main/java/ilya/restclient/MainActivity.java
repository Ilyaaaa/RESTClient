package ilya.restclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ilya.restclient.data.User;

public class MainActivity extends AppCompatActivity implements MainView {
    @BindView(R.id.user_id_field) EditText field;
    @BindView(R.id.btn1) Button btn1;
    @BindView(R.id.text) TextView text;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenterImpl(this);
    }

    @OnClick(R.id.btn1)
    void click() {
        presenter.getUser(Long.parseLong(field.getText().toString()));
    }

    @Override
    public void showUser(User user) {
        text.setText(user.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.dispose();
    }
}
