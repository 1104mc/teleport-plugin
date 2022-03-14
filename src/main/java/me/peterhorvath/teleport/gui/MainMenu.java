package me.peterhorvath.teleport.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class MainMenu {
    public static void openMainMenu(Player player){
        Inventory inv = Bukkit.createInventory(player, InventoryType.HOPPER, "Teleport menü");
        System.out.println(inv);
        ItemStack playerItem = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta playerMeta = playerItem.getItemMeta();
        assert playerMeta != null;
        playerMeta.setDisplayName("Teleportálás játékoshoz!");
        playerItem.setItemMeta(playerMeta);
        ItemStack waystoneItem = new ItemStack(Material.EMERALD);
        ItemMeta wsMeta = playerItem.getItemMeta();
        assert wsMeta != null;
        wsMeta.setDisplayName(ChatColor.GOLD + "Teleportálás állomásra");
        waystoneItem.setItemMeta(wsMeta);
        inv.addItem(new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
        inv.addItem(playerItem);
        inv.addItem(waystoneItem);
        inv.setItem(4, new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
        player.openInventory(inv);
    }

}
