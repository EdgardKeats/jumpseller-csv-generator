package cl.rivendel.csv.model.imgbb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImgBBData {
    private String id;
    private String title;
    @JsonProperty("url_viewer")
    private String urlViewer;
    private String url;
    @JsonProperty("display_url")
    private String displayURL;
    private String width;
    private String height;
    private String size;
    private String time;
    private String expiration;
    private ImgBBImage image;
    private ImgBBImage thumb;
    private ImgBBImage medium;
    @JsonProperty("delete_url")
    private String deleteUrl;

    public ImgBBData() {
    }

    public ImgBBData(String id, String title, String urlViewer, String url, String displayURL, String width, String height, String size, String time, String expiration, ImgBBImage image, ImgBBImage thumb, ImgBBImage medium, String deleteUrl) {
        this.id = id;
        this.title = title;
        this.urlViewer = urlViewer;
        this.url = url;
        this.displayURL = displayURL;
        this.width = width;
        this.height = height;
        this.size = size;
        this.time = time;
        this.expiration = expiration;
        this.image = image;
        this.thumb = thumb;
        this.medium = medium;
        this.deleteUrl = deleteUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlViewer() {
        return urlViewer;
    }

    public void setUrlViewer(String urlViewer) {
        this.urlViewer = urlViewer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisplayURL() {
        return displayURL;
    }

    public void setDisplayURL(String displayURL) {
        this.displayURL = displayURL;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public ImgBBImage getImage() {
        return image;
    }

    public void setImage(ImgBBImage image) {
        this.image = image;
    }

    public ImgBBImage getThumb() {
        return thumb;
    }

    public void setThumb(ImgBBImage thumb) {
        this.thumb = thumb;
    }

    public ImgBBImage getMedium() {
        return medium;
    }

    public void setMedium(ImgBBImage medium) {
        this.medium = medium;
    }

    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }
}
