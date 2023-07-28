package me.peterhorvath.teleport.commands;

import me.peterhorvath.teleport.gui.MainMenu;
import me.peterhorvath.teleport.gui.TeleportToPlayerMenu;
import me.peterhorvath.teleport.utils.TeleportUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;


public class Tp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = ((Player) sender).getPlayer();
        assert player != null;
        if(args.length == 0) {
            TeleportToPlayerMenu.openTeleportToPlayerMenu(player);
        }else if(args.length == 1){
            List<? extends Player> players = Bukkit.getOnlinePlayers().stream()
                    .filter(player1 -> player1.getName().equals(args[0]))
                    .toList();
            if(players.isEmpty()){
                player.sendMessage(Component.text("Player not found!", NamedTextColor.DARK_RED));
                return false;
            }
            TeleportUtil.askForTeleport(player, players.get(0));
        }else if(args.length == 3){
            //TODO: go to position
        }
        return false;
    }
}
