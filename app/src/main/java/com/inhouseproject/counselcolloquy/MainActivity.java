package com.inhouseproject.counselcolloquy;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(onNav);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new fragement1()).commit();


    }

    private final BottomNavigationView.OnItemSelectedListener onNav = new BottomNavigationView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            Fragment selected = null;

            if (item.getItemId()== R.id.profile_bottom){
                selected = new fragement1();
            }
            else if (item.getItemId()==R.id.ask_bottom) {
                selected = new fragement2();

            }
            else if (item.getItemId()==R.id.request_bottom) {
                selected = new fragement3();

            }
            else if (item.getItemId()==R.id.home_bottom) {
                selected = new fragement4();

            }
            assert selected != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,selected).commit();
            return true;
        }
    };
}