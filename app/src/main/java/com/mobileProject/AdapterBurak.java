package com.mobileProject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterBurak extends ArrayAdapter<ImgAndTxt> {

    public AdapterBurak(Context context, ArrayList<ImgAndTxt> objList) {
        super(context, R.layout.imgandtxt, objList);

    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.imgandtxt, parent, false);

        ImgAndTxt item = getItem(position);
        String itemTxt = item.getTxt();
        ImageView imageViewforListView = customView.findViewById(R.id.imageViewforListView);
        TextView tvForBookInfo = customView.findViewById(R.id.tvForBookInfo);

        imageViewforListView.setImageResource(item.getImgId());

        tvForBookInfo.setText(itemTxt);
        return customView;
    }
}
