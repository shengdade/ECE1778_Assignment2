package com.example.assignmenttwo.Fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.assignmenttwo.Classes.FileAdapter;
import com.example.assignmenttwo.Activities.MainActivity;
import com.example.assignmenttwo.Classes.Person;
import com.example.assignmenttwo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LoadFragment extends Fragment
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView;
    private FileAdapter adp;
    private File fileDirectory;
    private File[] fileList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View resultView = inflater.inflate(R.layout.frag_load, container, false);

        fileDirectory = getActivity().getFilesDir();
        fileList = fileDirectory.listFiles();

        listView = (ListView) resultView.findViewById(R.id.listView2);
        adp = new FileAdapter(getActivity(), R.layout.frag_load_row, fileList);
        listView.setAdapter(adp);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        return (resultView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String filename = fileList[position].getName();
        try {
            FileInputStream fis = getActivity().openFileInput(filename);
            ObjectInputStream is = new ObjectInputStream(fis);
            MainActivity.Friends.clear();
            MainActivity.Friends = (ArrayList<Person>) is.readObject();
            is.close();
            fis.close();
            Toast.makeText(getActivity(), "File '" + filename + "' loaded", Toast.LENGTH_SHORT).show();
            MainActivity.FileModified = false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final int p = position;
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(getActivity());
        dlgAlert.setMessage("Do you want to remove this file?");
        dlgAlert.setNegativeButton("No", null);
        dlgAlert.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        removeFile(p);
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
        return true;
    }

    public void removeFile(int position) {
        try {
            File fileToDelete = fileList[position];
            boolean deleted = fileToDelete.delete();
            if (deleted) {
                fileList = fileDirectory.listFiles();
                adp = new FileAdapter(getActivity(), R.layout.frag_load_row, fileList);
                listView.setAdapter(adp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}