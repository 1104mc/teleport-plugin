package me.peterhorvath.teleport.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ColorManager {
    public static ChatColor getColorByString(String colorText){
        switch (colorText){
            case "red":
                return ChatColor.RED;
            case "blue":
                return ChatColor.BLUE;
            case "green":
                return ChatColor.GREEN;
            case "gold":
                return ChatColor.GOLD;
            case "aqua":
                return ChatColor.AQUA;
            default:
                break;
        }
        return null;
    }

    public static ItemStack generateColoredTerracotta(String color){
        switch (color){
            case "red":
                ItemStack item = new ItemStack(Material.RED_TERRACOTTA);
                item.setItemMeta(generateMetaForTerracotta(color,"Piros", item));
                return item;
            case "blue":
                ItemStack item2 = new ItemStack(Material.LIGHT_BLUE_TERRACOTTA);
                item2.setItemMeta(generateMetaForTerracotta(color, "Kék", item2));
                return item2;
            case "green":
                ItemStack item3 = new ItemStack(Material.GREEN_TERRACOTTA);
                item3.setItemMeta(generateMetaForTerracotta(color, "Zöld", item3));
                return item3;
            case "gold":
                ItemStack item4 = new ItemStack(Material.YELLOW_TERRACOTTA);
                item4.setItemMeta(generateMetaForTerracotta(color, "Arany", item4));
                return item4;
            case "aqua":
                ItemStack item5 = new ItemStack(Material.CYAN_TERRACOTTA);
                item5.setItemMeta(generateMetaForTerracotta(color, "Aqua", item5));
                return item5;
            default:
                break;
        }
        return null;
    }

    public static ItemMeta generateMetaForTerracotta(String color, String colorName, ItemStack item){
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ColorManager.getColorByString(color) + colorName);
        return meta;
    }
}
