package me.peterhorvath.teleport.gui;

import me.peterhorvath.teleport.Teleport;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.logging.Level;

public class PlayerHead {
    public static ItemStack getPlayerHead(Player player, ChatColor color){
        return getPlayerHead(player, color, false);
    }
    public static ItemStack getPlayerHead(Player player, ChatColor color, boolean debug){
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        assert meta != null;
        meta.setOwningPlayer(player);
        meta.setDisplayName(color + "Teleportálás " + player.getDisplayName() + " játékoshoz!");
        if(debug) Teleport.getPlugin(Teleport.class).getLogger().log(Level.INFO, "Created head for player "
                + player.getDisplayName());
        skull.setItemMeta(meta);
        return skull;
    }
}
