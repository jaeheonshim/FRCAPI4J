package structures;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MatchResult {
    private LocalDateTime actualStartTime;
    private String description;
    private TournamentLevel tournamentLevel;
    private int matchNumber;
    private LocalDateTime postResultTime;
    private int scoreRedFinal;
    private int scoreRedFoul;
    private int scoreRedAuto;
    private int scoreBlueFinal;
    private int scoreBlueFoul;
    private int scoreBlueAuto;
    private List<Team> teams;

    public MatchResult(LocalDateTime actualStartTime, String description, TournamentLevel tournamentLevel, int matchNumber, LocalDateTime postResultTime, int scoreRedFinal, int scoreRedFoul, int scoreRedAuto, int scoreBlueFinal, int scoreBlueFoul, int scoreBlueAuto, List<Team> teams) {
        this.actualStartTime = actualStartTime;
        this.description = description;
        this.tournamentLevel = tournamentLevel;
        this.matchNumber = matchNumber;
        this.postResultTime = postResultTime;
        this.scoreRedFinal = scoreRedFinal;
        this.scoreRedFoul = scoreRedFoul;
        this.scoreRedAuto = scoreRedAuto;
        this.scoreBlueFinal = scoreBlueFinal;
        this.scoreBlueFoul = scoreBlueFoul;
        this.scoreBlueAuto = scoreBlueAuto;
        this.teams = teams;
    }

    public static class Team {
        private int teamNumber;
        private String station;
        private boolean disqualified;

        public Team(int teamNumber, String station, boolean disqualified) {
            this.teamNumber = teamNumber;
            this.station = station;
            this.disqualified = disqualified;
        }

        public static Team getFromJson(JSONObject json) {
            return new Team(json.optInt("teamNumber"), json.optString("station"), json.optBoolean("dq", false));
        }

        public int getTeamNumber() {
            return teamNumber;
        }

        public String getStation() {
            return station;
        }

        public boolean isDisqualified() {
            return disqualified;
        }
    }

    public static MatchResult getFromJson(JSONObject jsonObject) {
        JSONArray teams = jsonObject.getJSONArray("teams");
        List<Team> teamsList = new ArrayList<>();

        for (int i = 0; i < teams.length(); i++) {
            teamsList.add(Team.getFromJson(teams.getJSONObject(i)));
        }

        return new MatchResult(LocalDateTime.parse(jsonObject.optString("actualStartTime")),
                jsonObject.optString("description"),
                TournamentLevel.valueOf(jsonObject.optString("tournamentLevel").toUpperCase()),
                jsonObject.optInt("matchNumber"),
                LocalDateTime.parse(jsonObject.optString("postResultTime")),
                jsonObject.optInt("scoreRedFinal"),
                jsonObject.optInt("scoreRedFoul"),
                jsonObject.optInt("scoreRedAuto"),
                jsonObject.optInt("scoreBlueFinal"),
                jsonObject.optInt("scoreBlueFoul"),
                jsonObject.optInt("scoreBlueAuto"),
                teamsList);
    }

    public LocalDateTime getActualStartTime() {
        return actualStartTime;
    }

    public String getDescription() {
        return description;
    }

    public TournamentLevel getTournamentLevel() {
        return tournamentLevel;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public LocalDateTime getPostResultTime() {
        return postResultTime;
    }

    public int getScoreRedFinal() {
        return scoreRedFinal;
    }

    public int getScoreRedFoul() {
        return scoreRedFoul;
    }

    public int getScoreRedAuto() {
        return scoreRedAuto;
    }

    public int getScoreBlueFinal() {
        return scoreBlueFinal;
    }

    public int getScoreBlueFoul() {
        return scoreBlueFoul;
    }

    public int getScoreBlueAuto() {
        return scoreBlueAuto;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
