package structures;

import java.time.LocalDateTime;
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

    public enum TournamentLevel {
        QUALIFICATION, PLAYOFF
    }
}
