package me.peterhorvath.teleport.config;

import com.google.gson.Gson;
import me.peterhorvath.teleport.Teleport;
import me.peterhorvath.teleport.model.Waypoint;
import me.peterhorvath.teleport.model.WaypointData;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Config {
    private final File file;
    private WaypointData[] json;

    public Config() throws EOFException {
        if(!Teleport.configDir.exists()) Teleport.configDir.mkdir();
        file = new File(Teleport.configDir, ConfigConstans.waypointFilename);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        String data = null;
        try{
            data = new Scanner(file).next();
        }catch(FileNotFoundException ex){
            Teleport.logger.info("Nem található konfig!");
        }
        Teleport.logger.info(data);
        try{
            json = new Gson().fromJson(data, WaypointData[].class);
        }catch (NoSuchElementException ex){
            Teleport.logger.info("Üres a konfig!");
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
