package com.example.ace201m.contractor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ace201m.contractor.R;
import com.example.ace201m.contractor.dbhelper.JobReq;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class JobAdapter extends ArrayAdapter<JobReq>{

    public JobAdapter(@NonNull Context context, ArrayList<JobReq> data){
        super(context, 0, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_job_each,parent,false);
        }

        JobReq point = getItem(position);

        TextView v = (TextView)view.findViewById(R.id.job_address);
        v.setText(point.getAddress());
        v = (TextView)view.findViewById(R.id.job_city);
        v.setText(point.getCity());
        v = (TextView)view.findViewById(R.id.job_need);
        v.setText(point.getNeed());
        v = (TextView)view.findViewById(R.id.job_state);
        v.setText(point.getState());
        v = (TextView)view.findViewById(R.id.job_status);
        v.setText(point.getStatus());
        v = (TextView)view.findViewById(R.id.job_title);
        v.setText(point.getTitle());

        return view;
    }
}
