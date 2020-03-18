package com.jaeheonshim.frcapi4j.structures.listings;

import org.json.JSONArray;
import org.json.JSONObject;
import com.jaeheonshim.frcapi4j.structures.TeamAvatar;

import java.util.ArrayList;
import java.util.List;

public class TeamAvatarListings {
    private int pageCurrent;
    private int pageTotal;
    private int teamCountPage;
    private int teamCountTotal;
    private List<TeamAvatar> teams;

    public TeamAvatarListings(int pageCurrent, int pageTotal, int teamCountPage, int teamCountTotal, List<TeamAvatar> teams) {
        this.pageCurrent = pageCurrent;
        this.pageTotal = pageTotal;
        this.teamCountPage = teamCountPage;
        this.teamCountTotal = teamCountTotal;
        this.teams = teams;
    }

    public static TeamAvatarListings getFromJson(JSONObject jsonObject) {
        List<TeamAvatar> teamAvatarList = new ArrayList<>();
        JSONArray teamAvatars = jsonObject.getJSONArray("teams");

        for(int i = 0; i < teamAvatars.length(); i++) {
            teamAvatarList.add(TeamAvatar.getFromJson(teamAvatars.getJSONObject(i)));
        }

        return new TeamAvatarListings(jsonObject.optInt("pageCurrent"), jsonObject.optInt("pageTotal"), jsonObject.optInt("teamCountPage"), jsonObject.optInt("teamCountTotal"), teamAvatarList);
    }

    public int getPageCurrent() {
        return pageCurrent;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public int getTeamCountPage() {
        return teamCountPage;
    }

    public int getTeamCountTotal() {
        return teamCountTotal;
    }

    public List<TeamAvatar> getTeams() {
        return teams;
    }
}
