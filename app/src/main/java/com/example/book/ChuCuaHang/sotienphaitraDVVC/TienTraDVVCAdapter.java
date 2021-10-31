package com.example.book.ChuCuaHang.sotienphaitraDVVC;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.book.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class TienTraDVVCAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private ArrayList<TienTraDVVC> listTienTraDVVC;

    public TienTraDVVCAdapter(Context context, int layout, ArrayList<TienTraDVVC> listTienTraDVVC) {
        this.layout = layout;
        this.context = context;
        this.listTienTraDVVC = listTienTraDVVC;
    }

    @Override
    public int getCount() {
        return listTienTraDVVC.size();
    }

    @Override
    public Object getItem(int position) {
        return listTienTraDVVC.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView tvMaDH, tvTriGia, tvSoTienPhaiTra;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Định dạng số
        NumberFormat currentLocale = NumberFormat.getInstance();
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvMaDH = convertView.findViewById(R.id.adapterSTPTDVVCtvMaDH);
            viewHolder.tvTriGia = convertView.findViewById(R.id.adapterSTPTDVVCtvTriGia);
            viewHolder.tvSoTienPhaiTra = convertView.findViewById(R.id.adapterSTPTDVVCtvSoTienPhaiTra);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvMaDH.setText(listTienTraDVVC.get(position).getMaDH());
        viewHolder.tvTriGia.setText(en.format(listTienTraDVVC.get(position).getTriGia()) + " VNĐ");
        viewHolder.tvSoTienPhaiTra.setText(en.format(listTienTraDVVC.get(position).getSoTienPhaiTra()) + " VNĐ");

        return convertView;
    }
}
