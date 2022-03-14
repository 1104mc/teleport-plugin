package me.peterhorvath.teleport.utils;

import me.peterhorvath.teleport.Teleport;
import me.peterhorvath.teleport.model.Config;

public class ConfigManager {
    public static final Teleport plugin = Teleport.getPlugin(Teleport.class);
    public static final Config waypointConfig = new Config("waypoints.json", plugin);
}
