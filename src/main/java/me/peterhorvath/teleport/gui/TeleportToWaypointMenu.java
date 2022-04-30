package me.peterhorvath.teleport.gui;

import me.peterhorvath.teleport.model.Waypoint;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class TeleportToWaypointMenu {
    public static void openInventory(Player player){
        ArrayList<Waypoint> waypoints = Waypoint.getWaypointsInTheSameWorld(player.getWorld());
        Inventory inv;
        String title = "Teleport states";
        if(waypoints.size() <= 5) inv = Bukkit.createInventory(player, InventoryType.HOPPER, title);
        else if(waypoints.size() <= 9) inv = Bukkit.createInventory(player, 9, title);
        else inv = Bukkit.createInventory(player, waypoints.size(), title);
        for (Waypoint wp: waypoints) {
            inv.addItem(wp.getItem());
        }
        player.openInventory(inv);
    }
}
