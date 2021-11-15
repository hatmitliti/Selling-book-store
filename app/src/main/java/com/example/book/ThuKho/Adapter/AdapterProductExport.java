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
import java.util.List;

public class AdapterProductExport extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<String> data;
    ArrayList<Integer> dataQuality;

    public AdapterProductExport(Context context, int resource, ArrayList<String> data, ArrayList<Integer> dataQuality) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.dataQuality = dataQuality;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView qualityProductExportItem = convertView.findViewById(R.id.qualityProductExportItem);
        TextView nameProductExportItem = convertView.findViewById(R.id.nameProductExportItem);


        qualityProductExportItem.setText(dataQuality.get(position) + "");
        nameProductExportItem.setText(data.get(position) + "");
        return convertView;
    }
}
