package me.peterhorvath.teleport.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.geysermc.geyser.api.GeyserApi;

public class MessageUtil {
    public static Component getClientMessage(Player player, Component java, String bedrock, TextColor bedrockColor){
        if(GeyserApi.api().isBedrockPlayer(player.getUniqueId())) return Component.text(bedrock, bedrockColor);
        return java;
    }
}
