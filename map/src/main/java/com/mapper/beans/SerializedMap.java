package com.mapper.beans;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializedMap {

    private String name;
    private int id;
    private String imageSrc;
    private ToolData toolData;
    private String Description;

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

    public String getImageSrc() {
        return imageSrc;
    }
    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public ToolData getToolData() {
        return toolData;
    }
    public void setToolData(ToolData toolData) {
        this.toolData = toolData;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }

    //returns a JSON representation of the object
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}

