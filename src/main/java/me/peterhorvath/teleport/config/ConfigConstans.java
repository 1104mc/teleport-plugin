package me.peterhorvath.teleport.config;

import org.bukkit.Material;

import java.util.Map;

public interface ConfigConstans {
    public static final String waypointFilename = "waypoints.json";

    public static final Map<String, Material> blocks = Map.of(
            "iron_ingot", Material.IRON_INGOT,
            "rotten_flesh", Material.ROTTEN_FLESH,
            "chest", Material.CHEST,
            "deepstale_brick_chair", Material.DEEPSLATE_BRICK_STAIRS,
            "blaze_rod", Material.BLAZE_ROD
    );
}
