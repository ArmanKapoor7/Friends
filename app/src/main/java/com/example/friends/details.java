package com.example.friends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import static com.example.friends.HomeActivity.season;

public class details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ViewPager viewPager;
        FragmentCollectionAdapter fragmentCollectionAdapter;
        TabLayout tabLayout;

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);
        ImageButton imageButton = findViewById(R.id.button);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fragmentCollectionAdapter = new FragmentCollectionAdapter(getSupportFragmentManager());

        fragmentCollectionAdapter.total_episodes.add(0,24);
        fragmentCollectionAdapter.total_episodes.add(1,24);
        fragmentCollectionAdapter.total_episodes.add(2,24);
        fragmentCollectionAdapter.total_episodes.add(3,25);
        fragmentCollectionAdapter.total_episodes.add(4,24);
        fragmentCollectionAdapter.total_episodes.add(5,24);
        fragmentCollectionAdapter.total_episodes.add(6,25);
        fragmentCollectionAdapter.total_episodes.add(7,24);
        fragmentCollectionAdapter.total_episodes.add(8,24);
        fragmentCollectionAdapter.total_episodes.add(9,23);
        fragmentCollectionAdapter.total_episodes.add(10,17);

        String[] seasons = getResources().getStringArray(R.array.seasons);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.dropdown_item, seasons);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.dropdown_menu);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setText(arrayAdapter.getItem(season-1).toString(), false);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    season = 1;
                    viewPager.setAdapter(fragmentCollectionAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
                if(position==1){
                    season = 2;
                    viewPager.setAdapter(fragmentCollectionAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
                if(position==2){
                    season = 3;
                    viewPager.setAdapter(fragmentCollectionAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
                if(position==3){
                    season = 4;
                    viewPager.setAdapter(fragmentCollectionAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
                if(position==4){
                    season = 5;
                    viewPager.setAdapter(fragmentCollectionAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
                if(position==5){
                    season = 6;
                    viewPager.setAdapter(fragmentCollectionAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
                if(position==6){
                    season = 7;
                    viewPager.setAdapter(fragmentCollectionAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
                if(position==7){
                    season = 8;
                    viewPager.setAdapter(fragmentCollectionAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
                if(position==8){
                    season = 9;
                    viewPager.setAdapter(fragmentCollectionAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
                if(position==9){
                    season = 10;
                    viewPager.setAdapter(fragmentCollectionAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }

            }
        });

        viewPager.setAdapter(fragmentCollectionAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}