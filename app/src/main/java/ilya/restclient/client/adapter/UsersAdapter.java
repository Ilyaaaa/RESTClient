package ilya.restclient.client.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ilya.restclient.R;
import ilya.restclient.client.data.User;

public class UsersAdapter extends CustomArrayAdapter<User> {
    private ArrayList<User> users;

    public UsersAdapter(Context context, ArrayList<User> users, int layoutResId) {
        super(context, users, layoutResId);

        this.users = users;
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getId();
    }

    @Override
    View createView(int position, View view) {
        User item = users.get(position);

        ((TextView) view.findViewById(R.id.item_name_field)).setText(item.getName());
        ((TextView) view.findViewById(R.id.item_email_field)).setText(item.getEmail());
        ((TextView) view.findViewById(R.id.item_num_field)).setText(item.getPhone());

        Glide
                .with(view)
                .load(item.getLinks().getAvatar().getHref())
                .into((ImageView) view.findViewById(R.id.item_img));

        return view;
    }
}
