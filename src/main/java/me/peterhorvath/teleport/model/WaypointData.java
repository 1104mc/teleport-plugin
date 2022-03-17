package me.peterhorvath.teleport.model;

import me.peterhorvath.teleport.config.ConfigConstans;
import me.peterhorvath.teleport.utils.ColorManager;
import org.bukkit.*;

public class WaypointData {
    private String type;
    private String color;
    private String name;
    private String id;
    private LocationData loc;


    public Waypoint generateWaypoint(){
        return new Waypoint(ConfigConstans.blocks.get(type),
                ColorManager.getColorByString(this.color), this.loc.toLocation(), this.name);
    }

    public String getId() {
        return id;
    }
}
