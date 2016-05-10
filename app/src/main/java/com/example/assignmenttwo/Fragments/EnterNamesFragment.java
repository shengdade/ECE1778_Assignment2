package com.example.assignmenttwo.Fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignmenttwo.Activities.MainActivity;
import com.example.assignmenttwo.Classes.Person;
import com.example.assignmenttwo.R;

public class EnterNamesFragment extends Fragment {

    private View resultView;
    EditText textName;
    EditText textAge;

    public static final String[] movies = {
            "Star Wars: The Force Awakens",
            "Ride Along 2",
            "The Big Short",
            "The Revenant",
            "Monster Hunt (Mandarin w/e.s.t.)",
            "The Good Dinosaur",
            "Airlift (Hindi w/e.s.t.)",
            "Bridge Of Spies",
            "Brooklyn",
            "Daddy's Home",
            "Detective Chinatown",
            "Norm Of The North",
            "The 5th Wave",
            "The Danish Girl",
            "The Forest",
            "WWE Royal Rumble 2016",
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        resultView = inflater.inflate(R.layout.frag_enter_names, container, false);
        textName = (EditText) resultView.findViewById(R.id.editName);
        textAge = (EditText) resultView.findViewById(R.id.editAge);

        Spinner spin = (Spinner) resultView.findViewById(R.id.spinner);
        ArrayAdapter<String> apt = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, movies);
        apt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(apt);


        Button bt = (Button) resultView.findViewById(R.id.btnDone);
        bt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDonePressed(v);
                    }
                }
        );
        bt = (Button) resultView.findViewById(R.id.btnAdd);
        bt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onAddPressed(v);
                    }
                }
        );
        return (resultView);
    }

    private void onAddPressed(View v) {
        int age;
        //Enter name field
        String name = textName.getText().toString();
        if (name.equals("")) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(getActivity());
            dlgAlert.setMessage("Please Enter a Valid Name");
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
        //Enter age field
        try {
            age = Integer.parseInt(textAge.getText().toString());
        } catch (NumberFormatException e) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(getActivity());
            dlgAlert.setMessage("Please Enter a Valid Age");
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

        Spinner textMovie = (Spinner) resultView.findViewById(R.id.spinner);
        String movie = textMovie.getSelectedItem().toString();

        MainActivity.Friends.add(new Person(name, age, movie));
        MainActivity.FileModified = true;

        Toast.makeText(getActivity(), "Record saved", Toast.LENGTH_SHORT).show();

        textName.setText("");
        textAge.setText("");
        textName.requestFocus();
    }

    private void onDonePressed(View v) {
        if (textName.getText().toString().matches("") && textAge.getText().toString().matches("")) {
            getFragmentManager().popBackStackImmediate();
        } else {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(getActivity());
            dlgAlert.setMessage("Are you sure you want to exit without saving?");
            dlgAlert.setNegativeButton("No", null);
            dlgAlert.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            getFragmentManager().popBackStackImmediate();
                        }
                    });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
    }
}
