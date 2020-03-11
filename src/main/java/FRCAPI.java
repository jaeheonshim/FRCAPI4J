import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import structures.Alliance;
import structures.Award;

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
                return new JSONObject(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Alliance> getEventAlliances(int season, String eventCode) {
        JSONObject response = sendGet(String.format("/%d/alliances/%s", season, eventCode));
        List<Alliance> alliances = new ArrayList<>();

        JSONArray jsonArray = response.getJSONArray("Alliances");
        for(int i = 0; i < jsonArray.length(); i++) {
            alliances.add(Alliance.getFromJson(jsonArray.getJSONObject(i)));
        }

        return alliances;
    }

    public List<Alliance> getEventAlliances(String eventCode) {
        if(this.season != 0) {
            return getEventAlliances(this.season, eventCode);
        } else {
            return null;
        }
    }

    public List<Award> getEventAwards(int season, String eventCode, int teamNumber) {
        JSONObject response = sendGet(String.format("/%d/awards/%s/%d", season, eventCode, teamNumber));
        List<Award> awards = new ArrayList<>();

        JSONArray jsonArray = response.getJSONArray("Awards");
        for(int i = 0; i < jsonArray.length(); i++) {
            awards.add(Award.getFromJson(jsonArray.getJSONObject(i)));
        }

        return awards;
    }

    public List<Award> getEventAwards(int season, String eventCode) {
        JSONObject response = sendGet(String.format("/%d/awards/%s", season, eventCode));
        List<Award> awards = new ArrayList<>();

        JSONArray jsonArray = response.getJSONArray("Awards");
        for(int i = 0; i < jsonArray.length(); i++) {
            awards.add(Award.getFromJson(jsonArray.getJSONObject(i)));
        }

        return awards;
    }

    public List<Award> getEventAwards(String eventCode, int teamNumber) {
        if(this.season != 0) {
            return getEventAwards(this.season, eventCode, teamNumber);
        } else {
            return null;
        }
    }

    public List<Award> getEventAwards(String eventCode) {
        if(this.season != 0) {
            return getEventAwards(this.season, eventCode);
        } else {
            return null;
        }
    }
}
