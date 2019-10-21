package com.example.aps_irh.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.aps_irh.MainActivity;
import com.example.aps_irh.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Cadastro extends AppCompatActivity{
private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toast.makeText(this, "Cadastro", Toast.LENGTH_SHORT).show();
        final Context context = this.getBaseContext();
        tabs = findViewById(R.id.tabLayout);

        LayoutInflater.from(context).inflate(R.layout.blank_fragment, (FrameLayout)findViewById(R.id.frame1), true);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0) {
                    FrameLayout f = findViewById(R.id.frame1);
                    LayoutInflater.from(context).inflate(R.layout.blank_fragment, f, true);
                    Button b = findViewById(R.id.button2);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "AAAAAA", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    FrameLayout f = findViewById(R.id.frame1);
                    f.removeAllViews();
                    //LayoutInflater.from(context).inflate(R.layout.blank_fragment, f, true);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        tabs.getTabAt(0).select();
    }

}
