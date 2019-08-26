package com.ktds.jobs.common;

import android.content.Intent;

import com.ktds.jobs.model.Job;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;


/**
 * Created by Admin on 2017-07-18.
 */

public class DataHandler extends DefaultHandler {
    private String elementValue = null;
    private Boolean elementOn = false;
    private ArrayList<Job> jobList = new ArrayList<Job>();
    private Job data = null;

    public ArrayList<Job> getData()
    {
        return jobList;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        elementOn = true;
        if(localName.equals("job")){
            data = new Job();
        }
    }

    /**
     * This will be called when the tags of the XML end.
     **/
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        elementOn = false;
        if(localName.equalsIgnoreCase("id")){
            data.setId(Integer.parseInt(elementValue));
        }
        if(localName.equalsIgnoreCase("name")){
            data.setCompany(elementValue);
        } else if(localName.equalsIgnoreCase("title")){
            data.setTitle(elementValue);
        } else if(localName.equalsIgnoreCase("location")){
            data.setLocation(elementValue);
        } else if(localName.equalsIgnoreCase("job-type")){
            data.setJobType(elementValue);
        } else if(localName.equalsIgnoreCase("job-category")){
            data.setJobCategory(elementValue);
        } else if(localName.equalsIgnoreCase("experience-level")){
            data.setLevel(elementValue);
        } else if(localName.equalsIgnoreCase("required-education-level")) {
            data.setRequiredLevel(elementValue);
        }else if(localName.equalsIgnoreCase("keword")){
            data.setKeyword(elementValue);
        }else if(localName.equalsIgnoreCase("salary")) {
            data.setSalary(elementValue);
        } else if (localName.equalsIgnoreCase("job")) {
            jobList.add(data);
            data = null;
        }
    }

    /**
     * This is called to get the tags value
     **/
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (elementOn) {
            elementValue = new String(ch, start, length);
            elementOn = false;
        }

    }



}
