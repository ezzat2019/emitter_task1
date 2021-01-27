package com.talabto.emitterbyezzat.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

 public class UserModel implements Parcelable {

    private long id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    protected UserModel(Parcel in) {
       id = in.readLong();
       name = in.readString();
       username = in.readString();
       email = in.readString();
       phone = in.readString();
       website = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
       @Override
       public UserModel createFromParcel(Parcel in) {
          return new UserModel(in);
       }

       @Override
       public UserModel[] newArray(int size) {
          return new UserModel[size];
       }
    };

    @Override
    public String toString() {
       return "UserModel{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", address=" + address +
               ", phone='" + phone + '\'' +
               ", website='" + website + '\'' +
               ", company=" + company +
               '}';
    }

    @SerializedName("id")
    public long getID() { return id; }
    @SerializedName("id")
    public void setID(long value) { this.id = value; }

    @SerializedName("name")
    public String getName() { return name; }
    @SerializedName("name")
    public void setName(String value) { this.name = value; }

    @SerializedName("username")
    public String getUsername() { return username; }
    @SerializedName("username")
    public void setUsername(String value) { this.username = value; }

    @SerializedName("email")
    public String getEmail() { return email; }
    @SerializedName("email")
    public void setEmail(String value) { this.email = value; }

    @SerializedName("address")
    public Address getAddress() { return address; }
    @SerializedName("address")
    public void setAddress(Address value) { this.address = value; }

    @SerializedName("phone")
    public String getPhone() { return phone; }
    @SerializedName("phone")
    public void setPhone(String value) { this.phone = value; }

    @SerializedName("website")
    public String getWebsite() { return website; }
    @SerializedName("website")
    public void setWebsite(String value) { this.website = value; }

    @SerializedName("company")
    public Company getCompany() { return company; }
    @SerializedName("company")
    public void setCompany(Company value) { this.company = value; }

    @Override
    public int describeContents() {
       return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
       parcel.writeLong(id);
       parcel.writeString(name);
       parcel.writeString(username);
       parcel.writeString(email);
       parcel.writeString(phone);
       parcel.writeString(website);
    }
 }

// Address.java


 class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @SerializedName("street")
    public String getStreet() { return street; }
    @SerializedName("street")
    public void setStreet(String value) { this.street = value; }

    @SerializedName("suite")
    public String getSuite() { return suite; }
    @SerializedName("suite")
    public void setSuite(String value) { this.suite = value; }

    @SerializedName("city")
    public String getCity() { return city; }
    @SerializedName("city")
    public void setCity(String value) { this.city = value; }

    @SerializedName("zipcode")
    public String getZipcode() { return zipcode; }
    @SerializedName("zipcode")
    public void setZipcode(String value) { this.zipcode = value; }

    @SerializedName("geo")
    public Geo getGeo() { return geo; }
    @SerializedName("geo")
    public void setGeo(Geo value) { this.geo = value; }
}

// Geo.java



 class Geo {
    private String lat;
    private String lng;

    @SerializedName("lat")
    public String getLat() { return lat; }
    @SerializedName("lat")
    public void setLat(String value) { this.lat = value; }

    @SerializedName("lng")
    public String getLng() { return lng; }
    @SerializedName("lng")
    public void setLng(String value) { this.lng = value; }
}

// Company.java



 class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    @SerializedName("name")
    public String getName() { return name; }
    @SerializedName("name")
    public void setName(String value) { this.name = value; }

    @SerializedName("catchPhrase")
    public String getCatchPhrase() { return catchPhrase; }
    @SerializedName("catchPhrase")
    public void setCatchPhrase(String value) { this.catchPhrase = value; }

    @SerializedName("bs")
    public String getBs() { return bs; }
    @SerializedName("bs")
    public void setBs(String value) { this.bs = value; }
}
