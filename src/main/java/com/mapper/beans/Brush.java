package com.mapper.beans;

public class Brush {
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
