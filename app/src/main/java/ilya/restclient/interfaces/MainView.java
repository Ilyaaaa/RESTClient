package ilya.restclient.interfaces;

import java.util.ArrayList;

import ilya.restclient.client.data.User;

public interface MainView extends DialogVIew {
    void updateUserList(ArrayList<User> users);
    void removeUser(long id);
    void showError(int code, String msg);
}
