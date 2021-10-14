package com.example.book.ChuCuaHang.thongkedanhgia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.book.R;

import java.util.ArrayList;

public class DanhGiaAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private ArrayList<DanhGia> listDanhGia;

    public DanhGiaAdapter(int layout, Context context, ArrayList<DanhGia> listDanhGia) {
        this.layout = layout;
        this.context = context;
        this.listDanhGia = listDanhGia;
    }

    @Override
    public int getCount() {
        return listDanhGia.size();
    }

    @Override
    public Object getItem(int position) {
        return listDanhGia.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder {
        TextView tvNguoiDG, tvTenSP, tvSoSao, tvNoiDung;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvNguoiDG = convertView.findViewById(R.id.adapterTKDGTXNguoiDG);
            viewHolder.tvTenSP = convertView.findViewById(R.id.adapterTKDGTXTenSP);
            viewHolder.tvSoSao = convertView.findViewById(R.id.adapterTKDGTXSoSao);
            viewHolder.tvNoiDung = convertView.findViewById(R.id.adapterTKDGTXNoiDung);


            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvNguoiDG.setText(listDanhGia.get(position).getNguoiDG());
        viewHolder.tvTenSP.setText(listDanhGia.get(position).getTenSP());
        viewHolder.tvSoSao.setText(listDanhGia.get(position).getSoSao() + "");
        viewHolder.tvNoiDung.setText(listDanhGia.get(position).getNoiDung());
        return convertView;
    }
}
