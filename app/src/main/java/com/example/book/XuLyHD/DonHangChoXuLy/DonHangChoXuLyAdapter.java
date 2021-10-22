package com.example.book.XuLyHD.DonHangChoXuLy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.book.R;

import java.util.ArrayList;

public class DonHangChoXuLyAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private ArrayList<Bill> listDHChoXuLy;

    public DonHangChoXuLyAdapter(int layout, Context context, ArrayList<Bill> listDHChoXuLy) {
        this.layout = layout;
        this.context = context;
        this.listDHChoXuLy = listDHChoXuLy;
    }

    @Override
    public int getCount() {
        return listDHChoXuLy.size();
    }

    @Override
    public Object getItem(int position) {
        return listDHChoXuLy.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView tvMaDH, tvTenNguoiDat;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvMaDH = convertView.findViewById(R.id.adapterXLDHMaDonHang);
            viewHolder.tvTenNguoiDat = convertView.findViewById(R.id.adapterXLDHNguoiDat);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvMaDH.setText( "Mã Đơn Hàng: "+listDHChoXuLy.get(position).getId());
        viewHolder.tvTenNguoiDat.setText("Tên Người Đặt: "+listDHChoXuLy.get(position).getName());

        return convertView;
    }
}
