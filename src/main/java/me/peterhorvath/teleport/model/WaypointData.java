package me.peterhorvath.teleport.model;

import me.peterhorvath.teleport.utils.ColorManager;
import org.bukkit.*;
import org.json.JSONObject;

public class WaypointData {
    private Material type;
    private ChatColor color;
    private String name;
    private Location loc;

    public WaypointData(JSONObject inputData){
        this.type = Material.matchMaterial(inputData.getString("type"));
        this.color = ColorManager.getColorByString(inputData.getString("color"));
        this.name = inputData.getString("name");
        this.loc = createLocation(inputData.getJSONObject("loc"));
    }


    private Location createLocation(JSONObject loc){
        float x = loc.getFloat("x");
        float y = loc.getFloat("y");
        float z = loc.getFloat("z");
        World world = Bukkit.getWorld(loc.getString("world"));
        return new Location(world, x, y, z);
    }

    public Waypoint generateWaypoint(){
        return new Waypoint(this.type, this.color, this.loc, this.name);
    }
}
