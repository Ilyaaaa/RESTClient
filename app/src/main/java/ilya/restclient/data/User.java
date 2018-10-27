package ilya.restclient.data;

public class User {
    private long id;
    private String name;
    private String gender;
    private String dob;
    private String email;
    private String phone;
    private String website;
    private String address;
    private String status;
    private Links _links;

    public User(String name, String gender, String dob, String email, String phone, String website, String address, String status, Links _links) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.address = address;
        this.status = status;
        this._links = _links;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public Links get_links() {
        return _links;
    }

    private class Links {
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

        private class Link {
            private String href;

            public Link(String href) {
                this.href = href;
            }

            public String getHref() {
                return href;
            }
        }
    }
}
