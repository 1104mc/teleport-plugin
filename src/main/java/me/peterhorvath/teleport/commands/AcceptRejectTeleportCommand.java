package me.peterhorvath.teleport.commands;

import me.peterhorvath.teleport.utils.TeleportUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class AcceptRejectTeleportCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(args.length != 1) {
            player.sendMessage(Component.text("Elfogadod vagy elutasítod a kérelmet?"));
            return false;
        }
        String option = args[0];
        if(option.equals("accept")) {
            TeleportUtil.acceptTeleport(player);
            return true;
        }
        if(option.equals("deny")){
            TeleportUtil.rejectTeleport(player);
            return true;
        }
        player.sendMessage(Component.text("Na mi lesz?"));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length > 1) return null;
        return Arrays.asList("accept", "deny");
    }
}
