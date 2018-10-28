package ilya.restclient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collection;

abstract class CustomArrayAdapter<T> extends ArrayAdapter<T> {
    private LayoutInflater inflater;
    private ArrayList<T> data;
    private int layoutResId;

    public CustomArrayAdapter(Context context, ArrayList<T> data, int layoutResId) {
        super(context, layoutResId);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.data = data;
        this.layoutResId = layoutResId;
    }

    @Override
    public void add(T object) {
        data.add(object);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(@NonNull Collection<? extends T> collection) {
        data.addAll(collection);
        notifyDataSetChanged();
    }

    @Override
    public void remove(T object) {
        data.remove(object);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public boolean contains(T item) {
    return data.contains(item);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) view = inflater.inflate(layoutResId, parent, false);

        return createView(position, view);
    }

    abstract View createView(int position, View view);
}
