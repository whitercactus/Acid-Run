package com.mapper.beans;

public class Brush {
    String color;
    public boolean fill;
    public String fillColor;
    public double transparency;
    public Points points;
    public double thickness;

    public class Points {
        public double[][] x;
        public double[][] y;
    }
}
