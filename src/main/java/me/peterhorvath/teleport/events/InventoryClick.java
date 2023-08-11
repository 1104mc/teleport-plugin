package me.peterhorvath.teleport.events;

import me.peterhorvath.teleport.gui.TeleportToPlayerMenu;
import me.peterhorvath.teleport.utils.LocaleUtil;
import me.peterhorvath.teleport.utils.TeleportUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Objects;

public class InventoryClick implements Listener {
    @EventHandler
    public void onTeleportMenuClick(InventoryClickEvent event){
        if(!event.getView().getTitle().startsWith("Teleport")) return;
        Player player = ((Player) event.getView().getPlayer()).getPlayer();
        assert player != null;
        switch (Objects.requireNonNull(LocaleUtil.getMenuByLocaledName(event.getView().getTitle()))){
            case MainMenu:
                assert event.getCurrentItem() != null;
                switch (Objects.requireNonNull(event.getCurrentItem()).getType()){
                    case PLAYER_HEAD:
                        TeleportToPlayerMenu.openTeleportToPlayerMenu(player);
                        break;
                    default:
                        break;
                }
                break;
            case TeleportToPlayer:
                if(event.getCurrentItem() == null) break;
                SkullMeta meta = (SkullMeta) Objects.requireNonNull(event.getCurrentItem()).getItemMeta();
                if(meta == null) break;
                Player target = (Player) meta.getOwningPlayer();
                assert target != null;
                TeleportUtil.askForTeleport(player, target);
                Objects.requireNonNull(event.getClickedInventory()).close();
                break;
        }
        event.setCancelled(true);
    }
}
