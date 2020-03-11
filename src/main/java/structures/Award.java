package structures;

import org.json.JSONObject;

public class Award {
    private int awardId;
    private int teamId;
    private int eventId;
    private int eventDivisionId;
    private String name;
    private int series;
    private int teamNumber;
    private String fullTeamName;
    private String person;

    public Award(int awardId, int teamId, int eventId, int eventDivisionId, String name, int series, int teamNumber, String fullTeamName, String person) {
        this.awardId = awardId;
        this.teamId = teamId;
        this.eventId = eventId;
        this.eventDivisionId = eventDivisionId;
        this.name = name;
        this.series = series;
        this.teamNumber = teamNumber;
        this.fullTeamName = fullTeamName;
        this.person = person;
    }

    public int getAwardId() {
        return awardId;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getEventId() {
        return eventId;
    }

    public int getEventDivisionId() {
        return eventDivisionId;
    }

    public String getName() {
        return name;
    }

    public int getSeries() {
        return series;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getFullTeamName() {
        return fullTeamName;
    }

    public String getPerson() {
        return person;
    }

    public static Award getFromJson(JSONObject jsonObject) {
        return new Award(jsonObject.optInt("awardId"), jsonObject.optInt("teamId"), jsonObject.optInt("eventId"), jsonObject.optInt("eventDivisionId"), jsonObject.optString("name"), jsonObject.optInt("series"), jsonObject.optInt("teamNumber"), jsonObject.optString("fullTeamName"), jsonObject.optString("person"));
    }
}
