package me.peterhorvath.teleport.gui;

import me.peterhorvath.teleport.Teleport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;


public class MainMenu {
    public static void openMainMenu(Player player){
        Inventory inv = Bukkit.createInventory(player, InventoryType.HOPPER, "Teleport menü");

        //Player Head item
        ItemStack playerItem = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta playerMeta = playerItem.getItemMeta();
        assert playerMeta != null;
        playerMeta.setDisplayName("Teleportálás játékoshoz!");
        playerItem.setItemMeta(playerMeta);
        inv.setItem(1, playerItem);
        //Waystone Item
        ItemStack waystoneItem = new ItemStack(Material.EMERALD);
        ItemMeta wsMeta = playerItem.getItemMeta();
        assert wsMeta != null;
        wsMeta.setDisplayName(ChatColor.GOLD + "Teleportálás állomásra");
        waystoneItem.setItemMeta(wsMeta);
        inv.setItem(3, waystoneItem);
        player.openInventory(inv);
    }

}
