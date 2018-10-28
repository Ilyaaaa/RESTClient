package ilya.restclient;

import java.util.ArrayList;

import ilya.restclient.client.data.User;

public interface MainView {
    void showUser(User user);
    void updateUserList(ArrayList<User> users);
    void removeUser(User user);
    void showError(int code, String msg);
}
