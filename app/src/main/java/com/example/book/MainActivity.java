package com.example.book;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.example.book.ChuCuaHang.ChuCuaHang_ManHinhChinh;
import com.example.book.Object.Admin;
import com.example.book.QuanLy.manage.ManageActivity;
import com.example.book.SoanDon.PackerActivity;
import com.example.book.ThuKho.Thu_kho_hang_ton;
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
    String[] arraypinner = {"Quản lý", "Người soạn đơn", "Xử lý hóa đơn", "Chủ cửa hàng", "Thủ kho"};
    DatabaseReference data;
    FirebaseAuth auth;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();

        setAction();

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
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

        // Khi bấm đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (quyenTruyCap) {
                    case 0: // quản lý
                        username = txtUsername.getText().toString();
                        password = txtPassword.getText().toString();

                        if (username.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập tài khoản!", Toast.LENGTH_SHORT).show();
                        } else if (password.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                        } else {
                            auth = FirebaseAuth.getInstance();
                            auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        data.child("quanLy").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                                Admin admin = snapshot.getValue(Admin.class);
                                                if (admin.getId().equals(auth.getUid())) {
                                                    startActivity(new Intent(getApplicationContext(), ManageActivity.class));
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
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                        break;
                    case 1: // người soạn đơn
                        username = txtUsername.getText().toString();
                        password = txtPassword.getText().toString();

                        if (username.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập tài khoản!", Toast.LENGTH_SHORT).show();
                        } else if (password.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                        } else {
                            auth = FirebaseAuth.getInstance();
                            auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        data.child("soanDon").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                                Admin admin = snapshot.getValue(Admin.class);
                                                if (admin.getId().equals(auth.getUid())) {
                                                    startActivity(new Intent(getApplicationContext(), PackerActivity.class));
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
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }


                        break;
                    case 2: // Xử lý hóa đơn
                        username = txtUsername.getText().toString();
                        password = txtPassword.getText().toString();

                        if (username.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập tài khoản!", Toast.LENGTH_SHORT).show();
                        } else if (password.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                        } else {
                            auth = FirebaseAuth.getInstance();
                            auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        data.child("xuLyHD").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                                Admin admin = snapshot.getValue(Admin.class);
                                                if (admin.getId().equals(auth.getUid())) {
                                                    startActivity(new Intent(getApplicationContext(), NguoiXuLyHoaDon_ManHinhChinh.class));
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
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                        break;
                    case 3: // Chủ cửa hàng

                        username = txtUsername.getText().toString();
                        password = txtPassword.getText().toString();

                        if (username.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập tài khoản!", Toast.LENGTH_SHORT).show();
                        } else if (password.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                        } else {
                            auth = FirebaseAuth.getInstance();
                            auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        data.child("chuCuaHang").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                                Admin admin = snapshot.getValue(Admin.class);
                                                if (admin.getId().equals(auth.getUid())) {
                                                    startActivity(new Intent(getApplicationContext(), ChuCuaHang_ManHinhChinh.class));
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
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        break;
                    case 4: // thủ kho

                        username = txtUsername.getText().toString();
                        password = txtPassword.getText().toString();

                        if (username.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập tài khoản!", Toast.LENGTH_SHORT).show();
                        } else if (password.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                        } else {
                            auth = FirebaseAuth.getInstance();
                            auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        data.child("thuKho").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                                Admin admin = snapshot.getValue(Admin.class);
                                                if (admin.getId().equals(auth.getUid())) {
                                                    startActivity(new Intent(getApplicationContext(), Thu_kho_main.class));
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
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
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