package com.quynhlm.dev.pnlib.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.quynhlm.dev.pnlib.Dao.ThuThuDao;
import com.quynhlm.dev.pnlib.Model.ThuThu;
import com.quynhlm.dev.pnlib.R;


public class ThemThanhVienFragment extends Fragment {
    View view;

    EditText edt_username, edt_hoTen, edt_password;

    ThuThuDao thuThuDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_them_thanh_vien, container, false);
        edt_username = view.findViewById(R.id.edt_username_add);
        edt_password = view.findViewById(R.id.edtPassword_Add);
        edt_hoTen = view.findViewById(R.id.edt_hoTen_add);
        thuThuDao = new ThuThuDao(getContext());

        view.findViewById(R.id.btnSave_addTT).setOnClickListener(v -> {
            String username = edt_username.getText().toString().trim();
            String password = edt_password.getText().toString().trim();
            String hoTen = edt_hoTen.getText().toString().trim();
            if (validate(username, password, hoTen)) {
                ThuThu thuThu = new ThuThu(username, hoTen, password);
                if (thuThuDao.insertData(thuThu)) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    resetFrom();
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.findViewById(R.id.btnCancle_addTT).setOnClickListener(v -> {
            resetFrom();
        });
        return view;
    }

    private void resetFrom() {
        edt_username.setText("");
        edt_password.setText("");
        edt_hoTen.setText("");
    }

    private boolean validate(String username, String password, String hoTen) {
        try {
            if (username.isEmpty() || password.isEmpty() || hoTen.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Nhập sai định dạng", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}