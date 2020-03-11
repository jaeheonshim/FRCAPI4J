package structures;

import org.json.JSONObject;

public class Alliance {
    private String name;
    private int number;
    private int captain;
    private int round1;
    private int round2;
    private int round3;
    private int backup;
    private int backupReplaced;

    public Alliance(String name, int number, int captain, int round1, int round2, int round3, int backup, int backupReplaced) {
        this.name = name;
        this.number = number;
        this.captain = captain;
        this.round1 = round1;
        this.round2 = round2;
        this.round3 = round3;
        this.backup = backup;
        this.backupReplaced = backupReplaced;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getCaptain() {
        return captain;
    }

    public int getRound1() {
        return round1;
    }

    public int getRound2() {
        return round2;
    }

    public int getRound3() {
        return round3;
    }

    public int getBackup() {
        return backup;
    }

    public int getBackupReplaced() {
        return backupReplaced;
    }

    public static Alliance getFromJson(JSONObject jsonObject) {
        return new Alliance(jsonObject.optString("name"), jsonObject.optInt("number"), jsonObject.optInt("captain"), jsonObject.optInt("round1"), jsonObject.optInt("round2"), jsonObject.optInt("round3"), jsonObject.optInt("backup"), jsonObject.optInt("backupReplaced"));
    }
}
