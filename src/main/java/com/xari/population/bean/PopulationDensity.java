package com.xari.population.bean;

public class PopulationDensity {
    private  String   region_name;
    private  Float  popu_num;
    private  Integer  popu_dense;


    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public Float getPopu_num() {
        return popu_num;
    }

    public void setPopu_num(Float popu_num) {
        this.popu_num = popu_num;
    }

    public Integer getPopu_dense() {
        return popu_dense;
    }

    public void setPopu_dense(Integer popu_dense) {
        this.popu_dense = popu_dense;
    }
}
