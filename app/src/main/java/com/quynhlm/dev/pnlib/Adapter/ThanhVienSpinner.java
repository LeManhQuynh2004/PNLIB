package com.quynhlm.dev.pnlib.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quynhlm.dev.pnlib.Model.ThanhVien;
import com.quynhlm.dev.pnlib.R;

import java.util.ArrayList;

public class ThanhVienSpinner extends BaseAdapter {

    Context context;

    ArrayList<ThanhVien> list;

    public ThanhVienSpinner(Context context, ArrayList<ThanhVien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ThanhVienViewHolder {
        TextView txt_maThanhVien, txt_tenThanhVien;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ThanhVienViewHolder thanhVienViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_thanhvien_spinner, parent, false);
            thanhVienViewHolder = new ThanhVienViewHolder();
            thanhVienViewHolder.txt_maThanhVien = convertView.findViewById(R.id.txt_MaTV_Spinner);
            thanhVienViewHolder.txt_tenThanhVien = convertView.findViewById(R.id.txt_hoTen_Spinner);
            convertView.setTag(thanhVienViewHolder);
        } else {
            thanhVienViewHolder = (ThanhVienViewHolder) convertView.getTag();
        }
        ThanhVien thanhVien = list.get(position);
        thanhVienViewHolder.txt_tenThanhVien.setText(thanhVien.getHoTen());
        thanhVienViewHolder.txt_maThanhVien.setText(String.valueOf(thanhVien.getMaTV()));
        return convertView;
    }
}
