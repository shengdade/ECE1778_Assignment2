package com.example.assignmenttwo.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import com.example.assignmenttwo.Activities.MainActivity;
import com.example.assignmenttwo.R;
import com.example.assignmenttwo.Activities.ViewActivity;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.frag_main, container, false);

        OnClickListener btnListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(view);
            }
        };

        result.findViewById(R.id.btnEnterNames).setOnClickListener(btnListener);
        result.findViewById(R.id.btnView).setOnClickListener(btnListener);
        result.findViewById(R.id.btnStore).setOnClickListener(btnListener);
        result.findViewById(R.id.btnLoad).setOnClickListener(btnListener);
        result.findViewById(R.id.btnExit).setOnClickListener(btnListener);

        return (result);
    }

    private void onButtonPressed(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (view.getId()) {
            case R.id.btnEnterNames:
                EnterNamesFragment newFragEnter = new EnterNamesFragment();
                //getFragmentManager().beginTransaction().replace(R.id.fragMain, newFrag).commit();
                FragmentTransaction ft1 = fragmentManager.beginTransaction();
                ft1.replace(R.id.fragMain, newFragEnter);
                ft1.addToBackStack(null);
                ft1.commit();
                break;
            case R.id.btnView:
                getActivity().startActivity(new Intent(this.getActivity(), ViewActivity.class));
                break;
            case R.id.btnStore:
                StoreFragment newFragStore = new StoreFragment();
                FragmentTransaction ft2 = fragmentManager.beginTransaction();
                ft2.replace(R.id.fragMain, newFragStore);
                ft2.addToBackStack(null);
                ft2.commit();
                break;
            case R.id.btnLoad:
                LoadFragment newFragLoad = new LoadFragment();
                FragmentTransaction ft3 = fragmentManager.beginTransaction();
                ft3.replace(R.id.fragMain, newFragLoad);
                ft3.addToBackStack(null);
                ft3.commit();
                break;
            case R.id.btnExit:
                if (MainActivity.FileModified) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(getActivity());
                    dlgAlert.setMessage("Data records has been modified. Do you want to save them?");
                    dlgAlert.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().finish();
                                }
                            });
                    dlgAlert.setNegativeButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    FragmentManager fragmentManager = getFragmentManager();
                                    StoreFragment newFragStore = new StoreFragment();
                                    FragmentTransaction ft2 = fragmentManager.beginTransaction();
                                    ft2.replace(R.id.fragMain, newFragStore);
                                    ft2.addToBackStack(null);
                                    ft2.commit();
                                }
                            });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                } else {
                    getActivity().finish();
                }

                break;
        }
    }
}
