package ilya.restclient;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ilya.restclient.client.data.NewUser;
import ilya.restclient.client.data.User;
import ilya.restclient.interfaces.MainPresenter;
import ilya.restclient.interfaces.UserEditDialogCreator;

public class UserEditDialogCreatorImpl implements UserEditDialogCreator, View.OnClickListener {
    private Dialog dialog;
    private MainPresenter presenter;

    private TextInputLayout firstNameLayout;
    private EditText firstNameField;
    private TextInputLayout lastNameLayout;
    private EditText lastNameField;
    private TextInputLayout emailLayout;
    private EditText emailField;
    private TextInputLayout phoneLayout;
    private EditText phoneField;
    private TextInputLayout websiteLayout;
    private EditText websiteField;
    private TextInputLayout addressLayout;
    private EditText addressField;
    private TextInputLayout avatarLayout;
    private EditText avatarField;

    private Spinner genderSpinner;
    private DatePicker dob;

    private NewUser user;
    private long userId;
    private ArrayAdapter<CharSequence> adapter;

    public UserEditDialogCreatorImpl(Context context, MainPresenter presenter) {
        this.presenter = presenter;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.user_edit_dialog);

        firstNameLayout = dialog.findViewById(R.id.dialog_first_name_layout);
        firstNameField = dialog.findViewById(R.id.dialog_first_name_field);
        lastNameLayout = dialog.findViewById(R.id.dialog_last_name_layout);
        lastNameField = dialog.findViewById(R.id.dialog_last_name_field);
        emailLayout = dialog.findViewById(R.id.dialog_email_layout);
        emailField = dialog.findViewById(R.id.dialog_email_field);
        phoneLayout = dialog.findViewById(R.id.dialog_phone_layout);
        phoneField = dialog.findViewById(R.id.dialog_phone_field);
        websiteLayout = dialog.findViewById(R.id.dialog_website_layout);
        websiteField = dialog.findViewById(R.id.dialog_website_field);
        addressLayout = dialog.findViewById(R.id.dialog_address_layout);
        addressField = dialog.findViewById(R.id.dialog_address_field);
        avatarLayout = dialog.findViewById(R.id.dialog_avatar_link_layout);
        avatarField = dialog.findViewById(R.id.dialog_avatar_link_field);

        genderSpinner = dialog.findViewById(R.id.dialog_gender_spinner);
        dob = dialog.findViewById(R.id.dialog_date_picker);

        adapter = ArrayAdapter.createFromResource(context, R.array.genders_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        dialog.findViewById(R.id.dialog_submit_btn).setOnClickListener(this);
        dialog.findViewById(R.id.dialog_cancel_btn).setOnClickListener(this);
    }

    @Override
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void showDialog(long userId, NewUser user) {
        this.userId = userId;
        this.user = user;

        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhone());
        websiteField.setText(user.getWebsite());
        addressField.setText(user.getAddress());
        avatarField.setText(user.getAvatar());

        genderSpinner.setSelection(adapter.getPosition(user.getGender()));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(user.getDob());
        dob.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);

        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_submit_btn: {
                submit();
                break;
            }

            case R.id.dialog_cancel_btn: {
                cancel();
                break;
            }
        }
    }

    private void submit() {
        if (user == null) {
            NewUser newUser = new NewUser(firstNameField.getText().toString(), lastNameField.getText().toString(), genderSpinner.getSelectedItem().toString(), avatarField.getText().toString());
            newUser.setEmail(emailField.getText().toString());
            newUser.setPhone(phoneField.getText().toString());
            newUser.setWebsite(websiteField.getText().toString());
            newUser.setAddress(addressField.getText().toString());
            newUser.setDob(getDateFromDatePicker(dob));

            presenter.addUser(newUser);
        } else {
            user.setFirstName(firstNameField.getText().toString());
            user.setLastName(lastNameField.getText().toString());
            user.setGender(genderSpinner.getSelectedItem().toString());
            user.setAvatar(avatarField.getText().toString());
            user.setEmail(emailField.getText().toString());
            user.setPhone(phoneField.getText().toString());
            user.setWebsite(websiteField.getText().toString());
            user.setAddress(addressField.getText().toString());
            user.setDob(getDateFromDatePicker(dob));

            presenter.updateUser(userId, user);
        }

        cancel();
    }

    private void cancel() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        websiteField.setText("");
        addressField.setText("");
        avatarField.setText("");

        dialog.dismiss();
        user = null;
    }

    private Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
