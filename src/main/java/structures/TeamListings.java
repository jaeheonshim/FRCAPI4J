package structures;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeamListings {
    private int pageCurrent;
    private int pageTotal;
    private int teamCountPage;
    private int teamCountTotal;
    private List<Team> teams;

    public TeamListings(int pageCurrent, int pageTotal, int teamCountPage, int teamCountTotal, List<Team> teams) {
        this.pageCurrent = pageCurrent;
        this.pageTotal = pageTotal;
        this.teamCountPage = teamCountPage;
        this.teamCountTotal = teamCountTotal;
        this.teams = teams;
    }

    public static TeamListings getFromJson(JSONObject jsonObject) {
        List<Team> teamList = new ArrayList<>();
        JSONArray teams = jsonObject.getJSONArray("teams");

        for (int i = 0; i < teams.length(); i++) {
            teamList.add(Team.getFromJson(teams.getJSONObject(i)));
        }

        return new TeamListings(jsonObject.optInt("pageCurrent"), jsonObject.optInt("pageTotal"), jsonObject.optInt("teamCountPage"), jsonObject.optInt("teamCountTotal"), teamList);
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

    public List<Team> getTeams() {
        return teams;
    }
}
