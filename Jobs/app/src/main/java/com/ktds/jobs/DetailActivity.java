package com.ktds.jobs;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ktds.jobs.model.Job;


public class DetailActivity extends AppCompatActivity {

    private TextView tv_job_company;
    private TextView tv_job_title;
    private TextView tv_job_location;
    private TextView tv_job_type;
    private TextView tv_job_category;
    private TextView tv_job_level;
    private TextView tv_job_requiredlevel;
    private TextView tv_job_salary;

    private Button btn_apply;
    private Button btn_close;
    private Job job;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        tv_job_company = (TextView) findViewById(R.id.tv_job_company);
        tv_job_title = (TextView) findViewById(R.id.tv_job_title);
        tv_job_location = (TextView) findViewById(R.id.tv_job_location);
        tv_job_type = (TextView) findViewById(R.id.tv_job_type);
        tv_job_category = (TextView) findViewById(R.id.tv_job_category);
        tv_job_level = (TextView) findViewById(R.id.tv_job_level);
        tv_job_requiredlevel = (TextView) findViewById(R.id.tv_job_requiredlevel);
        tv_job_salary = (TextView) findViewById(R.id.tv_job_salary);
        btn_apply = (Button) findViewById(R.id.btn_apply);
        btn_close = (Button) findViewById(R.id.btn_close);

        job = (Job) intent.getSerializableExtra("job");
        String location = job.getLocation();
        location = location.replaceAll("&gt;",">");
        tv_job_company.setText(job.getCompany());
        tv_job_title.setText(job.getTitle());

        tv_job_location.setText(location);
        tv_job_type.setText(job.getJobType());
        tv_job_category.setText(job.getJobCategory());
        tv_job_level.setText(job.getLevel());
        tv_job_requiredlevel.setText(job.getRequiredLevel());
        tv_job_salary.setText(job.getSalary());

        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.saramin.co.kr/zf_user/jobs/view?rec_idx="+job.getId()+"#utm_source=job-search-api&utm_medium=api&utm_campaign=saramin-job-search-api";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
