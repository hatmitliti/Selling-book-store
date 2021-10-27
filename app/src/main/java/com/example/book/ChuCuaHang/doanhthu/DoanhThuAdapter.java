package com.example.book.ChuCuaHang.doanhthu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.book.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DoanhThuAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private ArrayList<DoanhThu> listDoanhThu;

    public DoanhThuAdapter(int layout, Context context, ArrayList<DoanhThu> listDoanhThu) {
        this.layout = layout;
        this.context = context;
        this.listDoanhThu = listDoanhThu;
    }
    @Override
    public int getCount() {
        return listDoanhThu.size();
    }

    @Override
    public Object getItem(int position) {
        return listDoanhThu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder {
        TextView tvMaDH, tvGiaTriDH;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Định dạng số
        NumberFormat currentLocale = NumberFormat.getInstance();
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        //
        ViewHolder viewHolder;
        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvMaDH = convertView.findViewById(R.id.adapterDTtvMaDH);
            viewHolder.tvGiaTriDH = convertView.findViewById(R.id.adapterDTtvTriGia);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvMaDH.setText(listDoanhThu.get(position).getMaDH());
        viewHolder.tvGiaTriDH.setText(en.format(listDoanhThu.get(position).getGiaTriDH())+" VNĐ");

        return convertView;
    }
}
