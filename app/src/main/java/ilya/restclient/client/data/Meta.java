package ilya.restclient.client.data;

public class Meta {
    private boolean success;
    private int code;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
