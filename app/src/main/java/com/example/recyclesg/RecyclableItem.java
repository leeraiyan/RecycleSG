package com.example.recyclesg;

public class RecyclableItem {
    private String name;
    private String material;
    private String serial;
    private Integer volume;
    private Integer points;
    private Boolean isVerified;

    public RecyclableItem(String name, String material, String serial, Integer volume, Integer points, Boolean isVerified) {
        this.name = name;
        this.material = material;
        this.serial = serial;
        this.volume = volume;
        this.points = points;
        this.isVerified = isVerified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }
}
