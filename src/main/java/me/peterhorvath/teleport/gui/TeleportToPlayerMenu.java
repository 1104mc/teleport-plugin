package me.peterhorvath.teleport.gui;

import me.peterhorvath.teleport.utils.LocaleUtil;
import me.peterhorvath.teleport.utils.PlayerHead;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class TeleportToPlayerMenu {
    public static void openTeleportToPlayerMenu(Player player){
        //check player number
        int playerNumber = Bukkit.getOnlinePlayers().size() - 1;
        if(playerNumber == 0){
            player.sendMessage(LocaleUtil.getTextByLocale("hu_hu", player.getLocale(),
                    "Nem tudjuk megnyitni a menüt mert csak te vagy fönn a szerveren!",
                    "Sorry, we can't open the menu because only you playing here!"));
            return;
        }
        //create the inventory
        Inventory inv;
        String title = LocaleUtil.getLocaledMenuTitle(player.getLocale(), LocaleUtil.UIMenu.TeleportToPlayer);
        if(playerNumber <= 5) inv = Bukkit.createInventory(player, InventoryType.HOPPER, title);
        else if(playerNumber <= 9) inv = Bukkit.createInventory(player, 9, title);
        else inv = Bukkit.createInventory(player, playerNumber, title);
        //add player heads
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        players.remove(player);
        for (int i = 0; i < playerNumber; i++) {
            if(players.get(i).getDisplayName().equals(player.getDisplayName())) continue;
            if(players.get(i) != null) inv.setItem(i, PlayerHead.getPlayerHead(players.get(i), ChatColor.GOLD));
        }
        //open the inventory
        player.openInventory(inv);
    }
}
