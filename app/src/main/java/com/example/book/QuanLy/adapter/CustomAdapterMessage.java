package com.example.book.QuanLy.adapter;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.book.QuanLy.models.Message;
import com.example.book.QuanLy.models.MessageDetail;
import com.example.book.R;

import java.util.ArrayList;

public class CustomAdapterMessage extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<MessageDetail> data;

    public CustomAdapterMessage(Context context, int resource, ArrayList<MessageDetail> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        MessageDetail message = data.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);

            viewHolder = new ViewHolder();
            viewHolder.itemTinNhan = convertView.findViewById(R.id.itemTinNhan);
            viewHolder.lnItemTinNhan = convertView.findViewById(R.id.lnItemTinNhan);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.itemTinNhan.setText(data.get(position).getContent());
        if (message.getWho().equals("admin")) {
            viewHolder.lnItemTinNhan.setGravity(Gravity.RIGHT);
        } else {
            viewHolder.lnItemTinNhan.setGravity(Gravity.LEFT);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public static class ViewHolder {
        TextView itemTinNhan;
        LinearLayout lnItemTinNhan;
    }
}
