package com.example.assignmenttwo.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.assignmenttwo.Fragments.MainFragment;
import com.example.assignmenttwo.Classes.Person;
import com.example.assignmenttwo.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Person> Friends;
    public static boolean FileModified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getFragmentManager();
            MainFragment fragment = new MainFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragMain, fragment);
            fragmentTransaction.commit();
        }

        Friends = new ArrayList<Person>();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }
}