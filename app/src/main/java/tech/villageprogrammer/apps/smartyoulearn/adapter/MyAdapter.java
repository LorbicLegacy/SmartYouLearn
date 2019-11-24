package tech.villageprogrammer.apps.smartyoulearn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import tech.villageprogrammer.apps.smartyoulearn.R;


public class MyAdapter extends ArrayAdapter {
    private int mResource;
    private Context mContext;

    public MyAdapter(@NonNull Context context, int resource, ArrayList<String> arrayList) {
        super(context, resource,arrayList);
        this.mContext = context;
        this.mResource = resource;
    }

    @Override
    public View getView(int i, View view,@NonNull ViewGroup viewGroup) {
        String text = getItem(i).toString();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(mResource,viewGroup,false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(text);
        return view;
    }
}