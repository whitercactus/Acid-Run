package com.example.web;

import com.example.beans.SerializedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;

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
    public ResponseEntity saveMap(@RequestBody SerializedMap input, BindingResult result) {
        System.out.println("POSTING " + Double.parseDouble("0.38862275449101796"));
        System.out.println(input.toString());
        ResponseEntity status;
        try {
            File file = new File(input.name + ".json");
            FileWriter writer = new FileWriter(file);

            writer.close();
            writer.write(input.toString());

            status = new ResponseEntity(HttpStatus.ACCEPTED);
        } catch(Exception e) {
            e.printStackTrace();
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return status;
    }
}