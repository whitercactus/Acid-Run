package com.mapper.beans;

import java.io.Serializable;

public class Waypoint implements Serializable {
    public double x;
    public double y;
    public String content;
    public String name;
    public int width;
    public int height;
    public boolean active;
    private static final long serialVersionUID = 1L;
}
