package com.jaeheonshim.frcapi4j.structures;

import org.json.JSONObject;

public class Team {
    private int teamNumber;
    private String nameFull;
    private String nameShort;
    private String schoolName;
    private String homeCMP;
    private String city;
    private String stateProv;
    private String country;
    private String website;
    private int rookieYear;
    private String robotName;
    private String districtCode;

    public Team(int teamNumber, String nameFull, String nameShort, String schoolName, String homeCMP, String city, String stateProv, String country, String website, int rookieYear, String robotName, String districtCode) {
        this.teamNumber = teamNumber;
        this.nameFull = nameFull;
        this.nameShort = nameShort;
        this.schoolName = schoolName;
        this.homeCMP = homeCMP;
        this.city = city;
        this.stateProv = stateProv;
        this.country = country;
        this.website = website;
        this.rookieYear = rookieYear;
        this.robotName = robotName;
        this.districtCode = districtCode;
    }

    public static Team getFromJson(JSONObject jsonObject) {
        return new Team(jsonObject.optInt("teamNumber"),
                jsonObject.optString("nameFull"),
                jsonObject.optString("nameShort"),
                jsonObject.optString("schoolName"),
                jsonObject.optString("homeCMP"),
                jsonObject.optString("city"),
                jsonObject.optString("stateProv"),
                jsonObject.optString("country "),
                jsonObject.optString("website"),
                jsonObject.optInt("rookieYear"),
                jsonObject.optString("robotName"),
                jsonObject.optString("districtCode"));
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getNameFull() {
        return nameFull;
    }

    public String getNameShort() {
        return nameShort;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getHomeCMP() {
        return homeCMP;
    }

    public String getCity() {
        return city;
    }

    public String getStateProv() {
        return stateProv;
    }

    public String getCountry() {
        return country;
    }

    public String getWebsite() {
        return website;
    }

    public int getRookieYear() {
        return rookieYear;
    }

    public String getRobotName() {
        return robotName;
    }

    public String getDistrictCode() {
        return districtCode;
    }
}
