package com.example.book.QuanLy.adapter.User;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.book.QuanLy.manage.UpdateUserActivity;
import com.example.book.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<User> listUser;
    private int layout;

    public UserAdapter(Context context, ArrayList<User> listUser, int layout) {
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

    private static class ViewHolder{
        private TextView txtUserName;
        private ImageView btnUpdateUser;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Biến FireBase
        DatabaseReference dataUser = FirebaseDatabase.getInstance().getReference();

        ViewHolder viewHolder;
        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(layout,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.txtUserName = view.findViewById(R.id.txtUserNameItemLayout);
            viewHolder.btnUpdateUser = view.findViewById(R.id.btnUpdateUserItemLayout);

            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }
        User user = listUser.get(i);
        viewHolder.txtUserName.setText(user.getName());
        viewHolder.btnUpdateUser.setImageResource(R.drawable.pencil);


        viewHolder.btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUser.child("users").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if(snapshot.getValue(User.class).toString().equals(user.toString()))
                        {
                            Intent intent = new Intent(context, UpdateUserActivity.class);
                            intent.putExtra("DataUser",  user);
                            intent.putExtra("keyUser",snapshot.getKey());
                            context.startActivity(intent);
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
