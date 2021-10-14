package com.example.book.SoanDon.adapter;import android.app.Activity;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ArrayAdapter;import android.widget.TextView;import androidx.annotation.NonNull;import com.example.book.R;import com.example.book.SoanDon.models.Delivered;import java.util.ArrayList;public class DeliveredAdapter extends ArrayAdapter<Delivered> {    private Activity context;    private int layoutItemID;    private ArrayList<Delivered> delivereds;    public DeliveredAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<Delivered> objects) {        super(context, resource, objects);        this.context = context;        layoutItemID = resource;        delivereds = objects;    }    @Override    public View getView(int position, View convertView, ViewGroup parent) {        ViewHolder viewHolder = null;        //ConverView: luu lại thong tin những phần từ mỗi lần quấn lên hoặc xuống(tái sử dụng,ko cần find view id lại)        if(convertView == null){            LayoutInflater inflater = context.getLayoutInflater();            convertView = inflater.inflate(layoutItemID,parent,false);            viewHolder =new ViewHolder();            //get view from itemlayout            viewHolder.tenSach = convertView.findViewById(R.id.txtDelivered);            viewHolder.gia = convertView.findViewById(R.id.txtgiaDelivered);            //luu lại khi quấn để tái sử dụng            convertView.setTag(viewHolder);        }        else {            //Tái sử dụng            viewHolder = (ViewHolder) convertView.getTag();        }        Delivered delivered = delivereds.get(position);        viewHolder.tenSach.setText(delivered.getTen());        viewHolder.gia.setText(delivered.getGia());        return convertView;    }    public static class ViewHolder{        TextView tenSach;        TextView gia;    }}