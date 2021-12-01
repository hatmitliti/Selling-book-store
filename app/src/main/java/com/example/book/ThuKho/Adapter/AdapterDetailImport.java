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
import com.example.book.ThuKho.Object.ImportProduct;
import com.example.book.ThuKho.Object.ProductImportObject;

import java.text.NumberFormat;
import java.util.ArrayList;

public class AdapterDetailImport extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ProductImportObject> data;

    public AdapterDetailImport(Context context, int resource, ArrayList<ProductImportObject> data) {
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
            viewHolder.txtname = convertView.findViewById(R.id.txtname);
            viewHolder.txtquality = convertView.findViewById(R.id.txtquality);
            viewHolder.txtprice = convertView.findViewById(R.id.txtprice);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ProductImportObject productImportObject = data.get(position);

        viewHolder.txtname.setText(productImportObject.getName());
        viewHolder.txtquality.setText("x" + productImportObject.getQuality());
        viewHolder.txtprice.setText(NumberFormat.getInstance().format(productImportObject.getPrice()) + " VND");


        return convertView;
    }

    public static class ViewHolder {
        TextView txtname, txtquality, txtprice;
    }
}
