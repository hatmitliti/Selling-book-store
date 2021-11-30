package com.example.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.book.ChuCuaHang.ChuCuaHang_ManHinhChinh;
import com.example.book.Dialog.NotificationDialog;
import com.example.book.Object.Admin;
import com.example.book.QuanLy.manage.ManageActivity;
import com.example.book.SoanDon.PackerActivity;
import com.example.book.ThuKho.Thu_kho_main;
import com.example.book.XuLyHD.NguoiXuLyHoaDon_ManHinhChinh;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    TextView txtUsername, txtPassword;
    Spinner spQuyenTruyCap;
    Integer quyenTruyCap;
    String[] arraypinner = {"Quản lý", "Soạn đơn", "Xử lý hóa đơn", "Chủ cửa hàng", "Thủ kho"};
    DatabaseReference data;
    FirebaseAuth auth;
    String username;
    String password;
    private NotificationDialog notificationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationDialog = new NotificationDialog(this);
        setControl();
        setAction();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
    }

    private void setControl() {
        btnLogin = findViewById(R.id.btnLogin);
        txtPassword = findViewById(R.id.txtPassword);
        txtUsername = findViewById(R.id.txtUsername);
        spQuyenTruyCap = findViewById(R.id.spQuyenTruyCap);
    }

    private void setAction() {
        data = FirebaseDatabase.getInstance().getReference("admin");
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

        //khi bấm quên passs:
        findViewById(R.id.btnFogotPass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgotPass.class));
            }
        });

        // Khi bấm đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();


                if (username.isEmpty()) {
                    txtUsername.setError(getResources().getString(R.string.empty_field));
                } else if (password.isEmpty()) {
                    txtPassword.setError(getResources().getString(R.string.empty_field));
                } else {
                    notificationDialog.startLoadingDialog();
                    auth = FirebaseAuth.getInstance();
                    auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                switch (quyenTruyCap) {
                                    case 0: // quản lý
                                        data.child("quanLy").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                                Admin admin = snapshot.getValue(Admin.class);
                                                if (admin.getId().equals(auth.getUid())) {
                                                    notificationDialog.endLoadingDialog();
                                                    startActivity(new Intent(getApplicationContext(), ManageActivity.class));
                                                    finishAffinity();
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
                                        break;
                                    case 1: //soan don
                                        data.child("soanDon").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                                Admin admin = snapshot.getValue(Admin.class);
                                                if (admin.getId().equals(auth.getUid())) {
                                                    notificationDialog.endLoadingDialog();
                                                    startActivity(new Intent(getApplicationContext(), PackerActivity.class));
                                                    finishAffinity();
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
                                        break;
                                    case 2:
                                        data.child("xuLyHD").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                                Admin admin = snapshot.getValue(Admin.class);
                                                if (admin.getId().equals(auth.getUid())) {
                                                    notificationDialog.endLoadingDialog();
                                                    startActivity(new Intent(getApplicationContext(), NguoiXuLyHoaDon_ManHinhChinh.class));
                                                    finishAffinity();
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
                                        break;
                                    case 3:
                                        data.child("chuCuaHang").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                                Admin admin = snapshot.getValue(Admin.class);
                                                if (admin.getId().equals(auth.getUid())) {
                                                    notificationDialog.endLoadingDialog();
                                                    startActivity(new Intent(getApplicationContext(), ChuCuaHang_ManHinhChinh.class));
                                                    finishAffinity();
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
                                        break;
                                    case 4:
                                        data.child("thuKho").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                                Admin admin = snapshot.getValue(Admin.class);
                                                if (admin.getId().equals(auth.getUid())) {
                                                    notificationDialog.endLoadingDialog();
                                                    startActivity(new Intent(getApplicationContext(), Thu_kho_main.class));
                                                    finishAffinity();
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
                                        break;
                                }
                            } else {
                                notificationDialog.startErrorDialog(getResources().getString(R.string.login_failed));
                            }
                        }
                    });
                }
            }
        });
    }
}