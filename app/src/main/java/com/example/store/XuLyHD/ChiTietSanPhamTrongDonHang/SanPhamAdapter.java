package com.example.store.XuLyHD.ChiTietSanPhamTrongDonHang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.store.R;

import java.util.ArrayList;

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
        viewHolder.tvTenSP.setText(listSanPham.get(position).getTenSP());
        viewHolder.tvSoLuong.setText("Số Lượng: "+listSanPham.get(position).getSoLuongSP());
        viewHolder.tvGiaSP.setText("Giá: "+listSanPham.get(position).getGiaSP() + "VNĐ");
        viewHolder.imgAnhSP.setImageResource(listSanPham.get(position).getImgAnhSP());

        return convertView;
    }
}
