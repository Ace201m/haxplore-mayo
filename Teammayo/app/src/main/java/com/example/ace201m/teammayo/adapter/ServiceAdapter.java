package com.example.ace201m.teammayo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ace201m.teammayo.R;
import com.example.ace201m.teammayo.dbhelper.ServiceReq;

import java.util.ArrayList;

public class ServiceAdapter extends ArrayAdapter<ServiceReq>{

    private ArrayList<ServiceReq> d;

    public ServiceAdapter(@NonNull Context context, ArrayList<ServiceReq> data){
        super(context, 0, data);
        d = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_learn_each,parent,false);
        }

        ServiceReq point = d.get(position);


        TextView v = (TextView)view.findViewById(R.id.contact_n);
        v.setText(point.getContPhone());
        v = (TextView)view.findViewById(R.id.learn_city);
        v.setText(point.getCity());
        v = (TextView)view.findViewById(R.id.learn_title);
        v.setText(point.getTitle());
        v = (TextView)view.findViewById(R.id.learn_body);
        v.setText(point.getBody());
        Log.i("DEBUGG", "dfdf" + point.getBody());

        return view;
    }
}
