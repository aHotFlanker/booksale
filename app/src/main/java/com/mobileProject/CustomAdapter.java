package com.mobileProject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<ImageAndText> {
    public CustomAdapter(Context context, ArrayList<ImageAndText> objList) {
        super(context, R.layout.listview_layout,objList);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.listview_layout,parent,
                false);

        ImageAndText item = getItem(position);
        ImageView imageView = customView.findViewById(R.id.image);
        TextView textView = customView.findViewById(R.id.txt);
        imageView.setImageResource(item.getImgId());
        textView.setText(item.getTxt());
        return customView;
    }
}
