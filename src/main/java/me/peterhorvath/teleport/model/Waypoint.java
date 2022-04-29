package me.peterhorvath.teleport.model;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Waypoint {
    private final ItemStack item;
    private final Location location;
    private final String name;
    private final ChatColor color;

    public Waypoint(Material type, ChatColor color, Location location, String name) {
        this.location = location;
        this.name = name;
        this.color = color;
        this.item = createItemStack(type);
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

    @Override
    public String toString(){
        return getName() + " at " + getLocation();
    }

    public String toLocaleString(String locale){
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
        return getName() + localedAt + "X: " + loc.getBlockX() + " Y: " + loc.getBlockY() + " Z: " + loc.getBlockZ() + " " + localedWorld;
    }
}
