package cl.rivendel.csv.model.scryfall;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AllBulkData {
    private String object;
    @JsonProperty("has_more")
    private boolean hasMore;
    private List<BulkData> data;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<BulkData> getData() {
        return data;
    }

    public void setData(List<BulkData> data) {
        this.data = data;
    }
}
