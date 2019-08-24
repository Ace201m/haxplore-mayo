package com.example.ace201m.contractor.activity;

import android.accounts.Account;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.ace201m.contractor.R;
import com.example.ace201m.contractor.frags.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView bnv = findViewById(R.id.bottombar1);
        openFragment(MainFrag.newInstance());

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.option1:
                        openFragment(MainFrag.newInstance());
                        break;
                    case R.id.option2:
                        openFragment(LearnFrag.newInstance());
                        break;
                    case R.id.option3:
                        openFragment(AccFrag.newInstance());
                        break;
                }
                return true;
            }
        });
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_view, fragment);
        transaction.commit();
    }
}
