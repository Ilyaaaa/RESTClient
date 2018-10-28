package ilya.restclient.interfaces;

import ilya.restclient.client.data.NewUser;
import ilya.restclient.client.data.User;

public interface MainPresenter {
    void getUser(long id);
    void getUsers();
    void addUser(NewUser user);
    void updateUser(long id, NewUser user);
    void deleteUser(long id);
    void dispose();
}
