package com.mapper.web;

import com.mapper.beans.SerializedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;

@Controller
public class SavemapController {
    private final String MAPS_DIR = "src/main/resources/static/maps/";

//    @GetMapping("/")
//    public String greeting(@RequestParam(name="", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "greeting";
//    }

    @RequestMapping(value = "/savemap", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveMap(@RequestBody SerializedMap input, BindingResult result) {
        System.out.println("POSTING");
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