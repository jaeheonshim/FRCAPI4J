package com.jaeheonshim.frcapi4j.structures;

import org.json.JSONObject;

public class TeamDistrictRanking {
    private String districtCode;
    private int rank;
    private int totalPoints;
    private String event1Code;
    private int event1Points;
    private String event2Code;
    private int event2Points;
    private String districtCmpCode;
    private int districtCmpPoints;
    private int adjustmentPoints;
    private boolean qualifiedDistrictCmp;
    private boolean qualifiedFirstCmp;

    public TeamDistrictRanking(String districtCode, int rank, int totalPoints, String event1Code, int event1Points, String event2Code, int event2Points, String districtCmpCode, int districtCmpPoints, int adjustmentPoints, boolean qualifiedDistrictCmp, boolean qualifiedFirstCmp) {
        this.districtCode = districtCode;
        this.rank = rank;
        this.totalPoints = totalPoints;
        this.event1Code = event1Code;
        this.event1Points = event1Points;
        this.event2Code = event2Code;
        this.event2Points = event2Points;
        this.districtCmpCode = districtCmpCode;
        this.districtCmpPoints = districtCmpPoints;
        this.adjustmentPoints = adjustmentPoints;
        this.qualifiedDistrictCmp = qualifiedDistrictCmp;
        this.qualifiedFirstCmp = qualifiedFirstCmp;
    }

    public static TeamDistrictRanking getFromJson(JSONObject jsonObject) {
        return new TeamDistrictRanking(jsonObject.optString("districtCode"),
                jsonObject.optInt("rank"),
                jsonObject.optInt("totalPoints"),
                jsonObject.optString("event1Code"),
                jsonObject.optInt("event1Points"),
                jsonObject.optString("event2Code"),
                jsonObject.optInt("event2Points"),
                jsonObject.optString("districtCmpCode"),
                jsonObject.optInt("districtCmpPoints"),
                jsonObject.optInt("adjustmentPoints"),
                jsonObject.optBoolean("qualifiedDistrictCmp"),
                jsonObject.optBoolean("qualifiedFirstCmp"));
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public int getRank() {
        return rank;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public String getEvent1Code() {
        return event1Code;
    }

    public int getEvent1Points() {
        return event1Points;
    }

    public String getEvent2Code() {
        return event2Code;
    }

    public int getEvent2Points() {
        return event2Points;
    }

    public String getDistrictCmpCode() {
        return districtCmpCode;
    }

    public int getDistrictCmpPoints() {
        return districtCmpPoints;
    }

    public int getAdjustmentPoints() {
        return adjustmentPoints;
    }

    public boolean isQualifiedDistrictCmp() {
        return qualifiedDistrictCmp;
    }

    public boolean isQualifiedFirstCmp() {
        return qualifiedFirstCmp;
    }
}
