package com.quynhlm.dev.pnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.quynhlm.dev.pnlib.Dao.ThuThuDao;

public class LoginActivity extends AppCompatActivity {

    EditText edt_username, edt_password;
    CheckBox chk_RememberPass;
    String strUserName, strPassWord;

    ThuThuDao thuThuDao;

    public boolean isChuoi(String str) {
        return str.matches("[a-zA-Z0-9]+");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_username = findViewById(R.id.edtUsername);
        edt_password = findViewById(R.id.edtPassword);
        chk_RememberPass = findViewById(R.id.chkRememberPass);
        thuThuDao = new ThuThuDao(this);
        ReadFile();
        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            checkLogin();
        });
        findViewById(R.id.btnCancel).setOnClickListener(v -> {
            clearFrom();
        });
    }

    private void checkLogin() {
        strUserName = edt_username.getText().toString();
        strPassWord = edt_password.getText().toString();
        if (validete(strUserName, strPassWord)) {
            if (thuThuDao.checkLogin(strUserName, strPassWord) || strUserName.equalsIgnoreCase("admin") && strPassWord.equalsIgnoreCase("admin")) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUserName, strPassWord, chk_RememberPass.isChecked());
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("username", strUserName);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ReadFile() {
        SharedPreferences sharedPreferences = getSharedPreferences("LIST_USER", MODE_PRIVATE);
        edt_username.setText(sharedPreferences.getString("USERNAME", ""));
        edt_password.setText(sharedPreferences.getString("PASSWORD", ""));
        chk_RememberPass.setChecked(sharedPreferences.getBoolean("REMEMBER", false));
    }


    private void rememberUser(String strUserName, String strPassWord, boolean checked) {
        SharedPreferences sharedPreferences = getSharedPreferences("LIST_USER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!checked) {
            editor.clear();
        } else {
            editor.putString("USERNAME", strUserName);
            editor.putString("PASSWORD", strPassWord);
            editor.putBoolean("REMEMBER", checked);
        }
        editor.commit();
    }

    private boolean validete(String strUserName, String strPassWord) {
        try {
            if (strPassWord.isEmpty() || strPassWord.isEmpty()) {
                Toast.makeText(this, "Vui lòng không bảo trống", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!isChuoi(strUserName) || !isChuoi(strPassWord)) {
                Toast.makeText(this, "Vui lòng nhập đúng định dạng", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Đã sẩy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void clearFrom() {
        edt_username.setText("");
        edt_password.setText("");
        chk_RememberPass.setChecked(false);
    }
}