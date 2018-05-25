package org.rest.model;

import java.util.List;

/**
 * Created by XiuYin.Cui on 2018/5/25.
 */
public class City {

    /**
     * 城市编码
     */
    private String citycode;

    /**
     * 区域编码
     */
    private String adcode;

    /**
     * 行政区名称
     */
    private String name;

    /**
     * 行政区边界坐标点
     */
    private String polyline;

    /**
     * 城市中心点
     */
    private String center;

    /**
     * 行政区划级别
     */
    private String level;

    /**
     * districts
     */
    private List<City> districts;

    private City(){

    }

    public City(String citycode, String adcode, String name, String polyline, String center, String level, List<City> districts) {
        this.citycode = citycode;
        this.adcode = adcode;
        this.name = name;
        this.polyline = polyline;
        this.center = center;
        this.level = level;
        this.districts = districts;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<City> getDistricts() {
        return districts;
    }

    public void setDistricts(List<City> districts) {
        this.districts = districts;
    }
}
