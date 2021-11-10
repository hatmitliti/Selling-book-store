package com.example.book.QuanLy.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.book.QuanLy.manage.UpdateEmployeeActivity;
import com.example.book.QuanLy.models.Employee;
import com.example.book.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdapterEmployee extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Employee> data;
    DeleteEmployee deleteEmploee;

    public AdapterEmployee(Context context, int resource, ArrayList<Employee> data, DeleteEmployee deleteEmploee) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.deleteEmploee = deleteEmploee;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
            viewHolder = new ViewHolder();
            viewHolder.btndeleteEmployee = convertView.findViewById(R.id.btndeleteEmployee);
            viewHolder.btnEditEmployee = convertView.findViewById(R.id.btnEditEmployee);
            viewHolder.txtEmployee = convertView.findViewById(R.id.txtEmployee);
            viewHolder.txtPermissionItem = convertView.findViewById(R.id.txtPermissionItem);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Employee employee = data.get(position);
        viewHolder.btndeleteEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmploee.deleteEmploee(employee);


            }
        });
        viewHolder.btnEditEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(getContext(), UpdateEmployeeActivity.class));
            }
        });
        viewHolder.txtEmployee.setText(employee.getName());

        String permission = employee.getPermission();
        if (permission.equals("soanDon")) {
            viewHolder.txtPermissionItem.setText("Soạn đơn");
        } else if (permission.equals("thuKho")) {
            viewHolder.txtPermissionItem.setText("Thủ kho");
        } else if (permission.equals("xuLyHD")) {
            viewHolder.txtPermissionItem.setText("Xử lý hóa đơn");
        } else if (permission.equals("shipper")) {
            viewHolder.txtPermissionItem.setText("Shipper");
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public static class ViewHolder {
        TextView txtEmployee;
        TextView txtPermissionItem;
        ImageView btnEditEmployee;
        ImageView btndeleteEmployee;
    }
}
