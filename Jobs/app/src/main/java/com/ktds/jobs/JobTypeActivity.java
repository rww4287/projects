package com.ktds.jobs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.ktds.jobs.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JobTypeActivity extends AppCompatActivity {

    private GridView gv_job_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_type);

        gv_job_type = (GridView) findViewById(R.id.gv_job_type);
        List<String> jobTypeList = getJobTypeList();

        gv_job_type.setAdapter(new ArrayAdapter<String>(JobTypeActivity.this, 0, jobTypeList){
            ItemHolder holder = null;
            @NonNull
            @Override
            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                if ( convertView == null ){
                    LayoutInflater inflater = JobTypeActivity.this.getLayoutInflater();
                    convertView = inflater.inflate(R.layout.item_job_type,parent,false);

                    holder = new ItemHolder();
                    holder.tv_job_type = (TextView) convertView.findViewById(R.id.tv_job_type);
                    convertView.setTag(holder);
                }else {
                    holder = (ItemHolder) convertView.getTag();
                }
                String jobType = getItem(position);
                holder.tv_job_type.setText(jobType);

                holder.tv_job_type.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(JobTypeActivity.this,JobTypeListActivity.class);
                        intent.putExtra("position",position+1);
                        intent.putExtra("jobType",getItem(position));
                        startActivity(intent);
                    }
                });

                return convertView;
            }
            class ItemHolder {
                public TextView tv_job_type;
            }
        });
    }

    public List<String> getJobTypeList(){
        List<String> jobTypeList = new ArrayList<>();

        jobTypeList.add("경영·사무");
        jobTypeList.add("영업·고객상담");
        jobTypeList.add("생산·제조");
        jobTypeList.add("IT·인터넷");
        jobTypeList.add("전문직");
        jobTypeList.add("교육");
        jobTypeList.add("미디어");
        jobTypeList.add("특수계층·공공");
        jobTypeList.add("건설");

        return jobTypeList;
    }
}
