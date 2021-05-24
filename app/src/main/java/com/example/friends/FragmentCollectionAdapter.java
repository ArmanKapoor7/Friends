package com.example.friends;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.friends.HomeActivity.season;

public class FragmentCollectionAdapter extends FragmentStatePagerAdapter {

    ArrayList<Integer> total_episodes = new ArrayList<>();
    public FragmentCollectionAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        episodes episodes = new episodes();
        Bundle bundle = new Bundle();
        bundle.putString("message", String.valueOf(position+1));
        episodes.setArguments(bundle);

        return episodes;
    }

    @Override
    public int getCount() {
        return total_episodes.get(season);
    }


    @Nullable
    @Override
    public String getPageTitle(int position) {
        String title = String.valueOf(position+1);
        return season+"x"+title;
    }
}

