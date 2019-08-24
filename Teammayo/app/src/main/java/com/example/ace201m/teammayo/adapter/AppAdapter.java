package com.example.ace201m.teammayo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ace201m.teammayo.R;
import com.example.ace201m.teammayo.dbhelper.AppReq;
import com.example.ace201m.teammayo.dbhelper.JobReq;

import java.util.ArrayList;

public class AppAdapter extends ArrayAdapter<AppReq> {
    public AppAdapter(@NonNull Context context, ArrayList<AppReq> data){
        super(context, 0, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_app_each,parent,false);
        }

        AppReq point = getItem(position);

        TextView v = (TextView)view.findViewById(R.id.app_id);
        v.setText(point.getAppId());
        v = (TextView)view.findViewById(R.id.app_emp);
        v.setText(point.getEmpId());
        v = (TextView)view.findViewById(R.id.app_jobid);
        v.setText(point.getJobId());
        v = (TextView)view.findViewById(R.id.app_status);
        v.setText(point.getStatus());

        return view;
    }
}
