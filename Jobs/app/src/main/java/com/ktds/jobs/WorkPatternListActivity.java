package com.ktds.jobs;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ktds.jobs.R;
import com.ktds.jobs.adapter.JobListAdapter;
import com.ktds.jobs.common.DataHandler;
import com.ktds.jobs.model.Job;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WorkPatternListActivity extends AppCompatActivity {

    private WorkPatternTask workPatternTask;
    private WorkPatternSearchTask workPatternSearchTask;

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

    private String workPattern="";
    private String param = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_pattern_list);

        et_keyword = (EditText) findViewById(R.id.et_keyword);
        btn_search = (Button) findViewById(R.id.btn_search);
        lv_content = (ListView) findViewById(R.id.lv_content);
        tv_text = (TextView) findViewById(R.id.tv_text);
        btn_go_main = (Button) findViewById(R.id.btn_go_main);
        btn_job_type = (Button) findViewById(R.id.btn_job_type);
        btn_work_pattern = (Button) findViewById(R.id.btn_work_pattern);
        btn_location = (Button) findViewById(R.id.btn_location);

        workPatternTask = new WorkPatternTask();

        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);
        param = Integer.toString(position);

        workPattern = intent.getStringExtra("workPattern");
        workPatternTask.execute(param);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workPatternSearchTask = new WorkPatternSearchTask();
                String keyword = et_keyword.getText().toString();
                workPatternSearchTask.execute(keyword,param);
            }
        });
        btn_go_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkPatternListActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        btn_job_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkPatternListActivity.this, JobTypeActivity.class);
                startActivity(intent);
            }
        });
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkPatternListActivity.this, LocationTypeActivity.class);
                startActivity(intent);
            }
        });
        btn_work_pattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkPatternListActivity.this, WorkPatternActivity.class);
                startActivity(intent);
            }
        });


    }
    class WorkPatternTask extends AsyncTask<String, Void, List<Job>> {

        @Override
        protected List<Job> doInBackground(String... params) {

            url = "http://api.saramin.co.kr/job-search?keywords=&job_type="+params[0]+"&loc_cd%5B%5D=&ind_cd%5B%5D=&job_category%5B%5D=&sort=&start=&count=";

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
            adapter = new JobListAdapter(WorkPatternListActivity.this,jobs);
            lv_content.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            tv_text.setText(workPattern);
        }
    }

    class WorkPatternSearchTask extends AsyncTask<String, Void, HashMap<String,Object>>{

        @Override
        protected HashMap<String,Object> doInBackground(String... params) {
            HashMap<String,Object> job = new HashMap<>();

            url = "http://api.saramin.co.kr/job-search?keywords="+params[0]+"&job_type="+params[1]+"&loc_cd%5B%5D=&ind_cd%5B%5D=&job_category%5B%5D=&sort=&start=&count=";

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
            String keyowrd = (String) job.get("keyword");
            adapter = new JobListAdapter(WorkPatternListActivity.this,jobs);
            lv_content.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            tv_text.setText("'"+keyowrd+"'의 검색 결과");
        }
    }
}
