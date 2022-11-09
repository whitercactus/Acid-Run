package com.mapper.beans;

public class Brush {
    String color;
    public Points points;
    public double thickness;

    public class Points {
        public double[][] x;
        public double[][] y;
    }
}
