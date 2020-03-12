package structures;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HybridSchedule {
    private LocalDateTime actualStartTime;
    private String description;
    private int matchNumber;
    private LocalDateTime postResultTime;
    private int scoreRedFinal;
    private int scoreRedFoul;
    private int scoreRedAuto;
    private int scoreBlueFinal;
    private int scoreBlueFoul;
    private int scoreBlueAuto;
    private LocalDateTime startTime;
    private TournamentLevel tournamentLevel;
    private List<Team> teams;

    public HybridSchedule(LocalDateTime actualStartTime, String description, int matchNumber, LocalDateTime postResultTime, int scoreRedFinal, int scoreRedFoul, int scoreRedAuto, int scoreBlueFinal, int scoreBlueFoul, int scoreBlueAuto, LocalDateTime startTime, TournamentLevel tournamentLevel, List<Team> teams) {
        this.actualStartTime = actualStartTime;
        this.description = description;
        this.matchNumber = matchNumber;
        this.postResultTime = postResultTime;
        this.scoreRedFinal = scoreRedFinal;
        this.scoreRedFoul = scoreRedFoul;
        this.scoreRedAuto = scoreRedAuto;
        this.scoreBlueFinal = scoreBlueFinal;
        this.scoreBlueFoul = scoreBlueFoul;
        this.scoreBlueAuto = scoreBlueAuto;
        this.startTime = startTime;
        this.tournamentLevel = tournamentLevel;
        this.teams = teams;
    }

    public static HybridSchedule getFromJson(JSONObject jsonObject) {
        List<Team> teamList = new ArrayList<>();
        JSONArray teams = jsonObject.getJSONArray("teams");

        for (int i = 0; i < teams.length(); i++) {
            teamList.add(Team.getFromJson(teams.getJSONObject(i)));
        }

        return new HybridSchedule(LocalDateTime.parse(jsonObject.optString("actualStartTime")),
                jsonObject.optString("description"),
                jsonObject.optInt("matchNumber"),
                LocalDateTime.parse(jsonObject.optString("postResultTime")),
                jsonObject.optInt("scoreRedFinal"),
                jsonObject.optInt("scoreRedFoul"),
                jsonObject.optInt("scoreRedAuto"),
                jsonObject.optInt("scoreBlueFinal"),
                jsonObject.optInt("scoreBlueFoul"),
                jsonObject.optInt("scoreBlueAuto"),
                LocalDateTime.parse(jsonObject.optString("startTime")),
                TournamentLevel.valueOf(jsonObject.optString("tournamentLevel").toUpperCase()),
                teamList);
    }

    public static class Team {
        private int teamNumber;
        private String station;
        private boolean surrogate;
        private boolean dq;

        public Team(int teamNumber, String station, boolean surrogate, boolean dq) {
            this.teamNumber = teamNumber;
            this.station = station;
            this.surrogate = surrogate;
            this.dq = dq;
        }

        public static Team getFromJson(JSONObject jsonObject) {
            return new Team(jsonObject.optInt("teamNumber"), jsonObject.optString("station"), jsonObject.optBoolean("surrogate"), jsonObject.optBoolean("dq"));
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

        public boolean isDq() {
            return dq;
        }
    }

    public LocalDateTime getActualStartTime() {
        return actualStartTime;
    }

    public String getDescription() {
        return description;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public TournamentLevel getTournamentLevel() {
        return tournamentLevel;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
