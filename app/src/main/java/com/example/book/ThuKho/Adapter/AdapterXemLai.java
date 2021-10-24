package com.example.book.ThuKho.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.book.R;
import com.example.book.ThuKho.Object.Category;
import com.example.book.ThuKho.Object.ProductExImport;

import java.util.ArrayList;
import java.util.List;

public class AdapterXemLai extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ProductExImport> data;

    public AdapterXemLai(Context context, int resource, ArrayList<ProductExImport> data) {
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
        convertView = LayoutInflater.from(context).inflate(resource, null);

        ProductExImport productExImport = data.get(position);
        TextView txtNameProductItemXemLai = convertView.findViewById(R.id.txtNameProductItemXemLai);
        TextView txtQualityProductXemLai = convertView.findViewById(R.id.txtQualityProductXemLai);
        TextView txtXacNhanItemXemLai = convertView.findViewById(R.id.txtXacNhanItemXemLai);

        txtNameProductItemXemLai.setText(productExImport.getName());
        txtQualityProductXemLai.setText(productExImport.getQuality() + "");
        txtXacNhanItemXemLai.setText(productExImport.isConfirm() == true ? "Đã xác nhận" : "Chưa xác nhận");
        if (!productExImport.isConfirm()) {
            txtXacNhanItemXemLai.setBackgroundColor(Color.RED);
        }else {
            txtXacNhanItemXemLai.setBackgroundColor(Color.GREEN);
        }
        return convertView;
    }
}
