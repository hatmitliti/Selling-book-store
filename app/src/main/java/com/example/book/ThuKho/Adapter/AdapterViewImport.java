package com.example.book.ThuKho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.book.R;
import com.example.book.ThuKho.Object.Company;
import com.example.book.ThuKho.Object.ImportProduct;

import java.util.ArrayList;

public class AdapterViewImport extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ImportProduct> data;

    public AdapterViewImport(Context context, int resource, ArrayList<ImportProduct> data) {
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource, null);
            viewHolder.txtNameImport = convertView.findViewById(R.id.txtNameImport);
            viewHolder.txtQualityImport = convertView.findViewById(R.id.txtQualityImport);
            viewHolder.txtDateImport = convertView.findViewById(R.id.txtDateImport);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImportProduct importProduct = data.get(position);
        viewHolder.txtNameImport.setText(importProduct.getNameCty());
        viewHolder.txtQualityImport.setText("Số lượng hàng: " + importProduct.getList().size());
        viewHolder.txtDateImport.setText(importProduct.getDate());

        return convertView;
    }

    public static class ViewHolder {
        TextView txtNameImport, txtQualityImport, txtDateImport;
    }
}
