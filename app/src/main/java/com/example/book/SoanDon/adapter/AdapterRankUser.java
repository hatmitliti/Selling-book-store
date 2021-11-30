package com.example.book.SoanDon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.book.R;
import com.example.book.SoanDon.models.Rank;

import java.text.NumberFormat;
import java.util.ArrayList;

public class AdapterRankUser extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Rank> list;

    public AdapterRankUser(Context context, int resource, ArrayList<Rank> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource, null);
            viewHolder.txtMoney = convertView.findViewById(R.id.txtMoney);
            viewHolder.txtNameRank = convertView.findViewById(R.id.txtNameRank);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Rank rank = list.get(position);

        viewHolder.txtNameRank.setText(rank.getName());
        viewHolder.txtMoney.setText(NumberFormat.getInstance().format(rank.getMoney()));
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public static class ViewHolder {
        TextView txtMoney;
        TextView txtNameRank;
    }
}
