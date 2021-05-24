package com.example.friends;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static com.example.friends.HomeActivity.season;


public class episodes extends Fragment {

    public episodes() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_episodes, container, false);
        ImageView imageView = view.findViewById(R.id.image);

        ProgressBar progressBar = view.findViewById(R.id.progress_bar);

        String number = getArguments().getString("message");
        Log.d("number", number);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Season "+season);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child(String.valueOf(number)).child("Name").getValue(String.class);
                String intro = snapshot.child(String.valueOf(number)).child("Intro").getValue(String.class);
                String image = snapshot.child(String.valueOf(number)).child("Image").getValue(String.class);
                String subs = snapshot.child(String.valueOf(number)).child("Subs").getValue(String.class);
                String video_url = snapshot.child(String.valueOf(number)).child("Video").getValue(String.class);
                Picasso.get().load(image).into(imageView);
                Log.d("name123",name);
                TextView textView2 = view.findViewById(R.id.txtview);
                textView2.setText("Episode "+ number);
                TextView textView = view.findViewById(R.id.txtview2);
                textView.setText(name);
                TextView introd = view.findViewById(R.id.intro);
                introd.setText(intro);
                progressBar.setVisibility(View.GONE);
                FloatingActionButton video = view.findViewById(R.id.video);
                video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        intent.putExtra("url",video_url);
                        intent.putExtra("title",name);
                        intent.putExtra("number",number);
                        if(season<5) {
                            intent.putExtra("subs",subs);
                        }
                        startActivity(intent);

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return  view;
    }
}