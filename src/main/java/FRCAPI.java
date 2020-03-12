import com.sun.istack.internal.NotNull;
import jdk.management.resource.internal.TotalResourceContext;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import structures.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class FRCAPI {
    private String authToken;
    private int season;

    private final String BASE_URL = "https://frc-api.firstinspires.org/v2.0";
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public FRCAPI(String username, String authentication) {
        authToken = String.valueOf(Base64.getEncoder().encodeToString(String.format("%s:%s", username, authentication).getBytes()));
    }

    public FRCAPI(String username, String authentication, int season) {
        this(username, authentication);
        this.season = season;
    }

    private JSONObject sendGet(String requestString) {
        HttpGet request = new HttpGet(BASE_URL + requestString);
        request.setHeader("Authorization", "Basic " + authToken);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);
                if (result.charAt(0) != '{') {
                    throw new RuntimeException(result);
                } else {
                    return new JSONObject(result);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String formRequest(int season, String endpoint, String eventCode, Parameter... args) {
        StringBuilder stringBuilder = new StringBuilder("/");
        stringBuilder.append(season);
        stringBuilder.append("/");
        stringBuilder.append(endpoint);
        stringBuilder.append("/");
        if (eventCode != null) {
            stringBuilder.append(eventCode);
        }

        for (Parameter arg : args) {
            if (arg.getValue() != null) {
                stringBuilder.append("?");
                stringBuilder.append(arg.getKey());
                stringBuilder.append("=");
                stringBuilder.append(arg.getValue());
            }
        }

        return stringBuilder.toString();
    }

    public static class Parameter {
        private String key;
        private String value;

        public Parameter(String key, Object value) {
            this.key = key;
            if (value != null) {
                this.value = value.toString();
            } else {
                this.value = null;
            }
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    public List<Alliance> getEventAlliances(int season, @NotNull String eventCode) {
        JSONObject response = sendGet(formRequest(season, "alliances", eventCode));
        List<Alliance> alliances = new ArrayList<>();

        JSONArray jsonArray = response.getJSONArray("Alliances");
        for (int i = 0; i < jsonArray.length(); i++) {
            alliances.add(Alliance.getFromJson(jsonArray.getJSONObject(i)));
        }

        return alliances;
    }

    public List<Alliance> getEventAlliances(@NotNull String eventCode) {
        if (this.season != 0) {
            return getEventAlliances(this.season, eventCode);
        } else {
            return null;
        }
    }

    public List<Award> getEventAwards(int season, @NotNull String eventCode, Integer teamNumber) {
        JSONObject response = sendGet(formRequest(season, "awards", eventCode, new Parameter("teamNumber", teamNumber)));
        List<Award> awards = new ArrayList<>();

        JSONArray jsonArray = response.getJSONArray("Awards");
        for (int i = 0; i < jsonArray.length(); i++) {
            awards.add(Award.getFromJson(jsonArray.getJSONObject(i)));
        }

        return awards;
    }

    public List<Award> getEventAwards(String eventCode, Integer teamNumber) {
        if (this.season != 0) {
            return getEventAwards(this.season, eventCode, teamNumber);
        } else {
            return null;
        }
    }

    public List<MatchResult> getMatchResults(int season, @NotNull String eventCode, TournamentLevel tournamentLevel, Integer teamNumber, Integer matchNumber, Integer start, Integer end) {
        JSONObject response = sendGet(
                formRequest(season, "matches", eventCode,
                        new Parameter("tournamentLevel", tournamentLevel),
                        new Parameter("teamNumber", teamNumber),
                        new Parameter("matchNumber", matchNumber),
                        new Parameter("start", start),
                        new Parameter("end", end)
                )
        );

        List<MatchResult> results = new ArrayList<>();
        JSONArray jsonArray = response.getJSONArray("Matches");

        for (int i = 0; i < jsonArray.length(); i++) {
            results.add(MatchResult.getFromJson(jsonArray.getJSONObject(i)));
        }

        return results;
    }

    public List<MatchResult> getMatchResults(@NotNull String eventCode, TournamentLevel tournamentLevel, Integer teamNumber, Integer matchNumber, Integer start, Integer end) {
        if (this.season != 0) {
            return getMatchResults(this.season, eventCode, tournamentLevel, teamNumber, matchNumber, start, end);
        } else {
            return null;
        }
    }

    public List<Ranking> getMatchRankings(int season, @NotNull String eventCode, Integer teamNumber, Integer top) {
        JSONObject response = sendGet(
                formRequest(season, "rankings", eventCode,
                        new Parameter("teamNumber", teamNumber),
                        new Parameter("top", top)
                )
        );

        List<Ranking> rankings = new ArrayList<>();
        JSONArray jsonArray = response.getJSONArray("Rankings");

        for (int i = 0; i < jsonArray.length(); i++) {
            rankings.add(Ranking.getFromJson(jsonArray.getJSONObject(i)));
        }

        return rankings;
    }

    public List<Ranking> getMatchRankings(@NotNull String eventCode, Integer teamNumber, Integer top) {
        if (this.season != 0) {
            return getMatchRankings(this.season, eventCode, teamNumber, top);
        } else {
            return null;
        }
    }

    public DistrictRankings getDistrictRankings(int season, @NotNull String districtCode, Integer teamNumber, Integer top, Integer page) {
        JSONObject response = sendGet(
                formRequest(season, "rankings/district", districtCode,
                        new Parameter("teamNumber", teamNumber),
                        new Parameter("top", top),
                        new Parameter("page", page)
                )
        );

        return DistrictRankings.getFromJson(response);
    }

    public DistrictRankings getDistrictRankings(@NotNull String districtCode, Integer teamNumber, Integer top, Integer page) {
        if (this.season != 0) {
            return getDistrictRankings(this.season, districtCode, teamNumber, top, page);
        } else {
            return null;
        }
    }

    public List<ScheduledMatch> getEventSchedule(int season, @NotNull String eventCode, TournamentLevel tournamentLevel, Integer teamNumber, Integer start, Integer end) {
        JSONObject response = sendGet(
                formRequest(season, "schedule", eventCode,
                        new Parameter("tournamentLevel", tournamentLevel.toString()),
                        new Parameter("teamNumber", teamNumber),
                        new Parameter("start", start),
                        new Parameter("end", end)
                )
        );

        List<ScheduledMatch> scheduledMatchList = new ArrayList<>();
        JSONArray scheduledMatches = response.getJSONArray("Schedule");

        for (int i = 0; i < scheduledMatches.length(); i++) {
            scheduledMatchList.add(ScheduledMatch.getFromJson(scheduledMatches.getJSONObject(i)));
        }

        return scheduledMatchList;
    }

    public List<ScheduledMatch> getEventSchedule(@NotNull String eventCode, TournamentLevel tournamentLevel, Integer teamNumber, Integer start, Integer end) {
        if (this.season != 0) {
            return getEventSchedule(this.season, eventCode, tournamentLevel, teamNumber, start, end);
        } else {
            return null;
        }
    }

    public List<HybridSchedule> getHybridSchedule(int season, @NotNull String eventCode, @NotNull TournamentLevel tournamentLevel, Integer start, Integer end) {
        JSONObject response = sendGet(
                formRequest(season, "schedule/" + eventCode + "/" + tournamentLevel.toString() + "/hybrid", "",
                        new Parameter("start", start),
                        new Parameter("end", end)
                )
        );

        List<HybridSchedule> hybridScheduleList = new ArrayList<>();
        JSONArray hybridSchedules = response.getJSONArray("Schedule");

        for (int i = 0; i < hybridSchedules.length(); i++) {
            hybridScheduleList.add(HybridSchedule.getFromJson(hybridSchedules.getJSONObject(i)));
        }

        return hybridScheduleList;
    }

    public List<HybridSchedule> getHybridSchedule(@NotNull String eventCode, @NotNull TournamentLevel tournamentLevel, Integer start, Integer end) {
        if (this.season != 0) {
            return getHybridSchedule(this.season, eventCode, tournamentLevel, start, end);
        } else {
            return null;
        }
    }

    public Season getSeason(int season) {
        JSONObject response = sendGet(formRequest(season, "", ""));
        return Season.getFromJson(response);
    }

    public Season getSeason() {
        if(this.season != 0) {
            return getSeason(this.season);
        } else {
            return null;
        }
    }

    public List<Event> getEvents(int season, String eventCode, Integer teamNumber, String districtCode, Boolean excludeDistrict) {
        JSONObject response = sendGet(
                formRequest(season, "events", "",
                        new Parameter("eventCode", eventCode),
                        new Parameter("teamNumber", teamNumber),
                        new Parameter("districtCode", districtCode),
                        new Parameter("excludeDistrict", excludeDistrict)
                )
        );

        List<Event> eventList = new ArrayList<>();
        JSONArray events = response.getJSONArray("Events");

        for(int i = 0; i < events.length(); i++) {
            eventList.add(Event.getFromJson(events.getJSONObject(i)));
        }

        return eventList;
    }

    public List<Event> getEvents(String eventCode, Integer teamNumber, String districtCode, Boolean excludeDistrict) {
        if(this.season != 0) {
            return getEvents(this.season, eventCode, teamNumber, districtCode, excludeDistrict);
        } else {
            return null;
        }
    }

    public List<District> getDistricts(int season) {
        JSONObject response = sendGet(formRequest(season, "districts", ""));

        List<District> districtList = new ArrayList<>();
        JSONArray districts = response.getJSONArray("districts");
        for(int i = 0; i < districts.length(); i++) {
            districtList.add(District.getFromJson(districts.getJSONObject(i)));
        }

        return districtList;
    }

    public List<District> getDistricts() {
        if(this.season != 0) {
            return getDistricts(this.season);
        } else {
            return null;
        }
    }

    public TeamListings getTeamListings(int season, Integer teamNumber, String eventCode, String districtCode, Integer page) {
        JSONObject response = sendGet(
                formRequest(season, "teams", "",
                        new Parameter("teamNumber", teamNumber),
                        new Parameter("eventCode", eventCode),
                        new Parameter("districtCode", districtCode),
                        new Parameter("page", page)
                        )
        );

        return TeamListings.getFromJson(response);
    }

    public TeamListings getTeamListings(Integer teamNumber, String eventCode, String districtCode, Integer page) {
        if(this.season != 0) {
            return getTeamListings(this.season, teamNumber, eventCode, districtCode, page);
        } else {
            return null;
        }
    }

    public List<Registration> getRegistrations(int season, Integer teamNumber, String eventCode) {
        JSONObject response = sendGet(
                formRequest(season, "registrations", "",
                        new Parameter("eventCode", eventCode),
                        new Parameter("teamNumber", teamNumber)
                        )
        );

        List<Registration> registrationList = new ArrayList<>();
        JSONArray registrations = response.getJSONArray("Registrations");

        for(int i = 0; i < registrations.length(); i++) {
            registrationList.add(Registration.getFromJson(registrations.getJSONObject(i)));
        }

        return registrationList;
    }

    public List<Registration> getRegistrations(Integer teamNumber, String eventCode) {
        if(this.season != 0) {
            return getRegistrations(this.season, teamNumber, eventCode);
        } else {
            return null;
        }
    }
}
