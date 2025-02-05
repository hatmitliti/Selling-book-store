package com.example.book.ThuKho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.book.Dialog.NotificationDialog;
import com.example.book.R;
import com.example.book.ThuKho.Object.Company;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Thu_Kho_UpdateCompany extends AppCompatActivity {
    EditText edtName, edtAddress, edtPhone, edtEmail, edtaxcode;
    Button btnSuaCongty;
    private NotificationDialog notificationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatecompany);

        setControll();
        setEvent();
    }

    private void setEvent() {
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
        //
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("company");
        notificationDialog = new NotificationDialog(Thu_Kho_UpdateCompany.this);

        Intent i = getIntent();
        Company company = (Company) i.getSerializableExtra("data");

        edtName.setText(company.getName());
        edtAddress.setText(company.getAddress());
        edtEmail.setText(company.getEmail());
        edtPhone.setText(company.getPhone());
        edtaxcode.setText(company.getTaxcode() + "");

        btnSuaCongty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtName.getText().toString().equals("") || edtAddress.getText().toString().equals("") || edtEmail.getText().toString().equals("") || edtPhone.getText().toString().equals("") ||
                        edtaxcode.getText().toString().equals("")) {
                    notificationDialog.startErrorDialog("Bạn Chưa Nhập Đầy Đủ Dữ Liệu");
                } else {
                    String id = company.getId();
                    String name = edtName.getText().toString();
                    String address = edtAddress.getText().toString();
                    String phone = edtPhone.getText().toString();
                    String email = edtEmail.getText().toString();
                    long taxcode = Long.parseLong(edtaxcode.getText().toString());
                    Company company = new Company(name, address, phone, email, id, taxcode);

                    data.child(id).setValue(company).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            onBackPressed();
                            notificationDialog.startSuccessfulDialog("Sửa Thông Tin Thành Công");
                        }
                    });
                }
            }
        });
    }

    private void setControll() {
        edtName = findViewById(R.id.edtUpdateTenCongTy);
        edtAddress = findViewById(R.id.edtUpdateDiaChiConTy);
        edtEmail = findViewById(R.id.edtUpdateEmailCongTy);
        edtPhone = findViewById(R.id.edtUpdateSDTCongTy);
        edtaxcode = findViewById(R.id.edtUpdateMSTCongTy);
        btnSuaCongty = findViewById(R.id.btnSuaCongTy);
    }
}
