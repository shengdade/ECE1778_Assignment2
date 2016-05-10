package com.example.assignmenttwo.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignmenttwo.Activities.MainActivity;
import com.example.assignmenttwo.R;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class StoreFragment extends Fragment {

    private EditText editFileName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View resultView = inflater.inflate(R.layout.frag_store, container, false);

        editFileName = (EditText) resultView.findViewById(R.id.editFileName);

        Button bt = (Button) resultView.findViewById(R.id.btnCancel);
        bt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCancalPressed(v);
                    }
                }
        );
        bt = (Button) resultView.findViewById(R.id.btnOK);
        bt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOKPressed(v);
                    }
                }
        );
        return (resultView);
    }

    private void onOKPressed(View v) {
        //Hint if it is not a valid file name
        if (editFileName.getText().toString().equals("")) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(getActivity());
            dlgAlert.setMessage("Please Enter a Valid File Name");
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            return;
        }
        //Hint if the data is empty
        if (MainActivity.Friends.isEmpty()) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(getActivity());
            dlgAlert.setMessage("Data record is empty, are you sure to save?");
            dlgAlert.setNegativeButton("No", null);
            dlgAlert.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            saveToFile();
                        }
                    });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        } else {
            saveToFile();
        }
    }

    private void saveToFile() {
        try {
            String filename = editFileName.getText().toString();
            FileOutputStream fos = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(MainActivity.Friends);
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(getActivity(), "File stored", Toast.LENGTH_SHORT).show();
        editFileName.setText("");
        MainActivity.FileModified = false;
    }

    private void onCancalPressed(View v) {
        getFragmentManager().popBackStackImmediate();
    }
}