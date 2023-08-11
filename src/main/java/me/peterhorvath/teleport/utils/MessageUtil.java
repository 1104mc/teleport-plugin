package me.peterhorvath.teleport.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.geysermc.geyser.api.GeyserApi;

import java.util.Objects;

public class MessageUtil {
    private static boolean isBedrockPlayer(Player player){
        try {
            return GeyserApi.api().isBedrockPlayer(player.getUniqueId());
        }catch (RuntimeException e){
            return Objects.requireNonNull(player.getAddress()).getAddress().toString().startsWith("127.0.0.1");
        }
    }

    public static Component getClientMessage(Player player, Component java, String bedrock, TextColor bedrockColor){
        if(isBedrockPlayer(player)) return Component.text(bedrock, bedrockColor);
        return java;
    }
}
