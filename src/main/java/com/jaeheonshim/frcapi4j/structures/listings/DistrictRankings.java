package com.jaeheonshim.frcapi4j.structures.listings;

import org.json.JSONArray;
import org.json.JSONObject;
import com.jaeheonshim.frcapi4j.structures.TeamDistrictRanking;

import java.util.ArrayList;
import java.util.List;

public class DistrictRankings {
    private int pageCurrent;
    private int pageTotal;
    private int rankingCountPage;
    private int rankingCountTotal;
    private List<TeamDistrictRanking> districtRanks;

    public DistrictRankings(int pageCurrent, int pageTotal, int rankingCountPage, int rankingCountTotal, List<TeamDistrictRanking> districtRanks) {
        this.pageCurrent = pageCurrent;
        this.pageTotal = pageTotal;
        this.rankingCountPage = rankingCountPage;
        this.rankingCountTotal = rankingCountTotal;
        this.districtRanks = districtRanks;
    }

    public static DistrictRankings getFromJson(JSONObject jsonObject) {
        List<TeamDistrictRanking> districtRankingList = new ArrayList<>();
        JSONArray districtRankings = jsonObject.getJSONArray("districtRanks");
        for(int i = 0; i < districtRankings.length(); i++) {
            districtRankingList.add(TeamDistrictRanking.getFromJson(districtRankings.getJSONObject(i)));
        }

        return new DistrictRankings(jsonObject.optInt("pageCurrent"), jsonObject.optInt("pageTotal"), jsonObject.optInt("rankingCountPage"), jsonObject.optInt("rankingCountTotal"), districtRankingList);
    }

    public int getPageCurrent() {
        return pageCurrent;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public int getRankingCountPage() {
        return rankingCountPage;
    }

    public int getRankingCountTotal() {
        return rankingCountTotal;
    }

    public List<TeamDistrictRanking> getDistrictRanks() {
        return districtRanks;
    }
}
