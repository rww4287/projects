package com.ktds.smallsumsettlement.model;

/**
 * Created by Admin on 2017-07-11.
 */

public class Settlement {

    private int id;
    private int limit = 300000;
    private int accumulate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getAccumulate() {
        return accumulate;
    }

    public void setAccumulate(int accumulate) {
        this.accumulate = accumulate;
    }
}
