package ilya.restclient;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ilya.restclient.client.adapter.UsersAdapter;
import ilya.restclient.client.data.NewUser;
import ilya.restclient.client.data.User;
import ilya.restclient.interfaces.MainPresenter;
import ilya.restclient.interfaces.MainView;
import ilya.restclient.interfaces.UserEditDialogCreator;

public class MainActivity extends AppCompatActivity implements MainView, AdapterView.OnItemClickListener {
    private MainPresenter presenter;
    private UserEditDialogCreator dialogCreator;

    @BindView(R.id.main_root_lay) ConstraintLayout rootLayout;
    @BindView(R.id.users_list) ListView usersList;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenterImpl(this);
        dialogCreator = new UserEditDialogCreatorImpl(this, presenter);

        adapter = new UsersAdapter(this, new ArrayList<>(), R.layout.users_list_item);
        usersList.setAdapter(adapter);
        usersList.setOnItemClickListener(this);
        presenter.getUsers();

        registerForContextMenu(usersList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() != R.id.users_list) return;

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(adapter.getItem(info.position).getName());
        String[] items = getResources().getStringArray(R.array.user_list_menu);
        for (int i = 0; i < items.length; i++) menu.add(Menu.NONE, i, i, items[i]);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String[] items = getResources().getStringArray(R.array.user_list_menu);

        switch (items[item.getItemId()]) {
            case "Remove": {
                presenter.deleteUser(adapter.getItem(info.position).getId());
                break;
            }
        }

        return true;
    }

    //NewUser - обьект для добавления и обновления
    private NewUser userToNewUser(User user) {
        String[] names = user.getName().split(" ");
        NewUser newUser = new NewUser(names[0], names[1], user.getGender(), user.getLinks().getAvatar().getHref());
        newUser.setAddress(user.getAddress());
        newUser.setDob(user.getDob());
        newUser.setEmail(user.getEmail());
        newUser.setAddress(user.getAddress());
        newUser.setPhone(user.getPhone());
        newUser.setWebsite(user.getWebsite());
        return newUser;
    }

    @OnClick(R.id.user_add_btn)
    public void createUser() {
        dialogCreator.showDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            dialogCreator.showDialog(id, userToNewUser(adapter.getItem(position)));
        }catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void showUser(User user) {
        adapter.add(user);
    }

    @Override
    public void updateUser(User user) {
        adapter.remove(adapter.getItemById(user.getId()));
        adapter.add(user);
    }

    @Override
    public void updateUserList(ArrayList<User> users) {
        adapter.addAll(users);
    }

    @Override
    public void removeUser(long id) {
        adapter.remove(adapter.getItemById(id));
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
