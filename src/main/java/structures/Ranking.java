package structures;

import org.json.JSONObject;

public class Ranking {
    private boolean disqualified;
    private int losses;
    private int matchesPlayed;
    private double qualAverage;
    private int rank;
    private int teamNumber;
    private int ties;
    private int wins;
    private int sortOrder1;
    private int sortOrder2;
    private int sortOrder3;
    private int sortOrder4;
    private int sortOrder5;
    private int sortOrder6;

    public Ranking(boolean disqualified, int losses, int matchesPlayed, double qualAverage, int rank, int teamNumber, int ties, int wins, int sortOrder1, int sortOrder2, int sortOrder3, int sortOrder4, int sortOrder5, int sortOrder6) {
        this.disqualified = disqualified;
        this.losses = losses;
        this.matchesPlayed = matchesPlayed;
        this.qualAverage = qualAverage;
        this.rank = rank;
        this.teamNumber = teamNumber;
        this.ties = ties;
        this.wins = wins;
        this.sortOrder1 = sortOrder1;
        this.sortOrder2 = sortOrder2;
        this.sortOrder3 = sortOrder3;
        this.sortOrder4 = sortOrder4;
        this.sortOrder5 = sortOrder5;
        this.sortOrder6 = sortOrder6;
    }

    public static Ranking getFromJson(JSONObject jsonObject) {
        return new Ranking(jsonObject.optBoolean("dq", false),
                jsonObject.optInt("losses"),
                jsonObject.optInt("matchesPlayed"),
                jsonObject.optDouble("qualAverage"),
                jsonObject.optInt("rank"),
                jsonObject.optInt("teamNumber"),
                jsonObject.optInt("ties"),
                jsonObject.optInt("wins"),
                jsonObject.optInt("sortOrder1"),
                jsonObject.optInt("sortOrder2"),
                jsonObject.optInt("sortOrder3"),
                jsonObject.optInt("sortOrder4"),
                jsonObject.optInt("sortOrder5"),
                jsonObject.optInt("sortOrder6"));
    }

    public boolean isDisqualified() {
        return disqualified;
    }

    public int getLosses() {
        return losses;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public double getQualAverage() {
        return qualAverage;
    }

    public int getRank() {
        return rank;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public int getTies() {
        return ties;
    }

    public int getWins() {
        return wins;
    }

    public int getSortOrder1() {
        return sortOrder1;
    }

    public int getSortOrder2() {
        return sortOrder2;
    }

    public int getSortOrder3() {
        return sortOrder3;
    }

    public int getSortOrder4() {
        return sortOrder4;
    }

    public int getSortOrder5() {
        return sortOrder5;
    }

    public int getSortOrder6() {
        return sortOrder6;
    }
}
