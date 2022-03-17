package me.peterhorvath.teleport.config;

import com.google.gson.Gson;
import me.peterhorvath.teleport.Teleport;
import me.peterhorvath.teleport.model.Waypoint;
import me.peterhorvath.teleport.model.WaypointData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Config {
    private final File file;
    private WaypointData[] json;

    public Config() {
        if(!Teleport.configDir.exists()) Teleport.configDir.mkdir();
        file = new File(Teleport.configDir, ConfigConstans.waypointFilename);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        try{
            json = new Gson().fromJson(new Scanner(file).next(), WaypointData[].class);
        }catch (FileNotFoundException ex){
            Teleport.logger.info(file.getPath());
            ex.printStackTrace();
        }
    }

    public Map<String, Waypoint> getWaypoints(){
        Map<String, Waypoint> waypoints = new HashMap<>();
        for (WaypointData data: json) {
            waypoints.put(data.getId(),data.generateWaypoint());
        }
        return waypoints;
    }
}
