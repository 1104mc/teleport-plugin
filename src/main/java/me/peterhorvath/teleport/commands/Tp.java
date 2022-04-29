package me.peterhorvath.teleport.commands;

import me.peterhorvath.teleport.Teleport;
import me.peterhorvath.teleport.gui.MainMenu;

import me.peterhorvath.teleport.model.Waypoint;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
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
        Teleport.logger.info("Number of arguments");
        assert player != null;
        if(args.length == 0){
            MainMenu.openMainMenu(player);
        }else if(args.length == 1){
            // go to waypoint
            if(args[0].equalsIgnoreCase("help")){
                String locatedTitle = "";
                if(player.getLocale().equals("en_us")) locatedTitle = "The places where you can go";
                else if(player.getLocale().equals("hu_hu")) locatedTitle = "A helyek ahová mehetsz";
                player.sendMessage(ChatColor.GREEN + locatedTitle);

                for (Waypoint waypoint: Teleport.waypoints) {
                    TextComponent msg = new TextComponent(waypoint.toLocaleString(player.getLocale()));
                    msg.setColor(waypoint.getColor().asBungee());
                    msg.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, waypoint.getCommandSuggest()));
                    player.spigot().sendMessage(msg);
                }
            }else{
                boolean succeed = false;
                Teleport.logger.info("Preparing to teleport player " + player.getName() + " to " + args[0]);
                for (Waypoint wp: Teleport.waypoints) {
                    if(wp.getName(false).equals(args[0])){
                        succeed = true;
                        player.teleport(wp.getLocation());
                        String localeSuccess = "";
                        if(player.getLocale().equals("hu_hu")) localeSuccess = player.getName() +
                                " sikeresen teleportálva a(z) " + wp.getName(false) + " helyre.";
                        else localeSuccess = player.getName() + " has teleported successfully to " + wp.getName(false);
                        player.sendMessage(ChatColor.GREEN + localeSuccess);
                        break;
                    }
                }
                if(!succeed){
                    String locale404 = "";
                    if(player.getLocale().equals("hu_hu")) locale404 = "A(z) " + args[0] + " helyszín nem található!";
                    else locale404 = "The place named " + args[0] + " not found!";
                    player.sendMessage(ChatColor.DARK_RED + locale404);
                }
            }
        }else if(args.length == 3){
            //TODO: go to position
        }
        return false;
    }
}
