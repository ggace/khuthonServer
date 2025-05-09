package org.khuthon.agriserver.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Land {
    private int id;
    private int latitude;
    private int longitude;
    private String userName;
    private String landName;
    private String img;
    private String contents;
    private String phoneNumber;
    private int price;

    public int getId() {
        return id;
    }
    public int getLatitude() {
        return latitude;
    }
    public int getLongitude() {
        return longitude;
    }
    public String getUserName() {
        return userName;
    }
    public String getContents() {
        return contents;
    }
    public String getImg() {
        return img;
    }
    public String getLandName() {
        return landName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public int getPrice() {
        return price;
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public void setLandName(String landName) {
        this.landName = landName;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
