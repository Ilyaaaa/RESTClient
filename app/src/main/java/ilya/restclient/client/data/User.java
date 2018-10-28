package ilya.restclient.client.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {
    private long id;
    private String name;
    private String gender;
    private Date dob;
    private String email;
    private String phone;
    private String website;
    private String address;
    private String status;
    @SerializedName("_links")
    private Links links;

    public User(String name, String gender, Links links) {
        this.name = name;
        this.gender = gender;
        this.links = links;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getDob() {
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

    public Links getLinks() {
        return links;
    }
}
