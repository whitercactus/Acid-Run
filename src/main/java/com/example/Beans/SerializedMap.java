package com.example.Beans;

public class SerializedMap {


    public class Brush {
        protected String color;
        protected double thickness;
        protected boolean fill;
        protected String fillColor;
        protected double transparency;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public double getThickness() {
            return thickness;
        }

        public void setThickness(double thickness) {
            this.thickness = thickness;
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

        public class Points {
            protected double[] x;
            protected double[] y;


        }
    }
}
