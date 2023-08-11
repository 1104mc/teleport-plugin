package me.peterhorvath.teleport.model;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TeleportRequest {
    private final Player requester;
    private final Player target;
    private final LocalDateTime deadline;
    private static final Style actionStyle = Style.style(TextDecoration.BOLD);

    public TeleportRequest(Player requester, Player target) {
        this.requester = requester;
        this.target = target;
        this.deadline = LocalDateTime.now().plusMinutes(2);
        target.sendMessage(Component.text(requester.getName()+" játékos rád szeretne teleportálni. Írd be a chatbe a /tpaccept accept parancsot az elfogadáshoz vagy a /tpaccept deny az elutasításhoz!"));
        requester.sendMessage(Component.text("A teleport kérelmedet elküldtük "+target.getName()+" játékosnak.", NamedTextColor.GOLD));
    }

    public boolean isThisTarget(Player player){
        return target.equals(player);
    }

    private long dateAsLong(LocalDateTime localDateTime){
        return localDateTime.toInstant(ZoneOffset.of("+1")).getEpochSecond();
    }

    public boolean isExpired(){
        return dateAsLong(LocalDateTime.now()) > dateAsLong(this.deadline);
    }

    public void accept(){
        requester.sendMessage(Component.text(target.getName()+" elfogadta a teleport kérelmedet. Teleportálás...", NamedTextColor.GREEN));
        target.sendMessage(Component.text(requester.getName()+" teleportálása...", NamedTextColor.GREEN));
        requester.teleport(target);
    }

    public void reject(){
        requester.sendMessage(Component.text(target.getName()+" elutasította a kérelmedet.", NamedTextColor.RED));
        target.sendMessage(Component.text("Sikeresen elutasítottad "+requester.getName()+" kérelmét!", NamedTextColor.GREEN));
    }

    public void expired(){
        target.sendMessage(Component.text(requester.getName()+" teleport kérelme lejárt!", NamedTextColor.DARK_GRAY));
        requester.sendMessage(Component.text(target.getName()+" játékoshoz intézett teleport kérelmed lejárt!", NamedTextColor.DARK_RED));
    }
}
