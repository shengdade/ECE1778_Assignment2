package com.example.assignmenttwo.Classes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmenttwo.Activities.ViewActivity;
import com.example.assignmenttwo.R;

import java.io.File;
import java.util.Date;

public class FileAdapter extends ArrayAdapter<File> {

    Context context;
    int layoutResourceId;
    File data[] = null;

    public FileAdapter(Context context, int layoutResourceId, File[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FileHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((AppCompatActivity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new FileHolder();
            holder.txtFileName = (TextView) row.findViewById(R.id.labelFileName);
            holder.txtModifiedTime = (TextView) row.findViewById(R.id.labelModifiedTime);
            holder.btnDelete = (ImageButton) row.findViewById(R.id.btnDelete);
            row.setTag(holder);
        } else {
            holder = (FileHolder) row.getTag();
        }

        File file = data[position];
        holder.txtFileName.setText(file.getName());
        Date lastModifiedTime = new Date(file.lastModified());
        holder.txtModifiedTime.setText(lastModifiedTime.toString());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Long press an item to delete it", Toast.LENGTH_SHORT).show();
            }
        });

        return (row);
    }

    static class FileHolder {
        TextView txtFileName;
        TextView txtModifiedTime;
        ImageButton btnDelete;
    }
}
