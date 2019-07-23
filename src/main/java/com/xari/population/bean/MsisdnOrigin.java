package com.xari.population.bean;

public class MsisdnOrigin  {
    private   Float   local_city;
    private   Float   local_prov;
    private   Float   other_prov;
    private   String   region_type;

    public String getRegion_type() {
        return region_type;
    }

    public void setRegion_type(String region_type) {
        this.region_type = region_type;
    }

    public Float getLocal_city() {
        return local_city;
    }

    public void setLocal_city(Float local_city) {
        this.local_city = local_city;
    }

    public Float getLocal_prov() {
        return local_prov;
    }

    public void setLocal_prov(Float local_prov) {
        this.local_prov = local_prov;
    }

    public Float getOther_prov() {
        return other_prov;
    }

    public void setOther_prov(Float other_prov) {
        this.other_prov = other_prov;
    }
}
