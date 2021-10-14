package com.example.book.ChuCuaHang.thongkekho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.book.R;

import java.util.ArrayList;

public class SPKhoAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private ArrayList<SPKho> listSPKho;

    public SPKhoAdapter(int layout, Context context, ArrayList<SPKho> listSPKho) {
        this.layout = layout;
        this.context = context;
        this.listSPKho = listSPKho;
    }

    @Override
    public int getCount() {
        return listSPKho.size();
    }

    @Override
    public Object getItem(int position) {
        return listSPKho.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView tvMaSP, tvTenSP, tvSoLuong;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvMaSP = convertView.findViewById(R.id.adapterTKKtvMaSP);
            viewHolder.tvTenSP = convertView.findViewById(R.id.adapterTKKtvTenSP);
            viewHolder.tvSoLuong = convertView.findViewById(R.id.adapterTKKtvSoLuong);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvMaSP.setText(listSPKho.get(position).getMaSP());
        viewHolder.tvTenSP.setText(listSPKho.get(position).getTenSP());
        viewHolder.tvSoLuong.setText(listSPKho.get(position).getSoLuong()+"");

        return convertView;
    }
}
