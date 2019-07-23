package com.xari.population.bean;

public class MapData {

    private String region_name;
    private String region_id;
    private String region_type;
    private String parent_id;
    private String parent_name;
    private String parent_type;
    private Float parent_total_num;
    private Float child_total_num;

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getRegion_type() {
        return region_type;
    }

    public void setRegion_type(String region_type) {
        this.region_type = region_type;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getParent_type() {
        return parent_type;
    }

    public void setParent_type(String parent_type) {
        this.parent_type = parent_type;
    }

    public Float getParent_total_num() {
        return parent_total_num;
    }

    public void setParent_total_num(Float parent_total_num) {
        this.parent_total_num = parent_total_num;
    }

    public Float getChild_total_num() {
        return child_total_num;
    }

    public void setChild_total_num(Float child_total_num) {
        this.child_total_num = child_total_num;
    }
}
