package com.jaeheonshim.frcapi4j;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import com.jaeheonshim.frcapi4j.structures.*;
import com.jaeheonshim.frcapi4j.structures.listings.*;

import java.io.IOException;
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

    /**
     * Returns details about alliance selection at a particular event in a particular season
     *
     * @param season    **Required** Numeric year of the event from which the event alliances are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
     * @param eventCode **Required** Case insensitive alphanumeric eventCode of the event from which the alliance selection results are requested. Must be at least 3 characters.
     * @return List containing alliances from api.
     */
    public List<Alliance> getEventAlliances(int season, @NotNull String eventCode) {
        JSONObject response = sendGet(formRequest(season, "alliances", eventCode));
        List<Alliance> alliances = new ArrayList<>();

        JSONArray jsonArray = response.getJSONArray("Alliances");
        for (int i = 0; i < jsonArray.length(); i++) {
            alliances.add(Alliance.getFromJson(jsonArray.getJSONObject(i)));
        }

        return alliances;
    }

    /**
     * Returns details about alliance selection at a particular event in a particular season
     *
     * @param eventCode **Required** Case insensitive alphanumeric eventCode of the event from which the alliance selection results are requested. Must be at least 3 characters.
     * @return List containing alliances from api. Returns null if season not specified in FRCAPI object.
     */
    public List<Alliance> getEventAlliances(@NotNull String eventCode) {
        if (this.season != 0) {
            return getEventAlliances(this.season, eventCode);
        } else {
            return null;
        }
    }

    /**
     * Returns details about awards presented at a particular event in a particular season.
     *
     * @param season     **Required** Numeric year of the event from which the award listings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
     * @param eventCode  **Optional** Optional case insensitive alphanumeric eventCode of the event from which the awards are requested. Must be at least 3 characters.
     * @param teamNumber **Optional** Optional teamNumber to search for within the results.
     * @return List containing awards from api.
     */
    public List<Award> getEventAwards(int season, @NotNull String eventCode, Integer teamNumber) {
        JSONObject response = sendGet(formRequest(season, "awards", eventCode, new Parameter("teamNumber", teamNumber)));
        List<Award> awards = new ArrayList<>();

        JSONArray jsonArray = response.getJSONArray("Awards");
        for (int i = 0; i < jsonArray.length(); i++) {
            awards.add(Award.getFromJson(jsonArray.getJSONObject(i)));
        }

        return awards;
    }

    /**
     * Returns details about awards presented at a particular event in a particular season.
     *
     * @param eventCode  **Optional** Optional case insensitive alphanumeric eventCode of the event from which the awards are requested. Must be at least 3 characters.
     * @param teamNumber **Optional** Optional teamNumber to search for within the results.
     * @return List containing awards from api. Returns null if season not specified in FRCAPI object.
     */
    public List<Award> getEventAwards(String eventCode, Integer teamNumber) {
        if (this.season != 0) {
            return getEventAwards(this.season, eventCode, teamNumber);
        } else {
            return null;
        }
    }

    /**
     * Returns the match results for all matches of a particular event in a particular season.
     *
     * @param season          **Required** Numeric year of the event from which the match results are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
     * @param eventCode       **Required** Case insensitive alphanumeric eventCode of the event from which the results are requested. Must be at least 3 characters.
     * @param tournamentLevel **Optional** Optional tournamentLevel of desired match results.
     * @param teamNumber      **Optional** Optional teamNumber to search for within the results. Only returns match results in which the requested team was a participant.
     * @param matchNumber     **Optional** Optional specific single matchNumber of result.
     * @param start           **Optional** Optional start match number for subset of results to return.
     * @param end             **Optional** Optional end match number for subset of results to return (inclusive).
     * @return List containing MatchResults from api.
     */
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

    /**
     * Returns the match results for all matches of a particular event in a particular season.
     *
     * @param eventCode       **Required** Case insensitive alphanumeric eventCode of the event from which the results are requested. Must be at least 3 characters.
     * @param tournamentLevel **Optional** Optional tournamentLevel of desired match results.
     * @param teamNumber      **Optional** Optional teamNumber to search for within the results. Only returns match results in which the requested team was a participant.
     * @param matchNumber     **Optional** Optional specific single matchNumber of result.
     * @param start           **Optional** Optional start match number for subset of results to return.
     * @param end             **Optional** Optional end match number for subset of results to return (inclusive).
     * @return List containing MatchResults from api. Returns null if season not specified in FRCAPI object.
     */
    public List<MatchResult> getMatchResults(@NotNull String eventCode, TournamentLevel tournamentLevel, Integer teamNumber, Integer matchNumber, Integer start, Integer end) {
        if (this.season != 0) {
            return getMatchResults(this.season, eventCode, tournamentLevel, teamNumber, matchNumber, start, end);
        } else {
            return null;
        }
    }

    /**
     * Returns team ranking detail from a particular event in a particular season.
     *
     * @param season     **Required** Numeric year of the event from which the rankings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
     * @param eventCode  **Required** Case insensitive alphanumeric eventCode of the event from which the rankings are requested. Must be at least 3 characters.
     * @param teamNumber **Optional** Optional team number of the team whose ranking is requested.
     * @param top        **Optional** Optional number of requested top ranked teams to return in result.
     * @return List containing Rankings from api.
     */
    public List<Ranking> getEventRankings(int season, @NotNull String eventCode, Integer teamNumber, Integer top) {
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

    /**
     * Returns team ranking detail from a particular event in a particular season.
     *
     * @param eventCode  **Required** Case insensitive alphanumeric eventCode of the event from which the rankings are requested. Must be at least 3 characters.
     * @param teamNumber **Optional** Optional team number of the team whose ranking is requested.
     * @param top        **Optional** Optional number of requested top ranked teams to return in result.
     * @return List containing Rankings from api. Returns null if season not specified in FRCAPI object.
     */
    public List<Ranking> getEventRankings(@NotNull String eventCode, Integer teamNumber, Integer top) {
        if (this.season != 0) {
            return getEventRankings(this.season, eventCode, teamNumber, top);
        } else {
            return null;
        }
    }

    /**
     * Returns team ranking detail from a particular team in a particular season
     *
     * @param season       **Required** Numeric year of the event from which the rankings are requested. Must be 4 digits and greater than or equal to 2017, and less than or equal to the current year.
     * @param districtCode **Required** Case insensitive alphanumeric districtCode of the district from which the rankings are requested. Must be at least 2 characters.
     * @param teamNumber   **Optional** Optional team number of the team whose ranking is requested.
     * @param top          **Optional** Optional number of requested top ranked teams to return in result.
     * @param page         **Optional** Numeric page of results to return. If not included, page 1 will be returned.
     * @return DistrictRankings object from api.
     */
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

    /**
     * Returns team ranking detail from a particular team in a particular season
     *
     * @param districtCode **Required** Case insensitive alphanumeric districtCode of the district from which the rankings are requested. Must be at least 2 characters.
     * @param teamNumber   **Optional** Optional team number of the team whose ranking is requested.
     * @param top          **Optional** Optional number of requested top ranked teams to return in result.
     * @param page         **Optional** Numeric page of results to return. If not included, page 1 will be returned.
     * @return DistrictRankings object from api. Returns null if season not specified in FRCAPI object.
     */
    public DistrictRankings getDistrictRankings(@NotNull String districtCode, Integer teamNumber, Integer top, Integer page) {
        if (this.season != 0) {
            return getDistrictRankings(this.season, districtCode, teamNumber, top, page);
        } else {
            return null;
        }
    }

    /**
     * Returns the match schedule for the desired tournament level of a particular event in a particular season.
     *
     * @param season          **Required** Numeric year of the event from which the schedule is requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
     * @param eventCode       **Required** Case insensitive alphanumeric eventCode of the event from which the schedule are requested. Must be at least 3 characters.
     * @param tournamentLevel **Required** tournamentLevel of desired match schedule.
     * @param teamNumber      **Optional** Optional teamNumber to search for within the schedule. Only returns matches in which the requested team participated.
     * @param start           **Optional** Optional start match number for subset of results to return (inclusive).
     * @param end             **Optional** Optional end match number for subset of results to return (inclusive).
     * @return List containing ScheduledMatches from api.
     */
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

    /**
     * Returns the match schedule for the desired tournament level of a particular event in a particular season.
     *
     * @param eventCode       **Required** Case insensitive alphanumeric eventCode of the event from which the schedule are requested. Must be at least 3 characters.
     * @param tournamentLevel **Required** tournamentLevel of desired match schedule.
     * @param teamNumber      **Optional** Optional teamNumber to search for within the schedule. Only returns matches in which the requested team participated.
     * @param start           **Optional** Optional start match number for subset of results to return (inclusive).
     * @param end             **Optional** Optional end match number for subset of results to return (inclusive).
     * @return List containing ScheduledMatches from api. Returns null if season not specified in FRCAPI object.
     */
    public List<ScheduledMatch> getEventSchedule(@NotNull String eventCode, TournamentLevel tournamentLevel, Integer teamNumber, Integer start, Integer end) {
        if (this.season != 0) {
            return getEventSchedule(this.season, eventCode, tournamentLevel, teamNumber, start, end);
        } else {
            return null;
        }
    }

    /**
     * Returns the match schedule for the desired tournament level of a particular event in a particular season in the hybrid format
     *
     * @param season          **Required** Numeric year of the event from which the hybrid schedule is requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
     * @param eventCode       **Required** Case insensitive alphanumeric eventCode of the event from which the hybrid schedule are requested. Must be at least 3 characters.
     * @param tournamentLevel **Required** tournamentLevel of desired hybrid schedule.
     * @param start           **Optional** Optional start match number for subset of results to return (inclusive).
     * @param end             **Optional** Optional end match number for subset of results to return (inclusive).
     * @return List containing HybridSchedules from api.
     */
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

    /**
     * Returns the match schedule for the desired tournament level of a particular event in a particular season in the hybrid format
     *
     * @param eventCode       **Required** Case insensitive alphanumeric eventCode of the event from which the hybrid schedule are requested. Must be at least 3 characters.
     * @param tournamentLevel **Required** tournamentLevel of desired hybrid schedule.
     * @param start           **Optional** Optional start match number for subset of results to return (inclusive).
     * @param end             **Optional** Optional end match number for subset of results to return (inclusive).
     * @return List containing HybridSchedules from api. Returns null if season not specified in FRCAPI object.
     */
    public List<HybridSchedule> getHybridSchedule(@NotNull String eventCode, @NotNull TournamentLevel tournamentLevel, Integer start, Integer end) {
        if (this.season != 0) {
            return getHybridSchedule(this.season, eventCode, tournamentLevel, start, end);
        } else {
            return null;
        }
    }

    /**
     * Returns a high level glance of a particular FRC season
     *
     * @param season **Required** Numeric year of the event from which the season summary is requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
     * @return Season object containing information about the requested FRC season.
     */
    public Season getSeason(int season) {
        JSONObject response = sendGet(formRequest(season, "", ""));
        return Season.getFromJson(response);
    }

    /**
     * Returns a high level glance of a particular FRC season
     *
     * @return Season object containing information about the requested FRC season. Returns null if season not specified in FRCAPI object. Returns null if season not specified in FRCAPI object.
     */
    public Season getSeason() {
        if (this.season != 0) {
            return getSeason(this.season);
        } else {
            return null;
        }
    }

    /**
     * Returns all FRC official district and regional events in a particular season
     *
     * @param season          **Required** Numeric year from which the event listings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
     * @param eventCode       **Optional** Case insensitive alphanumeric eventCode of the event about which details are requested.
     * @param teamNumber      **Optional** Numeric teamNumber of the team from which the attending event listings are requested.
     * @param districtCode    **Optional** Case insensitive districtCode of the district from which event listings are requested.
     * @param excludeDistrict **Optional** Boolean to specify whether or not to exclude district events in the event listings. true means exclude, but if no value is specified, false will be used (include district events). Excluding district events also excludes district championships.
     * @return List containing Events from api.
     */
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

        for (int i = 0; i < events.length(); i++) {
            eventList.add(Event.getFromJson(events.getJSONObject(i)));
        }

        return eventList;
    }

    /**
     * Returns all FRC official district and regional events in a particular season
     *
     * @param eventCode       **Optional** Case insensitive alphanumeric eventCode of the event about which details are requested.
     * @param teamNumber      **Optional** Numeric teamNumber of the team from which the attending event listings are requested.
     * @param districtCode    **Optional** Case insensitive districtCode of the district from which event listings are requested.
     * @param excludeDistrict **Optional** Boolean to specify whether or not to exclude district events in the event listings. true means exclude, but if no value is specified, false will be used (include district events). Excluding district events also excludes district championships.
     * @return List containing Events from api. Returns null if season not specified in FRCAPI object.
     */
    public List<Event> getEvents(String eventCode, Integer teamNumber, String districtCode, Boolean excludeDistrict) {
        if (this.season != 0) {
            return getEvents(this.season, eventCode, teamNumber, districtCode, excludeDistrict);
        } else {
            return null;
        }
    }

    /**
     * Returns all FRC official districts of a particular season.
     *
     * @param season **Required** Numeric year from which the district listings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
     * @return List containing Districts from api.
     */
    public List<District> getDistricts(int season) {
        JSONObject response = sendGet(formRequest(season, "districts", ""));

        List<District> districtList = new ArrayList<>();
        JSONArray districts = response.getJSONArray("districts");
        for (int i = 0; i < districts.length(); i++) {
            districtList.add(District.getFromJson(districts.getJSONObject(i)));
        }

        return districtList;
    }

    /**
     * Returns all FRC official districts of a particular season.
     *
     * @return List containing Districts from api. Returns null if season not specified in FRCAPI object.
     */
    public List<District> getDistricts() {
        if (this.season != 0) {
            return getDistricts(this.season);
        } else {
            return null;
        }
    }

    /**
     * Returns all FRC official teams in a particular season
     *
     * @param season       **Required** Numeric year from which the team listings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
     * @param teamNumber   **Optional** Numeric teamNumber of the team about which information is requested. Must be 1 to 4 digits.
     * @param eventCode    **Optional** Case insensitive alphanumeric eventCode of the event from which details are requested.
     * @param districtCode **Optional** Case insensitive districtCode code of the district from which team listings are requested.
     * @param page         **Optional** Numeric page of results to return. If not included, page 1 will be returned.
     * @return TeamListings object containing teams requested from api.
     */
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

    /**
     * Returns all FRC official teams in a particular season
     *
     * @param teamNumber   **Optional** Numeric teamNumber of the team about which information is requested. Must be 1 to 4 digits.
     * @param eventCode    **Optional** Case insensitive alphanumeric eventCode of the event from which details are requested.
     * @param districtCode **Optional** Case insensitive districtCode code of the district from which team listings are requested.
     * @param page         **Optional** Numeric page of results to return. If not included, page 1 will be returned.
     * @return TeamListings object containing teams requested from api. Returns null if season not specified in FRCAPI object.
     */
    public TeamListings getTeamListings(Integer teamNumber, String eventCode, String districtCode, Integer page) {
        if (this.season != 0) {
            return getTeamListings(this.season, teamNumber, eventCode, districtCode, page);
        } else {
            return null;
        }
    }

    /**
     * Returns all registrations of teams in a particular season at particular events.
     *
     * @param season     **Required** Numeric year from which the team listings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
     * @param teamNumber **Optional** Numeric teamNumber of the team about which information is requested. Must be 1 to 4 digits.
     * @param eventCode  **Optional** Case insensitive alphanumeric eventCode of the event from which details are requested.
     * @return Returns list containing Registrations from api.
     */
    public List<Registration> getRegistrations(int season, Integer teamNumber, String eventCode) {
        JSONObject response = sendGet(
                formRequest(season, "registrations", "",
                        new Parameter("eventCode", eventCode),
                        new Parameter("teamNumber", teamNumber)
                )
        );

        List<Registration> registrationList = new ArrayList<>();
        JSONArray registrations = response.getJSONArray("Registrations");

        for (int i = 0; i < registrations.length(); i++) {
            registrationList.add(Registration.getFromJson(registrations.getJSONObject(i)));
        }

        return registrationList;
    }

    /**
     * Returns all registrations of teams in a particular season at particular events.
     *
     * @param teamNumber **Optional** Numeric teamNumber of the team about which information is requested. Must be 1 to 4 digits.
     * @param eventCode  **Optional** Case insensitive alphanumeric eventCode of the event from which details are requested.
     * @return Returns list containing Registrations from api. Returns null if season not specified in FRCAPI object
     */
    public List<Registration> getRegistrations(Integer teamNumber, String eventCode) {
        if (this.season != 0) {
            return getRegistrations(this.season, teamNumber, eventCode);
        } else {
            return null;
        }
    }

    /**
     * This endpoint applies only to the 2018 or later seasons. Requests for other seasons will result in a Bad Season error. The team avatar listings API returns all FRC official teams in a particular season with, if applicable, their Avatar.
     *
     * @param season     **Required** Numeric year from which the team listings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
     * @param teamNumber **Optional** Numeric teamNumber of the team about which information is requested. Must be 1 to 4 digits.
     * @param eventCode  **Optional** Case insensitive alphanumeric eventCode of the event from which details are requested.
     * @param page       **Optional** Numeric page of results to return. If not included, page 1 will be returned.
     * @return Returns TeamAvatarListings containing Team Avatars requested from api.
     */
    public TeamAvatarListings getAvatarListings(int season, Integer teamNumber, String eventCode, Integer page) {
        JSONObject response = sendGet(
                formRequest(season, "avatars", "",
                        new Parameter("eventCode", eventCode),
                        new Parameter("teamNumber", teamNumber),
                        new Parameter("page", page)
                )
        );

        return TeamAvatarListings.getFromJson(response);
    }

    /**
     * This endpoint applies only to the 2018 or later seasons. Requests for other seasons will result in a Bad Season error. The team avatar listings API returns all FRC official teams in a particular season with, if applicable, their Avatar.
     *
     * @param teamNumber **Optional** Numeric teamNumber of the team about which information is requested. Must be 1 to 4 digits.
     * @param eventCode  **Optional** Case insensitive alphanumeric eventCode of the event from which details are requested.
     * @param page       **Optional** Numeric page of results to return. If not included, page 1 will be returned.
     * @return Returns TeamAvatarListings containing Team Avatars requested from api. Returns null if season not specified in FRCAPI object
     */
    public TeamAvatarListings getAvatarListings(Integer teamNumber, String eventCode, Integer page) {
        if (this.season != 0) {
            return getAvatarListings(this.season, teamNumber, eventCode, page);
        } else {
            return null;
        }
    }
}
