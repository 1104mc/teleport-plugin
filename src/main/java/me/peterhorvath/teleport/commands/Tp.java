package me.peterhorvath.teleport.commands;

import me.peterhorvath.teleport.Teleport;
import me.peterhorvath.teleport.gui.MainMenu;

import me.peterhorvath.teleport.model.Waypoint;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.logging.Level;


public class Tp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = ((Player) sender).getPlayer();
        assert player != null;
        if(args.length == 0){
            MainMenu.openMainMenu(player);
        }else if(args.length == 1){
            //TODO: go to waypoint
            if(args[0].equalsIgnoreCase("help")){
                player.sendMessage(ChatColor.GREEN + player.getLocale());
            }
        }else if(args.length == 3){
            //TODO: go to position
        }
        return false;
    }
}
