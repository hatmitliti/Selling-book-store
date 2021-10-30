package com.example.book.QuanLy.adapter.BlackList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.book.QuanLy.adapter.User.User;
import com.example.book.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BlackListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<User> listUser;
    private int layout;


    public BlackListAdapter(Context context, ArrayList<User> listUser, int layout) {
        this.context = context;
        this.listUser = listUser;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int i) {
        return listUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private static class ViewHolder {
        private TextView txtUserName;
        private ImageView btnRemoveblacklist;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Biến FireBase
        DatabaseReference dataUser = FirebaseDatabase.getInstance().getReference();

        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(layout, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.txtUserName = view.findViewById(R.id.txtUsernameListBlacklist);
            viewHolder.btnRemoveblacklist = view.findViewById(R.id.imgUsernameListBlacklist);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        User user = listUser.get(i);
        viewHolder.txtUserName.setText(user.getName());

        viewHolder.btnRemoveblacklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUser.child("users").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.getValue(User.class).toString().equals(user.toString())) {
                            dataUser.child("blacklist").child(snapshot.getKey()).removeValue();
                            listUser.remove(user);
                            BlackListAdapter.this.notifyDataSetChanged();
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
        });
        return view;

    }
}
