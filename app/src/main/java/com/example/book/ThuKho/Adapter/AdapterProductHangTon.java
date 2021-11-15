package com.example.book.ThuKho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.book.R;
import com.example.book.ThuKho.TKQuanLiSanPham.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterProductHangTon extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Product> data;

    public AdapterProductHangTon(Context context, int resource, ArrayList<Product> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);
        Product product = data.get(position);
        ImageView imgThuKhoHangTon = convertView.findViewById(R.id.imgThuKhoHangTon);
        TextView txtTenSachThuKhoHangTon = convertView.findViewById(R.id.txtTenSachThuKhoHangTon);
        TextView txtHangTonThuKhoHangTon = convertView.findViewById(R.id.txtHangTonThuKhoHangTon);
        TextView txtBanRaThuKhoHangTon = convertView.findViewById(R.id.txtBanRaThuKhoHangTon);
        TextView txtTiLeThuKhoHangTon = convertView.findViewById(R.id.txtTiLeThuKhoHangTon);

        Picasso.get().load(product.getHinhAnh().toString()).into(imgThuKhoHangTon);
        txtTenSachThuKhoHangTon.setText(product.getTenSanPham());
        txtHangTonThuKhoHangTon.setText("Kho: " + product.getStock() + "");
        txtBanRaThuKhoHangTon.setText("Đã bán: " + product.getSold() + "");
        double tyleBan = 100;
        try {
            tyleBan = ((double) (product.getSold()) / ((double) product.getStock())) * 100;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (product.getStock() == 0) {
            tyleBan = 100;
        }
        txtTiLeThuKhoHangTon.setText("Tỷ lệ bán ra: " + tyleBan + "%");
        return convertView;
    }


}
