package com.lbozo.AcidRun.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;

public class SerializedMap implements Serializable {

    public String name;
    public String imageSrc;
    public ToolData toolData;
    private static final long serialVersionUID = 1L;

    public class ToolData implements Serializable {
        public Brush[] brushes;
        public Waypoint[] waypoints;
        private static final long serialVersionUID = 1L;
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
