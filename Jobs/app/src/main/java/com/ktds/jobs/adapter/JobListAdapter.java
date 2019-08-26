package com.ktds.jobs.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ktds.jobs.DetailActivity;
import com.ktds.jobs.R;
import com.ktds.jobs.model.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2017-07-18.
 */

public class JobListAdapter extends BaseAdapter {
    private List<Job> jobList;
    private Context context;

    public JobListAdapter(Context context, List<Job> jobList)
    {
        this.context = context;
        this.jobList = jobList;
    }
    @Override
    public int getCount() {
        return jobList.size();
    }

    @Override
    public Object getItem(int position) {
        return jobList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final Job data = jobList.get(position);
        LayoutInflater inflater;

        if(view == null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_job,parent,false);
        }

        if(data != null){
            TextView tv_company = (TextView)view.findViewById(R.id.tv_company);
            TextView tv_title = (TextView)view.findViewById(R.id.tv_title);


            tv_company.setText(data.getCompany());
            tv_title.setText(data.getTitle());

            tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("job",data);
                    context.startActivity(intent);
                }
            });


        }


        return view;
    }
}