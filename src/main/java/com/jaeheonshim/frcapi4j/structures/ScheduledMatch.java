package com.jaeheonshim.frcapi4j.structures;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduledMatch {
    private String description;
    private String field;
    private TournamentLevel tournamentLevel;
    private int matchNumber;
    private LocalDateTime startTime;
    private List<Team> teams;

    public ScheduledMatch(String description, String field, TournamentLevel tournamentLevel, int matchNumber, LocalDateTime startTime, List<Team> teams) {
        this.description = description;
        this.field = field;
        this.tournamentLevel = tournamentLevel;
        this.matchNumber = matchNumber;
        this.startTime = startTime;
        this.teams = teams;
    }

    public static ScheduledMatch getFromJson(JSONObject jsonObject) {
        List<Team> teamList = new ArrayList<>();
        JSONArray teams = jsonObject.getJSONArray("teams");

        for(int i = 0; i < teams.length(); i++) {
            teamList.add(Team.getFromJson(teams.getJSONObject(i)));
        }

        return new ScheduledMatch(jsonObject.optString("description"), jsonObject.optString("field"), TournamentLevel.valueOf(jsonObject.optString("tournamentLevel").toUpperCase()), jsonObject.optInt("matchNumber"), LocalDateTime.parse(jsonObject.optString("startTime")), teamList);
    }

    public static class Team {
        private int teamNumber;
        private String station;
        private boolean surrogate;

        public Team(int teamNumber, String station, boolean surrogate) {
            this.teamNumber = teamNumber;
            this.station = station;
            this.surrogate = surrogate;
        }

        public static Team getFromJson(JSONObject jsonObject) {
            return new Team(jsonObject.optInt("number"), jsonObject.optString("station"), jsonObject.optBoolean("surrogate"));
        }

        public int getTeamNumber() {
            return teamNumber;
        }

        public String getStation() {
            return station;
        }

        public boolean isSurrogate() {
            return surrogate;
        }
    }

    public String getDescription() {
        return description;
    }

    public String getField() {
        return field;
    }

    public TournamentLevel getTournamentLevel() {
        return tournamentLevel;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
