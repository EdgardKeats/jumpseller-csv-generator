package cl.rivendel.csv.model.imgbb;

public class ImgBBResponse {
    private ImgBBData data;
    private boolean success;
    private int status;

    public ImgBBResponse() {
    }

    public ImgBBResponse(ImgBBData data, boolean success, int status) {
        this.data = data;
        this.success = success;
        this.status = status;
    }

    public ImgBBData getData() {
        return data;
    }

    public void setData(ImgBBData data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
