package me.peterhorvath.teleport.events;

import me.peterhorvath.teleport.Teleport;
import me.peterhorvath.teleport.gui.TeleportConfirm;
import me.peterhorvath.teleport.gui.TeleportToMenu;
import me.peterhorvath.teleport.gui.TeleportToWaypointMenu;
import me.peterhorvath.teleport.model.Waypoint;
import me.peterhorvath.teleport.utils.pickers.ColorPicker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;

public class InventoryClick implements Listener {
    @EventHandler
    public void onTeleportMenuClick(InventoryClickEvent event){
        if(!event.getView().getTitle().startsWith("Teleport")) return;
        Player player = ((Player) event.getView().getPlayer()).getPlayer();
        assert player != null;
        switch (event.getView().getTitle()){
            case "Teleport menü":
                assert event.getCurrentItem() != null;
                switch (Objects.requireNonNull(event.getCurrentItem()).getType()){
                    case PLAYER_HEAD:
                        TeleportToMenu.openTeleportToPlayerMenu(player);
                        break;
                    case EMERALD:
                        TeleportToWaypointMenu.openInventory(player);
                        break;
                    default:
                        break;
                }
                break;
            case "Teleportálás játékoshoz":
                if(event.getCurrentItem() == null) break;
                SkullMeta meta = (SkullMeta) Objects.requireNonNull(event.getCurrentItem()).getItemMeta();
                if(meta == null) break;
                Player target = (Player) meta.getOwningPlayer();
                assert target != null;
                if(Objects.equals(player.getCustomName(), "bedrock")){
                    player.closeInventory();
                    TeleportConfirm.openConfirmMenu(player, event.getCurrentItem());
                }else{
                    player.teleport(target.getLocation());
                }
                break;
            case "Teleport states":
                Teleport.logger.info("Waypoints slot: " + event.getSlot());
                ArrayList<Waypoint> wps = Waypoint.getWaypointsInTheSameWorld(player.getWorld());
                Waypoint waypoint = wps.get(event.getSlot());
                player.closeInventory();
                player.teleport(waypoint.getLocation());
            default:
                break;
        }
        if(event.getView().getTitle().startsWith(TeleportConfirm.titleBase)){
            if(event.getCurrentItem().getType() == TeleportConfirm.acceptMaterial){
                Player target = Bukkit.getPlayer(event.getView().getTitle());;
                assert target != null;
                player.teleport(target.getLocation());
            }else if(event.getCurrentItem().getType() == TeleportConfirm.cancelMaterial){
                player.closeInventory();
            }
        }
        event.setCancelled(true);
    }
}
