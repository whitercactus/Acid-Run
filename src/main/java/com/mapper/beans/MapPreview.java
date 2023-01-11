package com.mapper.beans;

public class MapPreview {
     public MapPreview() {

     }

     public MapPreview(SerializedMap serMap) {
        this.imageSrc = serMap.getImageSrc();
        this.name = serMap.getName();
        this.id = serMap.getId();
        this.description = serMap.getDescription();
     }

    private String imageSrc;
    private String name;
    private int id;
    private String description;

    public String getImageSrc() {
        return imageSrc;
    }
    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
