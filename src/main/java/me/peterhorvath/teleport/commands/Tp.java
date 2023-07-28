package me.peterhorvath.teleport.commands;

import me.peterhorvath.teleport.gui.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Tp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = ((Player) sender).getPlayer();
        assert player != null;
        if(args.length == 0){
            MainMenu.openMainMenu(player);
        }else if(args.length == 3){
            //TODO: go to position
        }
        return false;
    }
}
