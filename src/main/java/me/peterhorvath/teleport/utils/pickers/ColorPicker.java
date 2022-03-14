package me.peterhorvath.teleport.utils.pickers;

import me.peterhorvath.teleport.utils.ColorManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ColorPicker {
    public static void openColorPickerMenu(Player player, String redirect){
        Inventory inv = Bukkit.createInventory(player,
                InventoryType.HOPPER, "Válassz színt! " + redirect);
        inv.addItem(ColorManager.generateColoredTerracotta("red"));
        inv.addItem(ColorManager.generateColoredTerracotta("green"));
        inv.addItem(ColorManager.generateColoredTerracotta("yellow"));
        inv.addItem(ColorManager.generateColoredTerracotta("blue"));
        inv.addItem(ColorManager.generateColoredTerracotta("aqua"));
        player.openInventory(inv);
    }

}
