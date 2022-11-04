package com.example.beans;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializedMap {

    public String name;
    public String imageSrc;
    public ToolData toolData;

    public class ToolData {
        public Brush[] brushes;
        public Waypoint[] waypoints;
    }

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
