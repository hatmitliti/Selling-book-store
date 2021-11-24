package com.example.book.ChuCuaHang.thongkekho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.book.R;
import com.example.book.ThuKho.TKQuanLiSanPham.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SPKhoAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private ArrayList<SPKho> listSPKho;

    public SPKhoAdapter(int layout, Context context, ArrayList<SPKho> listSPKho) {
        this.layout = layout;
        this.context = context;
        this.listSPKho = listSPKho;
    }

    @Override
    public int getCount() {
        return listSPKho.size();
    }

    @Override
    public Object getItem(int position) {
        return listSPKho.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView tvMaSP, tvTenSP, tvSoLuong,tvTiLe;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvMaSP = convertView.findViewById(R.id.adapterTKKtvMaSP);
            viewHolder.tvTenSP = convertView.findViewById(R.id.adapterTKKtvTenSP);
            viewHolder.tvSoLuong = convertView.findViewById(R.id.adapterTKKtvSoLuong);
            viewHolder.tvTiLe = convertView.findViewById(R.id.adapterTKKtvTiLe);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvMaSP.setText(listSPKho.get(position).getMaSP());
        viewHolder.tvTenSP.setText(listSPKho.get(position).getTenSP());
        viewHolder.tvSoLuong.setText(listSPKho.get(position).getSoLuong() + "");
        // đọc dữ liệu từ firebase
        DatabaseReference dataProduct = FirebaseDatabase.getInstance().getReference();
        //
        dataProduct.child("products").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getValue(Product.class).getId().equals(listSPKho.get(position).getMaSP()))
                {
                    Product product = snapshot.getValue(Product.class);
                    double tyleBan = 100;
                    try {
                        tyleBan = ((double) (product.getSold()) / ((double) product.getStock())) * 100;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (product.getStock() == 0) {
                        tyleBan = 0;
                    }
                    viewHolder.tvTiLe.setText((Math.floor(tyleBan * 1000) / 1000)+" %");
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (listSPKho.get(position).getSoLuong() <= 20) {
            viewHolder.tvMaSP.setBackgroundResource(R.drawable.vienkho);
            viewHolder.tvTenSP.setBackgroundResource(R.drawable.vienkho);
            viewHolder.tvSoLuong.setBackgroundResource(R.drawable.vienkho);
            viewHolder.tvTiLe.setBackgroundResource(R.drawable.vienkho);
        } else {
            viewHolder.tvMaSP.setBackgroundResource(R.drawable.vien1);
            viewHolder.tvTenSP.setBackgroundResource(R.drawable.vien1);
            viewHolder.tvSoLuong.setBackgroundResource(R.drawable.vien1);
            viewHolder.tvTiLe.setBackgroundResource(R.drawable.vien1);
        }
        return convertView;
    }
}
