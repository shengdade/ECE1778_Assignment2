package com.example.assignmenttwo.Classes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.assignmenttwo.R;

import java.util.ArrayList;

public class PersonAdapter extends ArrayAdapter<Person> {

    Context context;
    int layoutResourceId;
    ArrayList<Person> data = null;

    public PersonAdapter(Context context, int layoutResourceId, ArrayList<Person> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PersonHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((AppCompatActivity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new PersonHolder();
            holder.txtName = (TextView) row.findViewById(R.id.labelName);
            holder.txtAge = (TextView) row.findViewById(R.id.labelAge);
            holder.txtMovie = (TextView) row.findViewById(R.id.labelMovie);
            row.setTag(holder);
        } else {
            holder = (PersonHolder) row.getTag();
        }

        Person person = (Person) data.toArray()[position];
        holder.txtName.setText(person.name);
        holder.txtAge.setText(String.valueOf(person.age));
        holder.txtMovie.setText(person.movie);

        return (row);
    }

    static class PersonHolder {
        TextView txtName;
        TextView txtAge;
        TextView txtMovie;
    }
}
