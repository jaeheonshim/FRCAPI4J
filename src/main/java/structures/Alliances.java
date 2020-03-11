package structures;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Alliances {
    private int count;
    private List<Alliance> alliances;

    public Alliances(int count, List<Alliance> alliances) {
        this.count = count;
        this.alliances = alliances;
    }

    public int getCount() {
        return count;
    }

    public List<Alliance> getAlliances() {
        return alliances;
    }

    public static Alliances getFromJson(JSONObject jsonObject) {
        int count = jsonObject.optInt("count");
        List<Alliance> alliances = new ArrayList<>();

        JSONArray jsonArray = jsonObject.getJSONArray("Alliances");
        for(int i = 0; i < jsonArray.length(); i++) {
            alliances.add(Alliance.getFromJson(jsonArray.getJSONObject(i)));
        }

        return new Alliances(count, alliances);
    }
}
