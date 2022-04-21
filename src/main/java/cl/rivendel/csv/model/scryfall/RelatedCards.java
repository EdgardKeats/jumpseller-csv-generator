package cl.rivendel.csv.model.scryfall;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class RelatedCards {
    private UUID id;
    private String object;
    private String component;
    private String name;
    @JsonProperty("type_line")
    private String typeLine;
    private String uri;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeLine() {
        return typeLine;
    }

    public void setTypeLine(String typeLine) {
        this.typeLine = typeLine;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
