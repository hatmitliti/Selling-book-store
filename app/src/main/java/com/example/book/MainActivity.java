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
import com.example.book.Object.ChuCuaHang_Login;
import com.example.book.Object.QuanLy_Login;
import com.example.book.Object.Shipper_Login;
import com.example.book.Object.SoanDon_Login;
import com.example.book.Object.ThuKho_Login;
import com.example.book.Object.User_Login;
import com.example.book.Object.XuLyHD_Login;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    TextView txtUsername, txtPassword;
    DatabaseReference data;
    FirebaseAuth auth;
    String username;
    String password;
    private NotificationDialog notificationDialog;


    ArrayList<ChuCuaHang_Login> listChuCuaHang;
    ArrayList<QuanLy_Login> listQuanLy;
    ArrayList<Shipper_Login> listShipper;
    ArrayList<SoanDon_Login> listSoanDon;
    ArrayList<ThuKho_Login> listThuKho;
    ArrayList<XuLyHD_Login> listXuLyHD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationDialog = new NotificationDialog(this);
        getDataLogin();
        setControl();
        setAction();
    }

    private void setControl() {
        btnLogin = findViewById(R.id.btnLogin);
        txtPassword = findViewById(R.id.txtPassword);
        txtUsername = findViewById(R.id.txtUsername);
    }

    public void getDataLogin() {
        listChuCuaHang = new ArrayList<>();
        listQuanLy = new ArrayList<>();
        listShipper = new ArrayList<>();
        listSoanDon = new ArrayList<>();
        listThuKho = new ArrayList<>();
        listXuLyHD = new ArrayList<>();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        // lấy danh sách chủ
        database.child("admin").child("chuCuaHang").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                listChuCuaHang.add(snapshot.getValue(ChuCuaHang_Login.class));
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
        // ds quản lý
        database.child("admin").child("quanLy").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                listQuanLy.add(snapshot.getValue(QuanLy_Login.class));
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
        // ds shipper
        database.child("shipper").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                listShipper.add(snapshot.getValue(Shipper_Login.class));
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
        // ds soandon
        database.child("admin").child("soanDon").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                listSoanDon.add(snapshot.getValue(SoanDon_Login.class));
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
        // ds thủ kho
        database.child("admin").child("thuKho").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                listThuKho.add(snapshot.getValue(ThuKho_Login.class));
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
        // ds xử lý hd
        database.child("admin").child("xuLyHD").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                listXuLyHD.add(snapshot.getValue(XuLyHD_Login.class));
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


    private void setAction() {
        data = FirebaseDatabase.getInstance().getReference("admin");

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
                                String checkPermission = checkLogin(username);
                                if (checkPermission == null) {
                                    notificationDialog.startErrorDialog("Bạn không có quyền truy cập");
                                    notificationDialog.endLoadingDialog();
                                } else {
                                    switch (checkPermission) {
                                        case "chuCuaHang":
                                            notificationDialog.endLoadingDialog();
                                            startActivity(new Intent(getApplicationContext(), ChuCuaHang_ManHinhChinh.class));
                                            break;
                                        case "soanDon":
                                            notificationDialog.endLoadingDialog();
                                            startActivity(new Intent(getApplicationContext(), PackerActivity.class));
                                            break;
                                        case "shipper":
                                            notificationDialog.endLoadingDialog();
                                            notificationDialog.startErrorDialog("Bạn không có quyền truy cập");
                                            auth.signOut();
                                            break;
                                        case "xuLyHD":
                                            notificationDialog.endLoadingDialog();
                                            startActivity(new Intent(getApplicationContext(), NguoiXuLyHoaDon_ManHinhChinh.class));
                                            break;
                                        case "thuKho":
                                            notificationDialog.endLoadingDialog();
                                            startActivity(new Intent(getApplicationContext(), Thu_kho_main.class));
                                            break;
                                        case "quanLy":
                                            notificationDialog.endLoadingDialog();
                                            startActivity(new Intent(getApplicationContext(), ManageActivity.class));
                                            break;
                                    }
                                }
                            } else {
                                notificationDialog.startErrorDialog(getResources().getString(R.string.login_failed));
                                notificationDialog.endLoadingDialog();
                            }
                        }
                    });
                }
            }
        });
    }

    public String checkLogin(String email) {
        String chuCuaHang = "chuCuaHang";
        String soanDon = "soanDon";
        String shipper = "shipper";
        String xuLyHD = "xuLyHD";
        String thuKho = "thuKho";
        String quanLy = "quanLy";

        for (int i = 0; i < listChuCuaHang.size(); i++) {
            if (listChuCuaHang.get(i).getEmail().equals(email)) {
                return chuCuaHang;
            }
        }
        for (int i = 0; i < listQuanLy.size(); i++) {
            if (listQuanLy.get(i).getEmail().equals(email)) {
                return quanLy;
            }
        }
        for (int i = 0; i < listShipper.size(); i++) {
            if (listShipper.get(i).getEmail().equals(email)) {
                return shipper;
            }
        }
        for (int i = 0; i < listSoanDon.size(); i++) {
            if (listSoanDon.get(i).getEmail().equals(email)) {
                return soanDon;
            }
        }
        for (int i = 0; i < listThuKho.size(); i++) {
            if (listThuKho.get(i).getEmail().equals(email)) {
                return thuKho;
            }
        }
        for (int i = 0; i < listXuLyHD.size(); i++) {
            if (listXuLyHD.get(i).getEmail().equals(email)) {
                return xuLyHD;
            }
        }
        return null;
    }
}