package com.example.book.ThuKho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.book.R;
import com.example.book.ThuKho.Adapter.AdapterCategory;
import com.example.book.ThuKho.Adapter.AdapterProductExport;
import com.example.book.ThuKho.Object.Admin;
import com.example.book.ThuKho.Object.Category;
import com.example.book.ThuKho.Object.ProductExImport;
import com.example.book.ThuKho.TKQuanLiSanPham.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class Thu_kho_xuat_hang extends AppCompatActivity {

    ListView lvXuatHang;
    Button btnXemLaiXuatHang;
    Spinner spTenSachXuatHang;
    EditText txtSoLuongXuatHang;
    Button btnThemXuatHang;
    Button btnXuatHang;

    ArrayList<Product> listProductFull;
    ArrayList<String> listProductString;

    ArrayAdapter adapterProduct;
    ArrayAdapter adapterAdmin;

    ArrayList<Admin> listAdmins;
    ArrayList<String> listAdminsString;

    ArrayList<String> productExport = new ArrayList<>();
    ArrayList<Integer> qualityProduct = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu_kho_xuat_hang);
        setControl();
        getDataProduct();
        setSpinnerProduct();
        getDataTenNguoiXuatHang();

        // toolbarr
        Toolbar toolbar = findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        AdapterProductExport adapterProductExportImport = new AdapterProductExport(this, R.layout.thu_kho_xuat_hang_item, productExport, qualityProduct);
        lvXuatHang.setAdapter(adapterProductExportImport);
        btnThemXuatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spTenSachXuatHang.getSelectedItem().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa chọn", Toast.LENGTH_SHORT).show();
                } else {
                    if (txtSoLuongXuatHang.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Bạn chưa nhập số lượng", Toast.LENGTH_SHORT).show();
                    } else {
                        productExport.add(spTenSachXuatHang.getSelectedItem().toString());
                        qualityProduct.add(Integer.parseInt(txtSoLuongXuatHang.getText().toString()));
                        adapterProductExportImport.notifyDataSetChanged();
                        spTenSachXuatHang.setSelection(0);
                        txtSoLuongXuatHang.setText("");
                    }
                }
            }
        });


        btnXuatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("exports");
                for (int j = 0; j < productExport.size(); j++) {
                    ProductExImport productExImport = new ProductExImport(productExport.get(j), qualityProduct.get(j),UUID.randomUUID().toString(),false);
                    mDatabase.child(productExImport.getId()).setValue(productExImport);
                }
                productExport.clear();
                qualityProduct.clear();
                adapterProductExportImport.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Xong", Toast.LENGTH_SHORT).show();
            }
        });


        btnXemLaiXuatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Thu_kho_xem_lai.class));
            }
        });

    }

    private void getDataTenNguoiXuatHang() {
        listAdmins = new ArrayList<>();
        listAdminsString = new ArrayList<>();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("admins");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Admin admin = snapshot.getValue(Admin.class);
                if (admin.getPermission().equals("Soạn đơn")) {
                    listAdmins.add(admin);
                    listAdminsString.add(admin.getName());
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

    }

    private void setSpinnerProduct() {

        adapterProduct = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listProductString);
        spTenSachXuatHang.setAdapter(adapterProduct);
    }


    private void getDataProduct() {
        listProductFull = new ArrayList<>();
        listProductString = new ArrayList<>();
        listProductString.add("");
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("products");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product pd = snapshot.getValue(Product.class);
                pd.setId(snapshot.getKey());
                listProductFull.add(pd);
                listProductString.add(pd.getTenSanPham());
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
    }

    private void setControl() {
        lvXuatHang = findViewById(R.id.lvXuatHang);
        btnXemLaiXuatHang = findViewById(R.id.btnXemLaiXuatHang);
        spTenSachXuatHang = findViewById(R.id.spTenSachXuatHang);
        txtSoLuongXuatHang = findViewById(R.id.txtSoLuongXuatHang);
        btnThemXuatHang = findViewById(R.id.btnThemXuatHang);
        btnXuatHang = findViewById(R.id.btnXuatXuatHang);
    }
}
