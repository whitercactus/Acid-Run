package com.example.Beans;

public class SerializedMap {

    public String src = "none";
    public ToolData toolData;

    public SerializedMap() {

    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public class ToolData {
        public int num;
    }
}
