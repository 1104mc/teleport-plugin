package me.peterhorvath.teleport.utils;

import me.peterhorvath.teleport.Teleport;
import me.peterhorvath.teleport.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static me.peterhorvath.teleport.utils.ConfigManager.waypointConfig;

public class WaypointManager {
    public static final List<Waypoint> waypoints = new ArrayList<Waypoint>();

    public static void DecodeWaypoints(){
        JSONArray jsonData = waypointConfig.getContent().getJSONArray("waypoints");
        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject waypointRawData = jsonData.getJSONObject(i);
            WaypointData waypointData = new WaypointData(waypointRawData);
            waypoints.add(waypointData.generateWaypoint());
        }
        System.out.println(waypoints);
    }


}
