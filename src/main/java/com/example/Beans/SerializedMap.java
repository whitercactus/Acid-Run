package com.example.Beans;

public class SerializedMap {

    public String name;
    public String imageSrc;
    public ToolData toolData;

    public class ToolData {
        public Brush[] brushes;
        public Waypoint[] waypoints;
    }

    public class Brush {
        public boolean fill;
        public String fillColor;
        public double transparency;
        public Points points;

        public class Points {
            public int[] x;
            public int[] y;
        }
    }

    public class Waypoint {
        public int x;
        public int y;
        public String content;
        public String name;
        public int width;
        public int height;
        public boolean active;
    }
}
