package com.jaeheonshim.frcapi4j.structures;

import org.json.JSONObject;

public class TeamAvatar {
    private int teamNumber;
    private String encodedAvatar;

    public TeamAvatar(int teamNumber, String encodedAvatar) {
        this.teamNumber = teamNumber;
        this.encodedAvatar = encodedAvatar;
    }

    public static TeamAvatar getFromJson(JSONObject jsonObject) {
        return new TeamAvatar(jsonObject.optInt("teamNumber"), jsonObject.optString("encodedAvatar"));
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getEncodedAvatar() {
        return encodedAvatar;
    }
}
