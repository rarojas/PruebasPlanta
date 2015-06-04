package com.selmec.plantaselmec.dto;

import java.util.Date;

public class TablaLecturaDTO {

    public String tagname;
    public Double tagvalue;
    public Integer tagquality;
    public Date tagtimestamp;

    public String getTagname() {
        return this.tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public Double getTagvalue() {
        return this.tagvalue;
    }

    public void setTagvalue(Double tagvalue) {
        this.tagvalue = tagvalue;
    }

    public Integer getTagquality() {
        return this.tagquality;
    }

    public void setTagquality(Integer tagquality) {
        this.tagquality = tagquality;
    }

    public Date getTagtimestamp() {
        return this.tagtimestamp;
    }

    public void setTagtimestamp(Date tagtimestamp) {
        this.tagtimestamp = tagtimestamp;
    }
}
