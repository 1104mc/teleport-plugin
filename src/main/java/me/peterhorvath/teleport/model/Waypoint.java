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
}
