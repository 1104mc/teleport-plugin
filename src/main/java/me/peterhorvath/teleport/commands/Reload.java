package me.peterhorvath.teleport.commands;

import me.peterhorvath.teleport.Teleport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp()) return false;
        Teleport.waypointConfig.reload();
        Teleport.waypoints = Teleport.waypointConfig.getAllWaypoints();
        sender.sendMessage("Reloaded...");
        return true;
    }
}
