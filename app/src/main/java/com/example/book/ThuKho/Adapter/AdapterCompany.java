package com.example.book.ThuKho.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.book.Dialog.NotificationDialog;
import com.example.book.R;
import com.example.book.ThuKho.Object.Company;
import com.example.book.ThuKho.Thu_Kho_AddCompany;
import com.example.book.ThuKho.Thu_Kho_UpdateCompany;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterCompany extends BaseAdapter {
    private int layout;
    private ArrayList<Company> listCompany;
    private Context context;

    public AdapterCompany(int layout, ArrayList<Company> listCompany, Context context) {
        this.layout = layout;
        this.listCompany = listCompany;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listCompany.size();
    }

    @Override
    public Object getItem(int i) {
        return listCompany.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {
        private TextView tvTenCongty, tvMaST;
        private ImageView imgUpdate, imgDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(layout, viewGroup, false);

            viewHolder.tvTenCongty = view.findViewById(R.id.tvItemTenCongTy);
            viewHolder.tvMaST = view.findViewById(R.id.tvItemMSTCongTy);
            viewHolder.imgUpdate = view.findViewById(R.id.btnEditCompany);
            viewHolder.imgDelete = view.findViewById(R.id.btnDeleteCompany);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Company company = listCompany.get(i);

        viewHolder.tvTenCongty.setText(company.getName());
        viewHolder.tvMaST.setText("MST: " + company.getTaxcode() + "");

        viewHolder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Thu_Kho_UpdateCompany.class);
                i.putExtra("data", company);
                context.startActivity(i);
            }
        });

        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn Có Muốn Xóa Thông Tin Công Ty ?")
                        .setTitle("Xóa Thông Tin Công Ty")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DatabaseReference data = FirebaseDatabase.getInstance().getReference("company");
                                data.child(company.getId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        NotificationDialog notificationDialog = new NotificationDialog((Activity) context);
                                        notificationDialog.startSuccessfulDialog("Xóa Thông Tin Thành Công");
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Không", null);
                // Create the AlertDialog object and return it
                builder.create().show();

            }
        });

        return view;
    }
}
