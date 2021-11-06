package com.example.book.ThuKho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.book.R;
import com.example.book.ThuKho.Object.Category;
import com.example.book.ThuKho.TKQuanLiSanPham.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategory extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Category> data;
    ArrayList<String> listCategoryProduct;

    public AdapterCategory(Context context, int resource, ArrayList<Category> data, ArrayList<String> listCategoryProduct) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.listCategoryProduct = listCategoryProduct;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        ArrayList<Integer> listNum = new ArrayList<>();
        for (int j = 0; j < data.size(); j++) {
            int dem = 0;
            for (int k = 0; k < listCategoryProduct.size(); k++) {
                if (listCategoryProduct.get(k).equals(data.get(j).getName())) {
                    dem++;
                }
            }
            listNum.add(dem);
        }

        //
        Category category = data.get(position);
        TextView txtLoaiSach = convertView.findViewById(R.id.txtLoaiSach);
        TextView txtSoLuongLoaiSach = convertView.findViewById(R.id.txtSoLuongLoaiSach);

        txtLoaiSach.setText(category.getName());
        txtSoLuongLoaiSach.setText("Có " + listNum.get(position) + " sách thuộc loại này");


        return convertView;
    }


}
