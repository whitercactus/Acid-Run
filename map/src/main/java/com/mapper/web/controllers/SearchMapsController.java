package com.mapper.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.Config;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
public class SearchMapsController {
    private static final String[] IGNORED_KEYWORDS = new String[]{"map"};

    @RequestMapping(value = "/searchmap", method = RequestMethod.GET)
    @ResponseBody
    public String[] saveMap(@RequestParam(name = "name") String name) {
        String[] keywords = name.split("\\,");
        String[] resultIDsArr = new String[0];

        try {
            //build query
            String query = """
                SELECT ID 
                FROM MAPS 
                WHERE
            """;

            keywordLoop:
            for(String keyword : keywords) {
                //ignore certain keywords
                for(String ignoredKeyword : IGNORED_KEYWORDS) {
                    if(keyword.toLowerCase().equals(ignoredKeyword)) {
                        System.out.println("ignoring " + keyword);
                        continue keywordLoop;
                    }
                }

                query += "NAME LIKE '%" + keyword + "%' OR ";
            }
            query += "FALSE;";

            //get maps from db
            Connection connection = DriverManager.getConnection(Config.ADDRESS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            LinkedList<String> resultIDsList = new LinkedList<>();

            //fill ArrayList with resulting IDs
            while(resultSet.next()) {
                resultIDsList.add("" + resultSet.getInt("ID"));
            };

            //convert ArrayList to json String
            resultIDsArr = resultIDsList.toArray(String[]::new);

            //close everything
            statement.close();
            connection.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return resultIDsArr;
    }
}
