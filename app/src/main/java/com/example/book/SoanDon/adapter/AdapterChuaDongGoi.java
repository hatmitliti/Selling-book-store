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
import com.example.book.XuLyHD.DonHangChoXuLy.Bill;

import java.util.ArrayList;
import java.util.List;

public class AdapterChuaDongGoi extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Bill> data;

    public AdapterChuaDongGoi(Context context, int resource, ArrayList<Bill> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(resource, null);

            viewHolder.txtDonhang = convertView.findViewById(R.id.txtDonhang);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Bill bill = data.get(position);
        viewHolder.txtDonhang.setText(bill.getName() + " - " + bill.getTotalMoney() + " VND");

        return convertView;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    public static class ViewHolder {
        TextView txtDonhang;
    }
}
