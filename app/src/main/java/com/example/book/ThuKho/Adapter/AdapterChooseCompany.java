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

import java.util.ArrayList;

public class AdapterChooseCompany extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Company> data;

    public AdapterChooseCompany(Context context, int resource, ArrayList<Company> data) {
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
            viewHolder.txtAddressCompany = convertView.findViewById(R.id.txtAddressCompany);
            viewHolder.txtIDCompany = convertView.findViewById(R.id.txtIDCompany);
            viewHolder.txtMailCompany = convertView.findViewById(R.id.txtMailCompany);
            viewHolder.txtPhoneCompany = convertView.findViewById(R.id.txtPhoneCompany);
            viewHolder.txtnameCompany = convertView.findViewById(R.id.txtnameCompany);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Company company = data.get(position);
        viewHolder.txtAddressCompany.setText(company.getAddress());
        viewHolder.txtIDCompany.setText(company.getId());
        viewHolder.txtMailCompany.setText(company.getEmail());
        viewHolder.txtPhoneCompany.setText(company.getPhone());
        viewHolder.txtnameCompany.setText(company.getName());

        return convertView;
    }

    public static class ViewHolder {
        TextView txtIDCompany, txtMailCompany, txtAddressCompany, txtPhoneCompany, txtnameCompany;
    }
}
