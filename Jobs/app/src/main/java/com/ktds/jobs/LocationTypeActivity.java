package com.ktds.jobs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LocationTypeActivity extends AppCompatActivity {

    private GridView gv_location_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_type);

        gv_location_type = (GridView) findViewById(R.id.gv_location_type);
        List LocationTypeList = getLocationTypeList();
        gv_location_type.setAdapter(new ArrayAdapter<String>(LocationTypeActivity.this, 0, LocationTypeList){
            ItemHolder holder = null;
            @NonNull
            @Override
            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                if ( convertView == null ){
                    LayoutInflater inflater = LocationTypeActivity.this.getLayoutInflater();
                    convertView = inflater.inflate(R.layout.item_location_type,parent,false);

                    holder = new ItemHolder();
                    holder.tv_location_type = (TextView) convertView.findViewById(R.id.tv_location_type);
                    convertView.setTag(holder);
                }else {
                    holder = (ItemHolder) convertView.getTag();
                }
                String jobType = getItem(position);
                holder.tv_location_type.setText(jobType);

                holder.tv_location_type.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LocationTypeActivity.this,LocationTypeListActivity.class);
                        intent.putExtra("position",position+1);
                        intent.putExtra("locationType",getItem(position));
                        startActivity(intent);
                    }
                });

                return convertView;
            }
            class ItemHolder {
                public TextView tv_location_type;
            }
        });

    }

    public List<String> getLocationTypeList(){
        List<String> locationTypeList = new ArrayList<>();

        locationTypeList.add("서울");
        locationTypeList.add("경기");
        locationTypeList.add("광주");
        locationTypeList.add("대구");
        locationTypeList.add("대전");
        locationTypeList.add("부산");
        locationTypeList.add("울산");
        locationTypeList.add("인천");
        locationTypeList.add("강원");
        locationTypeList.add("경남");
        locationTypeList.add("경북");
        locationTypeList.add("전남");
        locationTypeList.add("전북");
        locationTypeList.add("충북");
        locationTypeList.add("충남");
        locationTypeList.add("제주");

        return locationTypeList;
    }
}
