package com.wirebraincoffe.productapiannotation.vo;

public class enrollementQuery {

    private String programid;

    private String providerid;

    public enrollementQuery() {
    }

    public String getProgramid() {
        return programid;
    }

    public String getProviderid() {
        return providerid;
    }

    public void setProgramid(String programid) {
        this.programid = programid;
    }

    public void setProviderid(String providerid) {
        this.providerid = providerid;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    private int limit;
}
