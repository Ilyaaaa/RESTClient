package ilya.restclient.interfaces;

import ilya.restclient.client.data.NewUser;

public interface UserEditDialogCreator {
    void showDialog();
    void showDialog(long userId, NewUser user);
}
