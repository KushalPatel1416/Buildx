package com.example.buildx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.buildx.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding;

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Home_frag());

        BottomNavigationView btnv = findViewById(R.id.bottomNavigationView);
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        replaceFragment(new Home_frag());
                        break;
                    case R.id.Engineer:
                        replaceFragment(new Engineer_frag());
                        break;
                    case R.id.suggest:
                        replaceFragment(new Suggestion_frag());
                        break;
                    case R.id.profile:
                        replaceFragment(new Profile_frag());
                        break;
                }

                return true;
            }
        });

    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager frag = getSupportFragmentManager();
        FragmentTransaction fragt = frag.beginTransaction();
        fragt.replace(R.id.frameLayout, fragment);
        fragt.commit();
    }

    //BackPressed fun..
    @Override
    public void onBackPressed() {
        finish();
    }
}