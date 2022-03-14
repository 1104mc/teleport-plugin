package me.peterhorvath.teleport.events;

import me.peterhorvath.teleport.Teleport;
import me.peterhorvath.teleport.gui.TeleportToMenu;
import me.peterhorvath.teleport.utils.pickers.ColorPicker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;

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
                        //TODO: open teleport to waypoint menu
                        break;
                    default:
                        break;
                }
                break;
            case "Teleportálás játékoshoz":
                SkullMeta meta = (SkullMeta) Objects.requireNonNull(event.getCurrentItem()).getItemMeta();
                assert meta != null;
                Player target = (Player) meta.getOwningPlayer();
                assert target != null;
                player.teleport(target.getLocation());
                break;
            default:
                break;
        }
        event.setCancelled(true);
    }

    //TODO: manage pickers and waypoint creation porcess
}
