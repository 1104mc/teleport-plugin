package me.peterhorvath.teleport.commands;

import me.peterhorvath.teleport.utils.TeleportUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AcceptRejectTeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(label.equals("tpa")) TeleportUtil.acceptTeleport(player);
        else TeleportUtil.rejectTeleport(player);
        return true;
    }
}
