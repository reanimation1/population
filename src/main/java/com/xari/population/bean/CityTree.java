package com.xari.population.bean;

public class CityTree {

    private  String parent_id;
    private  String parent_name;
    private  String parent_region_type;
    private  String child_id;
    private  String child_name;
    private  String child_region_type;

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

    public String getParent_region_type() {
        return parent_region_type;
    }

    public void setParent_region_type(String parent_region_type) {
        this.parent_region_type = parent_region_type;
    }

    public String getChild_id() {
        return child_id;
    }

    public void setChild_id(String child_id) {
        this.child_id = child_id;
    }

    public String getChild_name() {
        return child_name;
    }

    public void setChild_name(String child_name) {
        this.child_name = child_name;
    }

    public String getChild_region_type() {
        return child_region_type;
    }

    public void setChild_region_type(String child_region_type) {
        this.child_region_type = child_region_type;
    }
}
