package com.example.book.SoanDon.adapter;import android.app.Activity;import android.content.Context;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ArrayAdapter;import android.widget.CheckBox;import android.widget.TextView;import android.widget.Toast;import androidx.annotation.NonNull;import androidx.annotation.Nullable;import com.example.book.R;import com.example.book.SoanDon.Object.Product_Cart;import com.example.book.SoanDon.models.Delivered;import com.example.book.SoanDon.models.User;import com.example.book.SoanDon.models.Voucher;import com.example.book.ThuKho.TKQuanLiSanPham.Product;import com.example.book.XuLyHD.DonHangChoXuLy.Bill;import com.google.firebase.database.ChildEventListener;import com.google.firebase.database.DataSnapshot;import com.google.firebase.database.DatabaseError;import com.google.firebase.database.DatabaseReference;import com.google.firebase.database.FirebaseDatabase;import java.util.ArrayList;import java.util.UUID;public class DeliveredAdapter extends ArrayAdapter {    Context context;    int layoutItemID;    ArrayList<Bill> delivereds;    public DeliveredAdapter(Context context, int resource, ArrayList<Bill> objects) {        super(context, resource, objects);        this.context = context;        this.layoutItemID = resource;        this.delivereds = objects;    }    @Override    public int getCount() {        return delivereds.size();    }    @Override    public View getView(int position, View convertView, ViewGroup parent) {        ViewHolder viewHolder = null;        //ConverView: luu lại thong tin những phần từ mỗi lần quấn lên hoặc xuống(tái sử dụng,ko cần find view id lại)        if (convertView == null) {            convertView = LayoutInflater.from(context).inflate(layoutItemID, parent, false);            viewHolder = new ViewHolder();            //get view from itemlayout            viewHolder.tenSach = convertView.findViewById(R.id.txtDelivered);            viewHolder.gia = convertView.findViewById(R.id.txtgiaDelivered);            viewHolder.chkDaNhanTien = convertView.findViewById(R.id.chkDaNhanTienItem);            //luu lại khi quấn để tái sử dụng            convertView.setTag(viewHolder);        } else {            //Tái sử dụng            viewHolder = (ViewHolder) convertView.getTag();        }        Bill delivered = delivereds.get(position);        viewHolder.tenSach.setText(delivered.getId());        viewHolder.gia.setText(delivered.getTotalMoney() + "");        ViewHolder finalViewHolder = viewHolder;        viewHolder.chkDaNhanTien.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();                mDatabase.child("bills").child(delivered.getId()).child("status").setValue(7);                // Cộng tiền cho user và tặng voucher khi đủ điểm                /* Hạng thành viên                 * 0 : Đồng                 * 1tr: Bạc                 * 5tr: Vàng                 * 10tr: Kim cương                 * */                /*                 * Tặng voucher:                 * Đơn hàng có giá trị:                 * 1tr: 10k                 * 2tr: 50k                 * 5tr: 500k                 * */                // tặng voucher:                int tongTienBill = delivered.getTotalMoney() - delivered.getDiscount();                if (tongTienBill >= 5000000) {                    Voucher voucher = new Voucher(UUID.randomUUID().toString(), 500000);                    mDatabase.child("vouchers").child(delivered.getId_user()).child(voucher.getId()).setValue(voucher);                } else if (tongTienBill >= 2000000) {                    Voucher voucher = new Voucher(UUID.randomUUID().toString(), 50000);                    mDatabase.child("vouchers").child(delivered.getId_user()).child(voucher.getId()).setValue(voucher);                } else if (tongTienBill >= 1000000) {                    Voucher voucher = new Voucher(UUID.randomUUID().toString(), 10000);                    mDatabase.child("vouchers").child(delivered.getId_user()).child(voucher.getId()).setValue(voucher);                }                // cong tiền user và tặng voucher:                mDatabase.child("users").addChildEventListener(new ChildEventListener() {                    @Override                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {                        if (snapshot.getKey().equals(delivered.getId_user())) {                            User user = snapshot.getValue(User.class);                            int tongTienMua = user.getMoneyBuy() + delivered.getTotalMoney() - delivered.getDiscount();                            // cọng tiền và hạng thành viên:                            DatabaseReference mDatabasetotal = FirebaseDatabase.getInstance().getReference();                            mDatabasetotal.child("users").child(delivered.getId_user()).child("moneyBuy").setValue(tongTienMua);                            if (tongTienMua < 1000000) {                                mDatabasetotal.child("users").child(delivered.getId_user()).child("rank").setValue("Đồng");                            } else if (tongTienMua < 5000000) {                                mDatabasetotal.child("users").child(delivered.getId_user()).child("rank").setValue("Bạc");                            } else if (tongTienMua < 10000000) {                                mDatabasetotal.child("users").child(delivered.getId_user()).child("rank").setValue("Kim cương");                            }                        }                    }                    @Override                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {                    }                    @Override                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {                    }                    @Override                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {                    }                    @Override                    public void onCancelled(@NonNull DatabaseError error) {                    }                });                Toast.makeText(getContext(), "Đã xử lý xong", Toast.LENGTH_SHORT).show();                // set lại sản phẩm đó là đã bán bao nhiêu:                mDatabase.child("bill_detail").child(delivered.getId()).addChildEventListener(new ChildEventListener() {                    @Override                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {                        Product_Cart productCart = snapshot.getValue(Product_Cart.class);                        String id__ = snapshot.getKey();                        DatabaseReference mData = FirebaseDatabase.getInstance().getReference("products");                        mData.addChildEventListener(new ChildEventListener() {                            @Override                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {                                Product product = snapshot.getValue(Product.class);                                if (product.getId().equals(id__)) {                                    int sold = productCart.getQuality() + product.getSold();                                    FirebaseDatabase.getInstance().getReference("products").child(product.getId())                                            .child("sold").setValue(sold);                                    int stock = product.getStock() - productCart.getQuality();                                    FirebaseDatabase.getInstance().getReference("products").child(product.getId())                                            .child("stock").setValue(stock);                                }                            }                            @Override                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {                            }                            @Override                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {                            }                            @Override                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {                            }                            @Override                            public void onCancelled(@NonNull DatabaseError error) {                            }                        });                    }                    @Override                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {                    }                    @Override                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {                    }                    @Override                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {                    }                    @Override                    public void onCancelled(@NonNull DatabaseError error) {                    }                });                CheckBox chk =(CheckBox) v;                chk.setChecked(false);            }        });        return convertView;    }    public static class ViewHolder {        TextView tenSach;        TextView gia;        CheckBox chkDaNhanTien;    }}