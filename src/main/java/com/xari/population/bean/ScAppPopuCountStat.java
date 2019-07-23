package com.xari.population.bean;


public class ScAppPopuCountStat  extends  Base {

    private String day;

    private String region_type;

    private String region_id;



    private String region_name;
    private String age;
    private Integer male_num;

    private Integer female_num;


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getMale_num() {
        return male_num;
    }

    public void setMale_num(Integer male_num) {
        this.male_num = male_num;
    }

    public Integer getFemale_num() {
        return female_num;
    }

    public void setFemale_num(Integer female_num) {
        this.female_num = female_num;
    }
}
