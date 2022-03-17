package me.peterhorvath.teleport.model;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationData {
    public String world;
    public int x;
    public int y;
    public int z;

    public Location toLocation(){
        return new Location(Bukkit.getWorld(world), x, y, z);
    }
}
