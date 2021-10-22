package com.example.book.XuLyHD.ChiTietSanPhamTrongDonHang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.book.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SanPhamAdapter  extends BaseAdapter {
    private int layout;
    private Context context;
    private ArrayList<SanPham> listSanPham;

    public SanPhamAdapter(int layout, Context context, ArrayList<SanPham> listSanPham) {
        this.layout = layout;
        this.context = context;
        this.listSanPham = listSanPham;
    }

    @Override
    public int getCount() {
        return listSanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return listSanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder {
        TextView tvTenSP, tvSoLuong,tvGiaSP;
        ImageView imgAnhSP;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NumberFormat currentLocale = NumberFormat.getInstance();
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        ViewHolder viewHolder;
        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvTenSP = convertView.findViewById(R.id.adapterCTSPtvTenSP);
            viewHolder.tvGiaSP = convertView.findViewById(R.id.adapterCTSPtvGiaSach);
            viewHolder.tvSoLuong = convertView.findViewById(R.id.adapterCTSPtvSoLuong);
            viewHolder.imgAnhSP = convertView.findViewById(R.id.adapterCTSPIMGAnhSP);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String giaTien = en.format(listSanPham.get(position).getPrice());
        viewHolder.tvTenSP.setText(listSanPham.get(position).getName());
        viewHolder.tvSoLuong.setText("Số Lượng: "+listSanPham.get(position).getQuality());
        viewHolder.tvGiaSP.setText("Giá: "+ giaTien + "VNĐ");
        Picasso.get().load(listSanPham.get(position).getImage()).into(viewHolder.imgAnhSP);

        return convertView;
    }
}
