package cl.rivendel.csv.model.scryfall;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SetListObject {
    private List<Set> data;
    @JsonProperty("has_more")
    private boolean hasMore;
    private String nextPage;
    @JsonProperty("total_cards")
    private Integer totalCards;
    private List<String> warnings;

    public List<Set> getData() {
        return data;
    }

    public void setData(List<Set> data) {
        this.data = data;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getTotalCards() {
        return totalCards;
    }

    public void setTotalCards(Integer totalCards) {
        this.totalCards = totalCards;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}
