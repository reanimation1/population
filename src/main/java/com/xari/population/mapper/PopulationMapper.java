package com.xari.population.mapper;

import com.xari.population.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PopulationMapper {


//    @Select("select * from sc_app_popu_count_stat  where region_id=#{regionId}  and region_type=#{regionType} and day=#{day}")
//    public List<ScAppPopuCountStat> geSexualAndAgeStatistics(String regionId, String regionType, String day);

    @Select("SELECT  \n" +
            "case\n" +
            "when t.AGE>=0 and t.age<=18 then '18'\n" +
            "when t.AGE>=19 and t.age<=24 then '19-24'\n" +
            "when t.AGE>=25 and t.age<=30 then '25-30'\n" +
            "when t.AGE>=31 and t.age<=40 then '31-40'\n" +
            "when t.AGE>=41 and t.age<=50 then '41-50'\n" +
            "when t.AGE>=51 and t.age<=60 then '51-60'\n" +
            "when t.AGE>=61 and t.age<=120 then '61'\n" +
            "\n" +
            "else null end  as  age_class,\n" +
            "TRUNCATE(sum(t.MALE_NUM)/10000,2)  as male,TRUNCATE(sum(t.FEMALE_NUM)/10000,2) as female\n" +
            "\n" +
            "from (select * from sc_app_popu_count_stat  where region_id=#{regionId}  and day=#{day}) t\n" +
            "GROUP BY\n" +
            "case \n" +
            "when t.AGE>=0 and t.age<=18 then '18'\n" +
            "when t.AGE>=19 and t.age<=24 then '19-24'\n" +
            "when t.AGE>=25 and t.age<=30 then '25-30'\n" +
            "when t.AGE>=31 and t.age<=40 then '31-40'\n" +
            "when t.AGE>=41 and t.age<=50 then '41-50'\n" +
            "when t.AGE>=51 and t.age<=60 then '51-60'\n" +
            "when t.AGE>=61 and t.age<=120 then '61'\n" +
            "else null end \n" +
            "ORDER BY \n" +
            "case \n" +
            "when t.AGE>=0 and t.age<=18 then '18'\n" +
            "when t.AGE>=19 and t.age<=24 then '19-24'\n" +
            "when t.AGE>=25 and t.age<=30 then '25-30'\n" +
            "when t.AGE>=31 and t.age<=40 then '31-40'\n" +
            "when t.AGE>=41 and t.age<=50 then '41-50'\n" +
            "when t.AGE>=51 and t.age<=60 then '51-60'\n" +
            "when t.AGE>=61 and t.age<=120 then '61'\n" +
            "else null end  ")
    public List<SexaulAndAge> geSexualAndAgeStatistics(String regionId, String regionType, String day);

    @Select("select region_type,truncate(local_city/10000,2) as local_city,truncate(local_prov/10000,2) as local_prov,truncate(other_prov/10000,2) as other_prov from sc_app_msisdn_origin_stat  where region_id=#{regionId}  and day=#{day}")
    public  List<MsisdnOrigin> getMsisdnOriginStatistics(String regionId, String regionType, String day);

    @Select("select truncate(reside_num/10000,2) as reside_num ,truncate(float_num/10000,2) as float_num  from sc_app_reside_float_stat  where region_id=#{regionId}   and day=#{day}")
    public List<ResideFloat>  getResideFloatStatistics(String regionId, String regionType, String day);

    @Select("SELECT region_name,truncate(popu_num/10000,2) as popu_num ,popu_dense from sc_app_popu_density_stat where region_type=#{regionType}  and  day=#{day}  and region_id in \n" +
            "(\n" +
            "SELECT child_id from \n" +
            "(\n" +
            "select  adcode_lev1 as  parent_id ,areaname_lev1 as  parent_name , '1' as parent_region_type ,adcode_lev2 as child_id, areaname_lev2 as child_name  ,'2' as child_region_type from sc_dim_adcode_lev \n" +
            "union all\n" +
            "select  adcode_lev2 as  parent_id ,areaname_lev2 as  parent_name , '2' as parent_region_type , adcode_lev3 as child_id, areaname_lev3 as child_name ,'3' as child_region_type  from sc_dim_adcode_lev\n" +
            ")  t  WHERE t.parent_id=#{regionId}\n" +
            "\n" +
            ")  order by popu_dense  desc  ")
    public  List<PopulationDensity>  getPopulationDensityStatistics(String regionId,String regionType, String day);

    @Select("select  other_prov_name ,truncate(total_num/10000,2) as total_num from  sc_app_domestic_roam_in  where region_id=#{regionId}   and day=#{day}  order by total_num desc  ")
    public List<DomesticRoamIn>   getDomesticRoamInStatistics(String regionId, String regionType, String day);

    @Select("select  country_name ,total_num as total_num from  sc_app_foreign_roam_in_top10  where region_id=#{regionId}  and  day=#{day} order by total_num desc ")
    public List<ForeignRoamIn>   getForeignRoamInStatistics(String regionId, String regionType, String day);

    @Select("SELECT  tt.DAY, truncate(sum( popu_num )/10000,2) AS total_num \n" +
            "FROM\n" +
            " (\n" +
            "SELECT  * FROM   sc_app_popu_density_stat t WHERE  t.REGION_ID=#{regionId}  and \n" +
            "DATE_SUB( date( #{day} ), INTERVAL 7 DAY ) < date( t.DAY )   AND date( t.DAY ) <= date( #{day} ) \n" +
            " ) AS tt \n" +
            "GROUP BY tt.DAY ORDER BY   tt.DAY")
    public  List<ActivityPeople> getActivityPeopleStatistics(String regionId, String regionType, String day);


    @Select("SELECT  t1.*,t2.region_name as parent_name, t2.region_type as parent_type,truncate(t3.parent_total_num/10000,2)  as parent_total_num\n" +
            "from (\n" +
            "SELECT #{regionId} as parent_id,region_name, region_id, region_type, truncate(popu_num/10000,2)  as child_total_num from sc_app_popu_density_stat stat where stat.REGION_ID in \n" +
            "(\n" +
            "select child_id  from (\n" +
            "select  adcode_lev1 as  parent_id ,areaname_lev1 as  parent_name , '1' as parent_type, adcode_lev2 as child_id, areaname_lev2 as child_name  ,'2' as child_type  from sc_dim_adcode_lev \n" +
            "union all\n" +
            "select  adcode_lev2 as  parent_id ,areaname_lev2 as  parent_name , '2' as parent_type, adcode_lev3 as child_id, areaname_lev3 as child_name  ,'3' as child_type  from sc_dim_adcode_lev \n" +
            ") t  where t.parent_id=#{regionId})   and  stat.day=#{day}  GROUP BY region_name, region_id  ) t1\n" +
            "left join \n" +
            "(select  adcode_lev1 as  region_id  ,areaname_lev1 as  region_name  , '1' as region_type from sc_dim_adcode_lev \n" +
            "union \n" +
            "SELECT  adcode_lev2 as region_id,  areaname_lev2 as region_name  ,'2' as region_type  from sc_dim_adcode_lev \n" +
            "union \n" +
            "SELECT adcode_lev3 as region_id, areaname_lev3 as region_name  ,'3' as region_type  from sc_dim_adcode_lev ) t2\n" +
            "on  (t1.parent_id =t2.region_id) \n" +
            "left join \n" +
            "(\n" +
            "SELECT #{regionId} as parent_id, sum(popu_num)  as parent_total_num from sc_app_popu_density_stat stat where stat.REGION_ID in \n" +
            "(\n" +
            "select child_id  from (\n" +
            "select  adcode_lev1 as  parent_id ,areaname_lev1 as  parent_name , '1' as parent_type, adcode_lev2 as child_id, areaname_lev2 as child_name  ,'2' as child_type  from sc_dim_adcode_lev \n" +
            "union all\n" +
            "select  adcode_lev2 as  parent_id ,areaname_lev2 as  parent_name , '2' as parent_type, adcode_lev3 as child_id, areaname_lev3 as child_name  ,'3' as child_type  from sc_dim_adcode_lev \n" +
            ") t  where t.parent_id=#{regionId})   and  stat.day=#{day}\n" +
            ")  t3 on (t1.parent_id= t3.parent_id)\n" )
    public List<MapData> getMapData (String regionId,String regionName ,String day);

    @Select("\n" +
            "select  adcode_lev1 as  parent_id ,areaname_lev1 as  parent_name , '1' as parent_region_type ,adcode_lev2 as child_id, areaname_lev2 as child_name  ,'2' as child_region_type from sc_dim_adcode_lev \n" +
            "union all\n" +
            "select  adcode_lev2 as  parent_id ,areaname_lev2 as  parent_name , '2' as parent_region_type , adcode_lev3 as child_id, areaname_lev3 as child_name ,'3' as child_region_type  from sc_dim_adcode_lev")
    public List<CityTree> getCityTree ();



//    @Select("SELECT \n" +
//            "adcode_lev1 as region_id_level1,areaname_lev1 as region_name_level1,\n" +
//            "adcode_lev2 as region_id_level2,areaname_lev2 as region_name_level2,\n" +
//            "adcode_lev3 as region_id_level3,areaname_lev3 as region_name_level3\n" +
//            "from sc_dim_adcode_lev  ")
//    public List<CityTree> getCityTree ();





}
