package com.xari.population.controller;

import com.xari.population.bean.Base;
import com.xari.population.bean.ScAppPopuCountStat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xari.population.services.Service1;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//@Controller
@CrossOrigin
@RestController
public class ActivityPopulation {

    @Resource
    Service1 service1 = new Service1();

    @GetMapping("/activePopulation")
    public HashMap<String  ,List> query( @RequestParam(value = "region_name",required =false) String region_name,
            @RequestParam(value = "region_id",required =false) String region_id,
                                             @RequestParam(value = "region_type",required =false) String region_type,
                                             @RequestParam(value = "task",required =false) String task,
                                             @RequestParam(value = "day",required =false) String day) {


        HashMap<String,List > hashMap=new HashMap<String,List>();
        switch (task) {
            case "sexualAndAge":
//                List<ScAppPopuCountStat> list = new ArrayList<ScAppPopuCountStat>();
//                list =  service1.getSexaulAndAgeStatistics(region_id, region_type, day);
                hashMap.clear();
                hashMap.put("data",service1.getSexaulAndAgeStatistics(region_id, region_type, day));
                break;
            case "msisdnOrigin":
                hashMap.clear();
                hashMap.put("data",service1.getMsisdnOrigin(region_id, region_type, day));
                break;
            case "resideFloat":
                hashMap.clear();
                hashMap.put("data",service1.getResideFloat(region_id, region_type, day));
                break;
            case "density":
                hashMap.clear();
                hashMap.put("data",service1.getPopulationDensity(region_id,region_type, day));
                break;
            case "domesticRoamIn":
                hashMap.clear();
                hashMap.put("data",service1.getDomesticRoamIn(region_id, region_type, day));
                break;

            case "foreignRoamIn":
                hashMap.clear();
                hashMap.put("data",service1.getForeignRoamIn(region_id, region_type, day));
                break;
            case "activityPeople":
                hashMap.clear();
                hashMap.put("data",service1.getActivityPeople(region_id, region_type, day));
                System.out.println("activityPeople");
                break;
            case "map":
                hashMap.clear();
                hashMap.put("data",service1.getMapData(region_id, region_type, day));
                break;
            case "cityTree":
                hashMap.clear();
                hashMap.put("data",service1.getCityTree());
                System.out.println("map");
                break;
        }
        return hashMap;

        //    @Autowired
//    JdbcTemplate jdbcTemplate;

//    public List<Map<String,Object>>  list =jdbcTemplate.queryForList("select * from sc_app_popu_count_stat ");
//    return  list.get(0);

    }
}
