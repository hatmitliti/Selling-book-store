package com.example.store.ChuCuaHang.thongkedonhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.store.R;

import java.util.ArrayList;

public class DonhangAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private ArrayList<DonHang> listDonHang;

    public DonhangAdapter(Context context, int layout, ArrayList<DonHang> listDonHang) {
        this.layout = layout;
        this.context = context;
        this.listDonHang = listDonHang;
    }

    @Override
    public int getCount() {
        return listDonHang.size();
    }

    @Override
    public Object getItem(int position) {
        return listDonHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView tvMaDH, tvNguoiDat, tvDiaChi, tvTriGia, tvTinhTrang;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvMaDH = convertView.findViewById(R.id.adapterTKDHtvMaDH);
            viewHolder.tvNguoiDat = convertView.findViewById(R.id.adapterTKDHtvNguoiDat);
            viewHolder.tvDiaChi = convertView.findViewById(R.id.adapterTKDHtvDiaChi);
            viewHolder.tvTriGia = convertView.findViewById(R.id.adapterTKDHtvTriGia);
            viewHolder.tvTinhTrang = convertView.findViewById(R.id.adapterTKDHtvTinhTrang);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvMaDH.setText(listDonHang.get(position).getMaDH());
        viewHolder.tvNguoiDat.setText(listDonHang.get(position).getNguoiDat());
        viewHolder.tvDiaChi.setText(listDonHang.get(position).getDiaChi());
        viewHolder.tvTriGia.setText(listDonHang.get(position).getTriGia());
        viewHolder.tvTinhTrang.setText(listDonHang.get(position).getTinhTrang());

        return convertView;
//        LayoutInflater inflater = LayoutInflater.from(context);
//        convertView = inflater.inflate(layout, parent, false);
//        TextView tvMaDH = convertView.findViewById(R.id.adapterTKDHtvMaDH);
//        TextView tvNguoiDat = convertView.findViewById(R.id.adapterTKDHtvNguoiDat);
//        TextView tvDiaChi = convertView.findViewById(R.id.adapterTKDHtvDiaChi);
//        TextView tvTriGia = convertView.findViewById(R.id.adapterTKDHtvTriGia);
//        TextView tvTinhTrang = convertView.findViewById(R.id.adapterTKDHtvTinhTrang);
//
//
//          tvMaDH.setText(listDonHang.get(position).getMaDH());
//        tvNguoiDat.setText(listDonHang.get(position).getNguoiDat());
//        tvDiaChi.setText(listDonHang.get(position).getDiaChi());
//        tvTriGia.setText(listDonHang.get(position).getTriGia());
//        tvTinhTrang.setText(listDonHang.get(position).getTinhTrang());
//
//        return convertView;
    }
}
