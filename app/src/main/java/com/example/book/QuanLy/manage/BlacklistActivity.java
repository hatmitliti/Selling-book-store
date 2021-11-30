package com.example.book.QuanLy.manage;import android.content.Context;import android.content.Intent;import android.os.Bundle;import android.view.View;import android.widget.Button;import androidx.appcompat.app.AppCompatActivity;import androidx.appcompat.widget.Toolbar;import com.example.book.R;public class BlacklistActivity extends AppCompatActivity {    private Context context;    private Button btnBack, btnDanhSachBlackList, btnAddBlackList;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.manage_blacklist_layout);        setTitle("Danh Sách Đen");        setControll();        setEvent();        // toolbarr        Toolbar toolbar = findViewById(R.id.toobar);        setSupportActionBar(toolbar);        getSupportActionBar().setDisplayHomeAsUpEnabled(true);        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);        toolbar.setNavigationOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                onBackPressed();            }        });    }    private void setEvent() {        context = this;        btnAddBlackList.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Intent intent = new Intent(context,BlackList_add.class);                startActivity(intent);            }        });        btnDanhSachBlackList.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Intent intent = new Intent(context,Blacklist_List.class);                startActivity(intent);            }        });    }    private void setControll() {       // btnBack = findViewById(R.id.backQuanLiBlackList);        btnDanhSachBlackList = findViewById(R.id.btnTKBLdanhSachBlackList);        btnAddBlackList = findViewById(R.id.btnTKBLthemTaiKhoanVaoBlackList);    }}