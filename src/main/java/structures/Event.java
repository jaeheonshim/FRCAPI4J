package structures;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Event {
    private String code;
    private String divisionCode;
    private String name;
    private String type;
    private String districtCode;
    private String venue;
    private String address;
    private String city;
    private String stateprov;
    private String country;
    private String website;
    private List<String> webcasts;
    private String timezone;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

    public Event(String code, String divisionCode, String name, String type, String districtCode, String venue, String address, String city, String stateprov, String country, String website, List<String> webcasts, String timezone, LocalDateTime dateStart, LocalDateTime dateEnd) {
        this.code = code;
        this.divisionCode = divisionCode;
        this.name = name;
        this.type = type;
        this.districtCode = districtCode;
        this.venue = venue;
        this.address = address;
        this.city = city;
        this.stateprov = stateprov;
        this.country = country;
        this.website = website;
        this.webcasts = webcasts;
        this.timezone = timezone;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public static Event getFromJson(JSONObject jsonObject) {
        return new Event(jsonObject.optString("code"),
                jsonObject.optString("divisionCode"),
                jsonObject.optString("name"),
                jsonObject.optString("type"),
                jsonObject.optString("districtCode"),
                jsonObject.optString("venue "),
                jsonObject.optString("address"),
                jsonObject.optString("city"),
                jsonObject.optString("stateprov"),
                jsonObject.optString("country"),
                jsonObject.optString("website"),
                jsonObject.optJSONArray("webcasts").toList().stream().map(s -> s.toString()).collect(Collectors.toList()),
                jsonObject.optString("timezone"),
                LocalDateTime.parse(jsonObject.optString("dateStart")),
                LocalDateTime.parse(jsonObject.optString("dateEnd")));
    }

    public String getCode() {
        return code;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public String getVenue() {
        return venue;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getStateprov() {
        return stateprov;
    }

    public String getCountry() {
        return country;
    }

    public String getWebsite() {
        return website;
    }

    public List<String> getWebcasts() {
        return webcasts;
    }

    public String getTimezone() {
        return timezone;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }
}
