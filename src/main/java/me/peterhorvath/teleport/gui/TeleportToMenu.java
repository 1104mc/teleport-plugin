package me.peterhorvath.teleport.gui;

import me.peterhorvath.teleport.Teleport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TeleportToMenu {
    public static void openTeleportToPlayerMenu(Player player){
        int playerNumber = Bukkit.getOnlinePlayers().size();
        Inventory inv;
        String title = "Teleportálás játékoshoz";
        if(playerNumber <= 5) inv = Bukkit.createInventory(player, InventoryType.HOPPER, title);
        else if(playerNumber <= 9) inv = Bukkit.createInventory(player, 9, title);
        else inv = Bukkit.createInventory(player, playerNumber, title);
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        int invIndex = 0;
        for (int i = 0; i < playerNumber; i++) {
            if(players.get(i) == player) continue;
            inv.setItem(invIndex, PlayerHead.getPlayerHead(players.get(i), ChatColor.GOLD, true));
            Teleport.getPlugin(Teleport.class).getLogger().info(players.get(0).getDisplayName() + " : " + invIndex);
            invIndex++;
        }
        player.openInventory(inv);
    }
}
