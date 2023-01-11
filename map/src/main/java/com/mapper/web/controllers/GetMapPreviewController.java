package com.mapper.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.Config;
import com.mapper.beans.MapPreview;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@Controller
public class GetMapPreviewController {

    @RequestMapping(value = "/getmappreview", method = RequestMethod.GET)
    @ResponseBody
    public MapPreview[] saveMap(@RequestParam(name = "IDs") String input) {
        String[] idArr = input.split("\\,");
        //prevent client from requesting too many maps at a time
        if(idArr.length > Config.MAX_REQUESTED_IDS) {
            return new MapPreview[0];
        }
        MapPreview[] mapsArr = new MapPreview[idArr.length];

        for (int i = 0; i < idArr.length; i++) {
            try {
                //read map files with requested IDs
                File file = new File(Config.MAPS_DIR + "/" + idArr[i] + ".json");
                ObjectMapper mapper = new ObjectMapper();
                SerializedMap serMap = mapper.readValue(file, SerializedMap.class);
                MapPreview mapPreview = new MapPreview(serMap);
                mapsArr[i] = mapPreview;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return mapsArr;
    }
}