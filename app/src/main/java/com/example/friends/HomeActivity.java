package com.example.friends;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    public static int season;
    CardView cardview, cardview2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ArrayList<Integer> position = new ArrayList<>();

        ArrayList<Integer> covers = new ArrayList<>();
        covers.add(R.drawable.friends_season_1_bluray);
        covers.add(R.drawable.friends_season_2_bluray);
        covers.add(R.drawable.friends_season_3_bluray);
        covers.add(R.drawable.friends_season_4_bluray);
        covers.add(R.drawable.friends_season_5_bluray);
        covers.add(R.drawable.friends_season_6_bluray);
        covers.add(R.drawable.friends_season_7_bluray);
        covers.add(R.drawable.friends_season_8_bluray);
        covers.add(R.drawable.friends_season_9_bluray);
        covers.add(R.drawable.friends_season_10_bluray);

        int j=0;
        ScrollView scrollView = findViewById(R.id.scrollview);
        LinearLayout vertical_linearLayout = findViewById(R.id.verticalll);

        for(int i=0;i<5;i++)
        {
            cardview = new CardView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(dpToPx(20), dpToPx(20), dpToPx(20), dpToPx(20));

            cardview.setId(j);
            cardview.setOnClickListener(this);
            cardview.setLayoutParams(params);

            cardview.setRadius(dpToPx(8));
            cardview.setCardElevation(dpToPx(6));

            LinearLayout linearLayout1 = new LinearLayout(this);
            linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(dpToPx(120),dpToPx(170)));
            linearLayout1.setOrientation(LinearLayout.VERTICAL);
            linearLayout1.setBackground(getDrawable(covers.get(j)));
            j++;


            cardview.addView(linearLayout1);



            cardview2 = new CardView(this);
            cardview2.setLayoutParams(params);

            cardview2.setId(j);
            cardview2.setOnClickListener(this);
            cardview2.setRadius(dpToPx(8));
            cardview2.setCardElevation(dpToPx(6));



            LinearLayout linearLayoutl = new LinearLayout(this);
            linearLayoutl.setLayoutParams(new LinearLayout.LayoutParams(dpToPx(120),dpToPx(170)));
            linearLayoutl.setOrientation(LinearLayout.VERTICAL);
            linearLayoutl.setBackground(getDrawable(covers.get(j)));
            j++;
            cardview2.addView(linearLayoutl);


            LinearLayout linearLayout2 = new LinearLayout(this);
            linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout2.setPadding(dpToPx(10),dpToPx(10),dpToPx(10),dpToPx(10));
            linearLayout2.setGravity(Gravity.CENTER);

            linearLayout2.addView(cardview);
            linearLayout2.addView(cardview2);

            vertical_linearLayout.addView(linearLayout2);
        }

    }
    public int dpToPx(int dp) {
        float density = getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(HomeActivity.this, details.class);

        switch (v.getId()) {
            case 0:
                season = 1;
                startActivity(intent);
                break;

            case 2:
                season = 3;
                startActivity(intent);
                break;

            case 4:
                season = 5;
                startActivity(intent);
                break;

            case 6:
                season = 7;
                startActivity(intent);
                break;

            case 8:
                season = 9;
                startActivity(intent);
                break;

            case 1:
                season = 2;
                startActivity(intent);
                break;

            case 3:
                season = 4;
                startActivity(intent);
                break;

            case 5:
                season = 6;
                startActivity(intent);
                break;

            case 7:
                season = 8;
                startActivity(intent);
                break;

            case 9:
                season = 10;
                startActivity(intent);
                break;

        }
    }
}