package com.ktds.jobs;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ktds.jobs.adapter.JobListAdapter;
import com.ktds.jobs.common.DataHandler;
import com.ktds.jobs.model.Job;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class HomeActivity extends AppCompatActivity {

    private SearchTask searchTask;
    private MainTask mainTask;

    private EditText et_keyword;
    private Button btn_search;
    private TextView tv_text;
    private Button btn_job_type;
    private Button btn_work_pattern;
    private Button btn_location;
    private Button btn_go_main;

    private List<Job> jobList;
    private ListView lv_content;
    private JobListAdapter adapter;
    private String url ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        et_keyword = (EditText) findViewById(R.id.et_keyword);
        btn_search = (Button) findViewById(R.id.btn_search);
        lv_content = (ListView) findViewById(R.id.lv_content);
        tv_text = (TextView) findViewById(R.id.tv_text);
        btn_go_main = (Button) findViewById(R.id.btn_go_main);
        btn_job_type = (Button) findViewById(R.id.btn_job_type);
        btn_work_pattern = (Button) findViewById(R.id.btn_work_pattern);
        btn_location = (Button) findViewById(R.id.btn_location);


        mainTask = new MainTask();
        mainTask.execute();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTask = new SearchTask();
                jobList = new ArrayList<>();

                searchTask.execute(et_keyword.getText().toString());

            }
        });
        btn_go_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        btn_job_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, JobTypeActivity.class);
                startActivity(intent);
            }
        });
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LocationTypeActivity.class);
                startActivity(intent);
            }
        });
        btn_work_pattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WorkPatternActivity.class);
                startActivity(intent);
            }
        });
    }

    class SearchTask extends AsyncTask<String, Void, HashMap<String,Object>> {

        @Override
        protected HashMap<String,Object> doInBackground(String... params) {
            HashMap<String,Object> job = new HashMap<>();

            url = "http://api.saramin.co.kr/job-search?keywords={"+ params[0] +"}";

            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = factory.newSAXParser();
                XMLReader reader = parser.getXMLReader();
                URL xmlUrl = new URL(url);
                DataHandler dataHandler = new DataHandler();
                reader.setContentHandler(dataHandler);
                reader.parse(new InputSource(xmlUrl.openStream()));

                jobList = dataHandler.getData();

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            job.put("jobList",jobList);
            job.put("keyword",params[0]);
            return job;
        }

        @Override
        protected void onPostExecute(HashMap<String,Object> job) {
            List<Job> jobs = (List<Job>) job.get("jobList");
            String keyword = (String) job.get("keyword");
            adapter = new JobListAdapter(HomeActivity.this,jobs);
            lv_content.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            tv_text.setText("'"+keyword+"' 의 검색결과");
        }
    }

    class MainTask extends AsyncTask<Void, Void, List<Job>> {

        @Override
        protected List<Job> doInBackground(Void... params) {

            url = "http://api.saramin.co.kr/job-search?keywords=&bbs_gb=0&loc_cd%5B%5D=&ind_cd%5B%5D=&job_category%5B%5D=&sort=pd&start=&count=";

            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = factory.newSAXParser();
                XMLReader reader = parser.getXMLReader();
                URL xmlUrl = new URL(url);
                DataHandler dataHandler = new DataHandler();
                reader.setContentHandler(dataHandler);
                reader.parse(new InputSource(xmlUrl.openStream()));

                jobList = dataHandler.getData();

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jobList;
        }

        @Override
        protected void onPostExecute(List<Job> jobs) {
            adapter = new JobListAdapter(HomeActivity.this,jobs);
            lv_content.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


}