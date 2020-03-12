package structures;

import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class Registration {
    private int teamNumber;
    private List<String> events;

    public Registration(int teamNumber, List<String> events) {
        this.teamNumber = teamNumber;
        this.events = events;
    }

    public static Registration getFromJson(JSONObject jsonObject) {
        return new Registration(jsonObject.optInt("teamNumber"), jsonObject.optJSONArray("events").toList().stream().map(s -> s.toString()).collect(Collectors.toList()));
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public List<String> getEvents() {
        return events;
    }
}
