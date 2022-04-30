package me.peterhorvath.teleport.model;

import me.peterhorvath.teleport.Teleport;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class Waypoint {
    private final ItemStack item;
    private final Location location;
    private final String name;
    private final String id;
    private final ChatColor color;

    public Waypoint(Material type, ChatColor color, Location location, String name, String id) {
        this.location = location;
        this.name = name;
        this.color = color;
        this.item = createItemStack(type);
        this.id = id;
    }

    private ItemStack createItemStack(Material type){
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(color + this.name);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getItem() {
        return item;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return color + name;
    }

    public String getName(boolean colored){
        if(!colored) return name;
        else return getName();
    }

    public ChatColor getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString(){
        return getName() + " at " + getLocation();
    }

    public String toLocaleString(String locale, boolean colored){
        String localedAt = "";
        if(locale.equals("en_us")) localedAt = " at ";
        else if(locale.equals("hu_hu")) localedAt = " itt: ";
        String localedWorld = "";
        String mc_world_name = getLocation().getWorld().getName();
        if(locale.equals("hu_hu")){
            if(mc_world_name.endsWith("nether")) localedWorld = "az alvilágban";
            else if(mc_world_name.endsWith("end")) localedWorld = "a végben";
            else localedWorld = "a normál világban";
        }else{
            if(mc_world_name.endsWith("nether")) localedWorld = "in the nether";
            else if(mc_world_name.endsWith("end")) localedWorld = "in the End";
            else localedWorld = "in the normal world";
        }
        Location loc = getLocation();
        return getName(colored) + "(id: " + getId() + " )" + localedAt +
                "X: " + loc.getBlockX() + " Y: " + loc.getBlockY() + " Z: " + loc.getBlockZ() + " " + localedWorld;
    }

    public String toLocaleString(String locale){
        return toLocaleString(locale, false);
    }

    public String getCommandSuggest(){
        return "/tp " + this.id;
    }

    public static ArrayList<Waypoint> getWaypointsInTheSameWorld(World world, ArrayList<Waypoint> allPoints){
        allPoints.removeIf(wp -> Objects.equals(wp.getLocation().getWorld(), world));
        return allPoints;
    }

    public static ArrayList<Waypoint> getWaypointsInTheSameWorld(World world){
        return getWaypointsInTheSameWorld(world, Teleport.waypoints);
    }
}
