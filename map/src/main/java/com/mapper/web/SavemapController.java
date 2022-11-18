package com.mapper.web;

import com.mapper.beans.SerializedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.*;
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
            File file = new File(MAPS_DIR + input.name + ".sr");
            file.createNewFile();

            FileOutputStream fs = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(input);

            os.close();
            fs.close();

            status = new ResponseEntity(HttpStatus.ACCEPTED);

            FileInputStream fis = new FileInputStream(MAPS_DIR + input.name + ".sr");
            ObjectInputStream ois = new ObjectInputStream(fis);

            SerializedMap serMap = (SerializedMap) ois.readObject();

            System.out.println(serMap);

            fs.close();
            os.close();
        } catch(Exception e) {
            e.printStackTrace();
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return status;
    }
}