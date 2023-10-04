package com.quynhlm.dev.pnlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.quynhlm.dev.pnlib.Fragment.ChangePassFragment;
import com.quynhlm.dev.pnlib.Fragment.QlPhieuMuonFragment;
import com.quynhlm.dev.pnlib.Fragment.QlSachFragment;
import com.quynhlm.dev.pnlib.Fragment.QlThanhVienFragment;
import com.quynhlm.dev.pnlib.Fragment.QlTheLoaiFragment;
import com.quynhlm.dev.pnlib.Fragment.Ql_TkDoanhThuFragment;
import com.quynhlm.dev.pnlib.Fragment.Ql_TkTop10Fragment;
import com.quynhlm.dev.pnlib.Fragment.ThemThanhVienFragment;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    TextView txt_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.Toolbar_Main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thư Viện Phương Nam");
        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.NavigationView);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QlPhieuMuonFragment()).commit();
        Intent intent = getIntent();
        String user = intent.getStringExtra("username");
        txt_user = navigationView.getHeaderView(0).findViewById(R.id.txt_HeaderTextView);
        txt_user.setText("WelCome " + user);

        if (user != null) {
            if (!user.equalsIgnoreCase("admin")) {
                navigationView.getMenu().findItem(R.id.menu_them_nguoi_dung).setVisible(false);
            }
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = item.getItemId();
                Fragment fragment = null;
                String title = "";
                if (position == R.id.menu_ql_phieu_muon) {
                    fragment = new QlPhieuMuonFragment();
                    title = "Quản lý phiếu mượn";
                } else if (position == R.id.menu_ql_loaiSach) {
                    fragment = new QlTheLoaiFragment();
                    title = "Quản lý thể loại";
                } else if (position == R.id.menu_ql_Sach) {
                    fragment = new QlSachFragment();
                    title = "Quản lý sách";
                } else if (position == R.id.menu_ql_thanhVien) {
                    fragment = new QlThanhVienFragment();
                    title = "Quản lý nhân viên";
                } else if (position == R.id.menu_tk_top10) {
                    fragment = new Ql_TkTop10Fragment();
                    title = "Top 10 sách bán chạy";
                } else if (position == R.id.menu_tk_DoanhThu) {
                    fragment = new Ql_TkDoanhThuFragment();
                    title = "Thống kê doanh số";
                } else if (position == R.id.menu_them_nguoi_dung) {
                    fragment = new ThemThanhVienFragment();
                    title = "Thêm người dùng";
                } else if (position == R.id.menu_doi_mat_khau) {
                    fragment = new ChangePassFragment();
                    title = "Đổi mật khẩu";
                } else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
                drawerLayout.close();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                getSupportActionBar().setTitle(title);
                return true;
            }
        });
    }
}