package ilya.restclient.client.data;

public class Links {
    private Link self;
    private Link avatar;

    public Links(Link self, Link avatar) {
        this.self = self;
        this.avatar = avatar;
    }

    public Link getSelf() {
        return self;
    }

    public Link getAvatar() {
        return avatar;
    }
}
