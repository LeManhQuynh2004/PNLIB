package com.quynhlm.dev.pnlib.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quynhlm.dev.pnlib.Model.TheLoai;
import com.quynhlm.dev.pnlib.R;

import java.util.ArrayList;

public class TheLoaiSpinner extends BaseAdapter {
    Context context;
    ArrayList<TheLoai> list;

    public TheLoaiSpinner(Context context, ArrayList<TheLoai> list) {
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

    class TheLoaiViewHolder {
        TextView txt_maLoai, txt_tenLoai;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TheLoaiViewHolder theLoaiViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_theloai_spinner, parent, false);
            theLoaiViewHolder = new TheLoaiViewHolder();
            if (theLoaiViewHolder != null) {
                theLoaiViewHolder.txt_maLoai = convertView.findViewById(R.id.txt_MaLoai_Spinner);
                theLoaiViewHolder.txt_tenLoai = convertView.findViewById(R.id.txt_tenLoai_Spinner);
            }
            convertView.setTag(theLoaiViewHolder);
        } else {
            theLoaiViewHolder = (TheLoaiViewHolder) convertView.getTag();
        }
        TheLoai theLoai = list.get(position);
        if (theLoai != null) {
            theLoaiViewHolder.txt_maLoai.setText(list.get(position).getMaLoai() + " | ");
            theLoaiViewHolder.txt_tenLoai.setText(list.get(position).getTenLoai());
        }
        return convertView;
    }
}
