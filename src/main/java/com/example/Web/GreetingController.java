package com.example.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

@Controller
public class GreetingController {
    private final String MAPS_DIR = "src/main/resources/static/maps/";

//    @GetMapping("/")
//    public String greeting(@RequestParam(name="", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "greeting";
//    }

    @RequestMapping(value = "/savemap", method = RequestMethod.POST)
    @ResponseBody
    public void saveMap(@RequestBody String input) {
        System.out.println("POSTING " + input);
        try {
            //read request
            Scanner scn = new Scanner(input);
            int mapId = scn.nextInt();
            String mapJson = "";

            while(scn.hasNextLine()) {
                mapJson += scn.nextLine();
            }

            //create/find file to save map in
            File file = new File(MAPS_DIR + mapId + ".json");
            file.createNewFile();

            //write map data to file
            FileWriter fw = new FileWriter(file, false);
            fw.write(mapJson);
            fw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}