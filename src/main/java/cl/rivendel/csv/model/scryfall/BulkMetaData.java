package cl.rivendel.csv.model.scryfall;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BulkMetaData {
    private String object;
    @JsonProperty("has_more")
    private boolean hasMore;
    private List<BulkData> data;
}
