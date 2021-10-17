package com.example.book.ThuKho;

import static com.example.book.R.color.common_google_signin_btn_text_dark_disabled;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.book.R;
import com.example.book.ThuKho.TKQuanLiSanPham.Product;
import com.example.book.ThuKho.TKQuanLiSanPham.ProductAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Thu_kho_tong_hop extends AppCompatActivity {
    ArrayList<Product> listProduct = new ArrayList<>();
    ProductAdapter adapterProduct;
    DatabaseReference dataProduct;
    ArrayList<String> mKey = new ArrayList<>();
    Context context;
    ListView lvProducts;
    ImageView ImgCamera, IMGThuVien;
    EditText edtTenSP, edtGiaSP, edtTacGia, edtMota;
    Spinner spnTheLoai;
    Button btnThem, btnSua, btnXoa;
    RadioButton rdbChonAnhTuCamera, rdbChonAnhTuThuVien;
    int REQUEST_CODE_IMAGE = 1;
    int RESULT_LOAD_IMAGE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu_kho_tong_hop);

        setControll();
        setEvent();
    }

    private void setEvent() {
        ImgCamera.setEnabled(false);
        IMGThuVien.setEnabled(false);
        ImgCamera.setTag("");
        String idThuVien = R.drawable.ic_baseline_image_24 + "";
        IMGThuVien.setTag(idThuVien);
        String idCamera = R.drawable.ic_baseline_camera_alt_24 + "";
        ImgCamera.setTag(idCamera);
        // gọi các đối tượng để đổ dữ liệu vào listview
        context = this;
        dataProduct = FirebaseDatabase.getInstance().getReference();
        adapterProduct = new ProductAdapter(context, R.layout.thu_kho_tong_hop_adapter_item, listProduct);
        lvProducts.setAdapter(adapterProduct);

        // tiến hành lấy dữ liệu đổ vào array list từ firebase
        dataProduct.child("products").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product pd = snapshot.getValue(Product.class);
                listProduct.add(pd);
                adapterProduct.notifyDataSetChanged();
                // lấy id của các sản phẩm
                String key = snapshot.getKey();
                mKey.add(key);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // lấy địa chỉ id của đối tượng vừa bị thay đổi bên trong mảng mkey
                String key = snapshot.getKey();
                int index = mKey.indexOf(key);
                // thay đổi dữ liệu trong gridview giống với dữ liệu trên firebase
                listProduct.set(index, snapshot.getValue(Product.class));
                adapterProduct.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String key = snapshot.getKey();
                int index = mKey.indexOf(key);
                listProduct.remove(index);
                adapterProduct.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        // sử lí sự kiện radiobutton cho phương thức nhận ảnh
        rdbChonAnhTuCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImgCamera.setEnabled(true);
                IMGThuVien.setEnabled(false);
                ImgCamera.setBackground(null);
                IMGThuVien.setBackground(getDrawable(R.drawable.backgroundimage));

            }
        });
        rdbChonAnhTuThuVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImgCamera.setEnabled(false);
                IMGThuVien.setEnabled(true);
                IMGThuVien.setBackground(null);
                ImgCamera.setBackground(getDrawable(R.drawable.backgroundimage));
            }
        });

        // xử lý sự kiện lấy ảnh từ camera
        ImgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
                Toast.makeText(context, "Lấy Ảnh Từ Camera", Toast.LENGTH_SHORT).show();
            }
        });
        IMGThuVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("scale", true);
                intent.putExtra("outputX", 256);
                intent.putExtra("outputY", 256);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);

                Toast.makeText(context, "Lấy Ảnh Từ Thư Viện", Toast.LENGTH_SHORT).show();
            }
        });
        //xử lý sự kiện cho button Thêm
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtTenSP.getText().toString().equals("") || edtGiaSP.getText().toString().equals("") || edtTacGia.getText().toString().equals("") || edtMota.getText().toString().equals("")
                        || spnTheLoai.getSelectedItem().toString().equals("Lựa chọn loại sách")
                ) {
                    Toast.makeText(context, "Thiếu Dữ Liệu Vui Lòng Kiểm Tra Lại", Toast.LENGTH_SHORT).show();
                }
                else if (rdbChonAnhTuCamera.isSelected() == true && ImgCamera.getTag().toString().equals(R.drawable.ic_baseline_camera_alt_24 + "") == true
                        || rdbChonAnhTuThuVien.isSelected() == true && IMGThuVien.getTag().toString().equals(R.drawable.ic_baseline_image_24 + "") == true ||
                        rdbChonAnhTuCamera.isSelected() == false &&  rdbChonAnhTuThuVien.isSelected() == false && ImgCamera.getTag().toString().equals(R.drawable.ic_baseline_camera_alt_24 + "") == true
                                && IMGThuVien.getTag().toString().equals(R.drawable.ic_baseline_image_24 + "") == true) {
                    Toast.makeText(context, "Bạn Chưa Chọn Ảnh", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    // Gọi Hàm Đổ Hình chụp từ camera ra màn hình
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null) {
            String idCamera = R.drawable.ic_baseline_camera_alt_24 + "";
            ImgCamera.setTag(idCamera);
            IMGThuVien.setTag("");
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ImgCamera.setImageBitmap(bitmap);

        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            String idThuVien = R.drawable.ic_baseline_image_24 + "";
            IMGThuVien.setTag(idThuVien);
            ImgCamera.setTag("");
            Uri imageUri = data.getData();
            IMGThuVien.setImageURI(imageUri);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setControll() {
        lvProducts = findViewById(R.id.TKTH_lisviewProducts);
        ImgCamera = findViewById(R.id.TKTHImageViewCameraAnhSanPhamCanThem);
        IMGThuVien = findViewById(R.id.TKTHImageViewChonAnhSanPhamCanThem);
        edtTenSP = findViewById(R.id.TKTHedittextTenSach);
        edtGiaSP = findViewById(R.id.TKTHeditextTacGia);
        edtTacGia = findViewById(R.id.TKTHeditextGiaSach);
        spnTheLoai = findViewById(R.id.TKTHspinnerLoaiSach);
        edtMota = findViewById(R.id.TKTHedittextMoTa);
        btnThem = findViewById(R.id.btnTKTHThem);
        btnSua = findViewById(R.id.btnTKTHSua);
        btnXoa = findViewById(R.id.btnTKTHXoa);
        rdbChonAnhTuCamera = findViewById(R.id.TKTHrdbChonAnhTuCamera);
        rdbChonAnhTuThuVien = findViewById(R.id.TKTHrdbChonAnhTuThuVien);

    }
}
