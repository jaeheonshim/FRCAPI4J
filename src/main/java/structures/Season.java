package structures;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Season {
    private int eventCount;
    private String gameName;
    private LocalDateTime kickoff;
    private int rookieStart;
    private int teamCount;
    private List<FRCChampionship> frcChampionships;

    public Season(int eventCount, String gameName, LocalDateTime kickoff, int rookieStart, int teamCount, List<FRCChampionship> frcChampionships) {
        this.eventCount = eventCount;
        this.gameName = gameName;
        this.kickoff = kickoff;
        this.rookieStart = rookieStart;
        this.teamCount = teamCount;
        this.frcChampionships = frcChampionships;
    }

    public static Season getFromJson(JSONObject jsonObject) {
        List<FRCChampionship> championshipList = new ArrayList<>();
        JSONArray championships = jsonObject.getJSONArray("frcChampionships");

        for(int i = 0; i < championships.length(); i++) {
            championshipList.add(FRCChampionship.getFromJson(championships.getJSONObject(i)));
        }

        return new Season(jsonObject.optInt("eventCount"), jsonObject.optString("gameName"), LocalDateTime.parse(jsonObject.optString("kickoff").replace("Z", "")), jsonObject.optInt("rookieStart"), jsonObject.optInt("teamCount"), championshipList);
    }

    public static class FRCChampionship {
        private String name;
        private LocalDateTime startDate;
        private String location;

        public FRCChampionship(String name, LocalDateTime startDate, String location) {
            this.name = name;
            this.startDate = startDate;
            this.location = location;
        }

        public static FRCChampionship getFromJson(JSONObject jsonObject) {
            return new FRCChampionship(jsonObject.optString("name"), LocalDateTime.parse(jsonObject.optString("startDate")), jsonObject.optString("location"));
        }

        public String getName() {
            return name;
        }

        public LocalDateTime getStartDate() {
            return startDate;
        }

        public String getLocation() {
            return location;
        }
    }

    public int getEventCount() {
        return eventCount;
    }

    public String getGameName() {
        return gameName;
    }

    public LocalDateTime getKickoff() {
        return kickoff;
    }

    public int getRookieStart() {
        return rookieStart;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public List<FRCChampionship> getFrcChampionships() {
        return frcChampionships;
    }
}
