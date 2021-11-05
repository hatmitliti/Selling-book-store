package com.example.book.SoanDon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.book.R;
import com.example.book.ThuKho.Object.ProductExImport;
import com.example.book.XuLyHD.DonHangChoXuLy.Bill;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ComfirmAdapter extends ArrayAdapter {
    Context context;
    int layoutItemID;
    ArrayList<ProductExImport> delivereds;

    public ComfirmAdapter(Context context, int resource, ArrayList<ProductExImport> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutItemID = resource;
        this.delivereds = objects;
    }

    @Override
    public int getCount() {
        return delivereds.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(layoutItemID, null);

        TextView txtTenSachNhanHang = convertView.findViewById(R.id.txtTenSachNhanHang);
        TextView txtSoLuongNhanHang = convertView.findViewById(R.id.txtSoLuongNhanHang);
        Button btnXacNhanNhanHang = convertView.findViewById(R.id.btnXacNhanNhanHang);

        ProductExImport productExImport = delivereds.get(position);
        txtSoLuongNhanHang.setText("x" + productExImport.getQuality());
        txtTenSachNhanHang.setText(productExImport.getName());

        if (productExImport.isConfirm()) {
            btnXacNhanNhanHang.setEnabled(false);
        }

        btnXacNhanNhanHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("exports").child(productExImport.getId()).child("confirm").setValue(true);
                btnXacNhanNhanHang.setEnabled(false);
            }
        });

        return convertView;
    }
}
