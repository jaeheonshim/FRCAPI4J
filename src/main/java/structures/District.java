package structures;

import org.json.JSONObject;

public class District {
    private String code;
    private String name;

    public District(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static District getFromJson(JSONObject jsonObject) {
        return new District(jsonObject.optString("code"), jsonObject.optString("name"));
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
