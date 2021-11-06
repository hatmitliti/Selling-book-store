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
import com.example.book.ThuKho.Object.Category;

import java.util.ArrayList;

public class AdapterImportProduct extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Integer> listProductQualityImport;
    ArrayList<String> listProductIDImport;
    ArrayList<String> listProductStringImport;

    public AdapterImportProduct(Context context, int resource, ArrayList<Integer> listProductQualityImport, ArrayList<String> listProductIDImport, ArrayList<String> listProductStringImport) {
        super(context, resource, listProductStringImport);
        this.context = context;
        this.resource = resource;
        this.listProductQualityImport = listProductQualityImport;
        this.listProductIDImport = listProductIDImport;
        this.listProductStringImport = listProductStringImport;
    }

    @Override
    public int getCount() {
        return listProductQualityImport.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);


        TextView qualityProductExportItem = convertView.findViewById(R.id.qualityProductExportItem);
        TextView nameProductExportItem = convertView.findViewById(R.id.nameProductExportItem);

        qualityProductExportItem.setText(listProductQualityImport.get(position) + "");
        nameProductExportItem.setText(listProductStringImport.get(position) + "");

        return convertView;
    }
}
