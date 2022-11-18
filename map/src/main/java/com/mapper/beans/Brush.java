package com.mapper.beans;

import java.io.Serializable;

public class Brush implements Serializable {
    String color;
    public Points points;
    public double thickness;
    private static final long serialVersionUID = 1L;

    public class Points implements Serializable {
        public double[][] x;
        public double[][] y;
        private static final long serialVersionUID = 1L;
    }
}
