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

class ToolData {
    private Brush[] brushes;
    private Waypoint[] waypoints;

    public Brush[] getBrushes() {
        return brushes;
    }
    public void setBrushes(Brush[] brushes) {
        this.brushes = brushes;
    }

    public Waypoint[] getWaypoints() {
        return waypoints;
    }
    public void setWaypoints(Waypoint[] waypoints) {
        this.waypoints = waypoints;
    }
}

class Brush {
    private String color;
    private boolean fill;
    private String fillColor;
    private double transparency;
    private Points points;
    private double thickness;

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFill() {
        return fill;
    }
    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public String getFillColor() {
        return fillColor;
    }
    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public double getTransparency() {
        return transparency;
    }
    public void setTransparency(double transparency) {
        this.transparency = transparency;
    }

    public Points getPoints() {
        return points;
    }
    public void setPoints(Points points) {
        this.points = points;
    }

    public double getThickness() {
        return thickness;
    }
    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public class Points {
        private double[][] x;
        private double[][] y;

        public double[][] getX() {
            return x;
        }
        public void setX(double[][] x) {
            this.x = x;
        }

        public double[][] getY() {
            return y;
        }
        public void setY(double[][] y) {
            this.y = y;
        }
    }
}

class Waypoint {
    private double x;
    private double y;
    private String content;
    private String name;
    private int width;
    private int height;
    private boolean active;

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
