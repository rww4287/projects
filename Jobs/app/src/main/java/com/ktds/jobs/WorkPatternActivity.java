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
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WorkPatternActivity extends AppCompatActivity {

    private GridView gv_work_pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_pattern);

        gv_work_pattern = (GridView) findViewById(R.id.gv_work_pattern);
        List<String> workPartternList = getWorkPatternList();

        gv_work_pattern.setAdapter(new ArrayAdapter<String>(WorkPatternActivity.this, 0, workPartternList){
            ItemHolder holder = null;
            @NonNull
            @Override
            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                if ( convertView == null ){
                    LayoutInflater inflater = WorkPatternActivity.this.getLayoutInflater();
                    convertView = inflater.inflate(R.layout.item_work_pattern,parent,false);

                    holder = new ItemHolder();
                    holder.tv_work_pattern = (TextView) convertView.findViewById(R.id.tv_work_pattern);
                    convertView.setTag(holder);
                }else {
                    holder = (ItemHolder) convertView.getTag();
                }
                String jobType = getItem(position);
                holder.tv_work_pattern.setText(jobType);

                holder.tv_work_pattern.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(WorkPatternActivity.this,WorkPatternListActivity.class);
                        intent.putExtra("position",position+1);
                        intent.putExtra("workPattern",getItem(position));
                        startActivity(intent);
                    }
                });

                return convertView;
            }
            class ItemHolder {
                public TextView tv_work_pattern;
            }
        });
    }


    public List<String> getWorkPatternList(){
        List<String> workPatternList = new ArrayList<>();

        workPatternList.add("정규직");
        workPatternList.add("계약직");
        workPatternList.add("병역특례");
        workPatternList.add("인턴직");
        workPatternList.add("시간제");
        workPatternList.add("파견직");
        workPatternList.add("해외취업");
        workPatternList.add("위촉직");
        workPatternList.add("프리랜서");

        return workPatternList;
    }
}
