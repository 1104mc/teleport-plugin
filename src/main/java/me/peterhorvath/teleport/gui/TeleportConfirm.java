package me.peterhorvath.teleport.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class TeleportConfirm {
    public static final Material acceptMaterial = Material.GREEN_TERRACOTTA;
    public static final Material cancelMaterial = Material.BARRIER;
    public static final String titleBase = "Teleportálás játékoshoz: ";

    public static void openConfirmMenu(Player player, ItemStack skull){
        Player target = (Player) ((SkullMeta) skull.getItemMeta()).getOwningPlayer();
        Inventory inventory = Bukkit.createInventory(player, InventoryType.HOPPER, titleBase + target.getDisplayName());
        inventory.setItem(0, new ItemStack(acceptMaterial));
        inventory.setItem(2, skull);
        inventory.setItem(4, new ItemStack(cancelMaterial));
        player.openInventory(inventory);
    }

    public static String getPlayerNameByTitle(String title){
        return title.split(titleBase)[1];
    }
}
