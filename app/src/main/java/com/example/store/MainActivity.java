package com.example.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.store.ThuKho.Thu_kho_hang_ton;
import com.example.store.ThuKho.Thu_kho_main;
import com.example.store.XuLyHD.NguoiXuLyHoaDon_ManHinhChinh;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    TextView txtUsername, txtPassword;
    Spinner spQuyenTruyCap;
    Integer quyenTruyCap;
    String[] arraypinner = {"Quản lý", "Người soạn đơn", "Xử lý hóa đơn", "Chủ cửa hàng", "Thủ kho"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setAction();

    }

    private void setAction() {

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraypinner);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spQuyenTruyCap.setAdapter(spinnerArrayAdapter);


        // Khi bấm spinner:
        spQuyenTruyCap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                quyenTruyCap = spQuyenTruyCap.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Khi bấm đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (quyenTruyCap) {
                    case 0: // quản lý
                        break;
                    case 1: // người soạn đơn
                        break;
                    case 2: // Xử lý hóa đơn
                        startActivity(new Intent(getApplicationContext(), NguoiXuLyHoaDon_ManHinhChinh.class));
                        break;
                    case 3: // Chủ cửa hàng
                        break;
                    case 4: // thủ kho
                        Intent intent = new Intent(getApplicationContext(), Thu_kho_main.class);
                        startActivity(intent);
                        break;
                }
            }

        });
    }

    private void setControl() {
        btnLogin = findViewById(R.id.btnLogin);
        txtPassword = findViewById(R.id.txtPassword);
        txtUsername = findViewById(R.id.txtUsername);
        spQuyenTruyCap = findViewById(R.id.spQuyenTruyCap);
    }
}