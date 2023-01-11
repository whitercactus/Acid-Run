package com.mapper;

import java.sql.Connection;
import java.sql.DriverManager;

public class Config {
    public static final String ADDRESS = "jdbc:sqlite:maps.db";
    public static final String MAPS_DIR = "src/main/resources/static/maps/";
    public static final int MAX_REQUESTED_IDS = 20;
}
