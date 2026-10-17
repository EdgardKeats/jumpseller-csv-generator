package cl.rivendel.csv.model.scryfall;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class BulkData {
    private String object;
    private String id;
    private String type;
    private String updatedAt;
    private String uri;
    private String name;
    private String description;
    private long compressedSize;
    private String jsonlDownloadUri;

    @Override
    public String toString() {
        return "BulkData{" +
                "object='" + object + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", description='" + description + '\'' +
                ", uri='" + uri + '\'' +
                ", name='" + name + '\'' +
                ", compressedSize=" + compressedSize +
                ", jsonlDownloadUri='" + jsonlDownloadUri + '\'' +
                '}';
    }

}
