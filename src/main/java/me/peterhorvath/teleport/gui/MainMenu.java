package me.peterhorvath.teleport.gui;

import me.peterhorvath.teleport.Teleport;
import me.peterhorvath.teleport.utils.LocaleUtil;
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
        Inventory inv = Bukkit.createInventory(player, InventoryType.HOPPER,
                LocaleUtil.getLocaledMenuTitle(player.getLocale(), LocaleUtil.UIMenu.MainMenu));
        inv.setItem(1, getTeleportToPlayerItem(player)); //Player Head item
        player.openInventory(inv);
    }

    private static ItemStack getTeleportToPlayerItem(Player player){
        ItemStack playerItem = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta playerMeta = playerItem.getItemMeta();
        assert playerMeta != null;
        playerMeta.setDisplayName(LocaleUtil.getTextByLocale("hu_hu", player.getLocale(), "Teleportálás játékoshoz!", "Teleport to player!"));
        playerItem.setItemMeta(playerMeta);
        return playerItem;
    }
}
