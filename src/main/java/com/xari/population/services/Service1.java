package com.xari.population.services;

import com.alibaba.fastjson.JSON;
import com.xari.population.bean.*;
import com.xari.population.mapper.PopulationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Service1 {

    @Autowired
    PopulationMapper populationMapper;

    public List<SexaulAndAge> getSexaulAndAgeStatistics(String region_id, String region_type, String day) {
//        List<ScAppPopuCountStat> list = new ArrayList<ScAppPopuCountStat>();
//        list=  scAppPopuCountStatMapper.geSexualAndAgeStatistics(region_id,region_type,day);
//        List<HashMap> l=new ArrayList();
//
//        for (int i = 0; i < list.size(); i++){
//            if ( Integer.parseInt(list.get(i).getAGE())<=5&&Integer.parseInt(list.get(i).getAGE())>=1){
//                list.get(i).getFEMALE_NUM();
//            }
//        }
        return populationMapper.geSexualAndAgeStatistics(region_id, region_type, day);

    }

    public List<MsisdnOrigin> getMsisdnOrigin(String region_id, String region_type, String day) {
        System.out.println("msisdn original");
        return populationMapper.getMsisdnOriginStatistics(region_id, region_type, day);
    }

    public List<ResideFloat> getResideFloat(String region_id, String region_type, String day) {
        return populationMapper.getResideFloatStatistics(region_id, region_type, day);
    }

    public List<PopulationDensity> getPopulationDensity(String region_id, String region_type, String day) {
        return populationMapper.getPopulationDensityStatistics(region_id, region_type, day);
    }

    public List<DomesticRoamIn> getDomesticRoamIn(String region_id, String region_type, String day) {

        return populationMapper.getDomesticRoamInStatistics(region_id, region_type, day);
    }

    public List<ForeignRoamIn> getForeignRoamIn(String region_id, String region_type, String day) {

        return populationMapper.getForeignRoamInStatistics(region_id, region_type, day);
    }

    public List<ActivityPeople> getActivityPeople(String region_id, String region_type, String day) {
        return populationMapper.getActivityPeopleStatistics(region_id, region_type, day);
    }

    public List<MapData> getMapData(String region_id, String region_name, String day) {
        return populationMapper.getMapData(region_id, region_name, day);
    }

    public Boolean isValueInList(List<HashMap> list, String s) {
        List<String> temp = new ArrayList<>();
        for (HashMap<String, String> map : list) {
            for (String key : map.keySet()) {
                temp.add(map.get(key));
            }
        }
        return temp.contains(s);
    }

    public List<HashMap> getCityTree() {

        List<HashMap> resultList = new ArrayList<>(); //全国级容器
        HashMap<String, Object> provinceMap = new HashMap<>();//省级容器

        List<CityTree> cityTreeList = populationMapper.getCityTree();
        for (CityTree cityTree : cityTreeList.stream().filter(x -> x.getParent_region_type().equals("1")).collect(Collectors.toList())) {
            provinceMap.put("region_name", cityTree.getParent_name());
            provinceMap.put("region_id", cityTree.getParent_id());
        }   //将省信息放入provinceMapMap中
        List<HashMap> tempLevel2 = new ArrayList<>();
        for (CityTree cityTree : cityTreeList.stream().filter(x -> x.getChild_region_type().equals("2")).collect(Collectors.toList())) {
            HashMap<String, String> temp = new HashMap();
            temp.put("region_name", cityTree.getChild_name());
            temp.put("region_id", cityTree.getChild_id());
            temp.put("region_type", cityTree.getChild_region_type());
            if (tempLevel2.size() == 0) {
                tempLevel2.add(temp);
            } else {
                if (!isValueInList(tempLevel2, cityTree.getChild_id())) {
                    tempLevel2.add(temp);
                }
            }
        }
        for (HashMap<String, Object> level2Map : tempLevel2) {
            List<HashMap> tempLevel3 = new ArrayList<>();
            for (CityTree level3City : cityTreeList.stream().filter(x -> x.getChild_region_type().equals("3") && x.getParent_id().equals(level2Map.get("region_id"))).collect(Collectors.toList())) {
                HashMap<String, String> temp = new HashMap();
                temp.put("region_name", level3City.getChild_name());
                temp.put("region_id", level3City.getChild_id());
                temp.put("region_type", level3City.getChild_region_type());
                tempLevel3.add(temp);
            }
            level2Map.put("children", tempLevel3);//县级信息放入对应的市内 children

        }

        provinceMap.put("children", tempLevel2);// 将省下辖市放入省级容器 children中
        resultList.add(provinceMap);
        return resultList;

//        System.out.println(JSON.toJSONString(populationMapper.getCityTree()));

//        for(CityTree cityTree :populationMapper.getCityTree()){
//            if(cityTree.getParent_region_type().equals("1")){
//                if (!resultMap.containsKey("children")){
//                    resultMap.put("region_name",cityTree.getParent_name());
//                    resultMap.put("region_id",cityTree.getParent_id());
//                    List<HashMap> childLevel2List=new ArrayList<>();
//                    HashMap<String,Object>  childLevel2ListContent=new HashMap<>();
//
//                    List<HashMap> childLevel3List=new ArrayList<>();
//                    HashMap<String,String>  childLevel3ListContent=new HashMap<>();
//                    childLevel3List.add(childLevel3ListContent);
//                    childLevel2ListContent.put("children",childLevel3List);
//                    childLevel2List.add(childLevel2ListContent);
//                    resultMap.put("children",childLevel2List);
//                }else{
//                    System.out.println(resultMap.get("children").getClass().toString());
//
//                    HashMap<String,Object> tempMap =new HashMap<>();
//                    tempMap.put("region_name",cityTree.getChild_name());
//                    tempMap.put("region_id",cityTree.getChild_id());
//                    List<HashMap> tempList= (ArrayList<HashMap>) resultMap.get("children");
//
//                    tempList.add(tempMap);
//                    resultMap.put("children",tempMap);
//                    System.out.println(resultMap.get("children"));
//
//                }
//            }
//
//        }

    }


}

