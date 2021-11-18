package com.example.book.XuLyHD.DonHangChoXuLy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.book.R;
import com.example.book.SoanDon.models.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DonHangChoXuLyAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private ArrayList<Bill> listDHChoXuLy;
    private ArrayList<String> mkey = new ArrayList<>();

    public DonHangChoXuLyAdapter(int layout, Context context, ArrayList<Bill> listDHChoXuLy) {
        this.layout = layout;
        this.context = context;
        this.listDHChoXuLy = listDHChoXuLy;
    }

    @Override
    public int getCount() {
        return listDHChoXuLy.size();
    }

    @Override
    public Object getItem(int position) {
        return listDHChoXuLy.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView tvMaDH, tvTenNguoiDat;
        ImageView imgBlacklistIconDHCXL;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Biến FireBase
        DatabaseReference dataBill = FirebaseDatabase.getInstance().getReference();
        //
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvMaDH = convertView.findViewById(R.id.adapterXLDHMaDonHang);
            viewHolder.tvTenNguoiDat = convertView.findViewById(R.id.adapterXLDHNguoiDat);
            viewHolder.imgBlacklistIconDHCXL = convertView.findViewById(R.id.imgBlacklistIconDHCXL);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvMaDH.setText("Mã Đơn Hàng: " + listDHChoXuLy.get(position).getId());
        viewHolder.tvTenNguoiDat.setText("Tên Người Đặt: " + listDHChoXuLy.get(position).getName());
        String billUser = listDHChoXuLy.get(position).getId_user();

        dataBill.child("blacklist").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String key = snapshot.getKey();
                mkey.add(key);
                if(mkey.contains(billUser))
                {
                    viewHolder.imgBlacklistIconDHCXL.setImageResource(R.drawable.blacklisticon);
                    DonHangChoXuLyAdapter.this.notifyDataSetChanged();
                }else{
                    viewHolder.imgBlacklistIconDHCXL.setImageResource(0);
                    DonHangChoXuLyAdapter.this.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String key = snapshot.getKey();
                mkey.remove(key);
                if(mkey.contains(billUser))
                {
                    viewHolder.imgBlacklistIconDHCXL.setImageResource(0);
                    DonHangChoXuLyAdapter.this.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return convertView;
    }
}
