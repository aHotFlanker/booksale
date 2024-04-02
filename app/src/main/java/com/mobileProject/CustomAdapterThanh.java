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

public class CustomAdapterThanh extends ArrayAdapter<OrderInformation> {
    public CustomAdapterThanh(Context context, ArrayList<OrderInformation> orders) {
        super(context, R.layout.order_edit_list, orders);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.order_edit_list, parent, false);
        OrderInformation item = getItem(position);

        ImageView img = customView.findViewById(R.id.imageView);
        TextView tvBookName = customView.findViewById(R.id.textView6);
        TextView tvOrderDesc = customView.findViewById(R.id.textView5);

        img.setImageResource(item.getImgID());
        tvBookName.setText(item.getBookName());
        tvOrderDesc.setText(item.getDelivery());

        return customView;

    }
}
