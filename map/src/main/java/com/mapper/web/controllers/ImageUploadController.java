package com.mapper.web.controllers;

import com.mapper.Config;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.mapper.beans.*;
import java.io.File;

@Controller
public class ImageUploadController  {

    @RequestMapping(value = "/imageupload", method = RequestMethod.POST)
    @ResponseBody
    public int uploadMap(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());

        try {
            file.transferTo(new File("src/main/resources/static/assets/maps/" + file.getOriginalFilename()).getAbsoluteFile());
        } catch(Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
