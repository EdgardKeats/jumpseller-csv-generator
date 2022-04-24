package cl.rivendel.csv.model.imgbb;

public class ImgBBRequest {
    private String key;
    private String image;
    private String name;
    private int expiration;

    public ImgBBRequest() {
    }

    public ImgBBRequest(String key, String image, String name, int expiration) {
        this.key = key;
        this.image = image;
        this.name = name;
        this.expiration = expiration;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }
}
