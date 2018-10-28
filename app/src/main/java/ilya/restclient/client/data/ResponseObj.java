package ilya.restclient.client.data;

import com.google.gson.annotations.SerializedName;

public class ResponseObj<T> {
    @SerializedName("_meta")
    private Meta meta;
    private T result;

    public Meta getMeta() {
        return meta;
    }

    public T getResult() {
        return result;
    }
}
